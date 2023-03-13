package cz.mpsv.filebox.service;

import com.azure.storage.blob.*;
import com.azure.storage.blob.specialized.BlockBlobClient;
import cz.mpsv.filebox.property.AzureBlobProperties;
import cz.mpsv.filebox.property.FileStorageProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileManagerService {

    private final Path fileStorageLocation;
    private final BlobServiceClient blobServiceClient;


    @Autowired
    public FileManagerService(@NonNull FileStorageProperties fileStorageProperties, BlobServiceClient blobServiceClient) {
        // get the path of the upload directory
        fileStorageLocation = Path.of(fileStorageProperties.getUploadDir());
        this.blobServiceClient = blobServiceClient;
        try {
            // creates directory/directories, if directory already exists, it will not throw exception
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            log.error("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public Path getFileStorageLocation() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            log.error("Could not create the directory where the uploaded file will be stored.", e);
        }
        return fileStorageLocation;
    }

    private @NonNull BlobContainerClient getBlobContainerClient(@NonNull String containerName) {
        // create container if not exists
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }
        return blobContainerClient;
    }

    public Boolean uploadAndDownloadFile(@NonNull MultipartFile file, String containerName) {
        log.info("Start upload file 1");

        boolean isSuccess = true;
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        String filename = file.getOriginalFilename();
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(filename).getBlockBlobClient();
        try {
            log.info("Start upload file");

            // delete file if already exists in that container
            if (Boolean.TRUE.equals(blockBlobClient.exists())) {
                blockBlobClient.delete();
            }
            // upload file to azure blob storage
            blockBlobClient.upload(new BufferedInputStream(file.getInputStream()), file.getSize(), true);
            String tempFilePath = fileStorageLocation + "/" + filename;
            Files.deleteIfExists(Paths.get(tempFilePath));
            // download file from azure blob storage to a file
            blockBlobClient.downloadToFile(new File(tempFilePath).getPath());
        } catch (IOException e) {
            isSuccess = false;
            log.error("Error while processing file {}", e.getLocalizedMessage());
        }
        return isSuccess;
    }

    public void deleteFile(String key) {
        BlobContainerClient blobContainerClient = getBlobContainerClient("files");
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(key).getBlockBlobClient();
        if (Boolean.TRUE.equals(blockBlobClient.exists())) {
            blockBlobClient.delete();
        }
    }


    public ResponseEntity<Void> uploadFile(String key, MultipartFile file) {
        String connection = "AccountName=<acName>;" +
                "AccountKey= <>;" +
                "EndpointSuffix=core.windows.net;" +
                "DefaultEndpointProtocol=https;";

        BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
                .connectionString(connection)
                .containerName("test-container")
                .buildClient();
        BlobClient blobClient = blobContainerClient.getBlobClient(key);
        try {
            blobClient.upload(file.getInputStream(), file.getSize(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



    public ResponseEntity<String> downloadFile(String key) {
        return null;
    }

}
