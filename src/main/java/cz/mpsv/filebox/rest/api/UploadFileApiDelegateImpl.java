package cz.mpsv.filebox.rest.api;

import cz.mpsv.filebox.api.UploadApiDelegate;
import cz.mpsv.filebox.service.FileManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadFileApiDelegateImpl implements UploadApiDelegate {

    private final FileManagerService fileManagerService;

    @Override
    public ResponseEntity<Void> uploadCsvKeyPost(String key, MultipartFile file) {
        log.info("Upload File " + file.getName());

//        fileManagerService.upload(file, key);
        return ResponseEntity.ok().build();
//        try {
//            if (fileManagerService.uploadAndDownloadFile(file, "container")) {
//                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileManagerService
//                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//            }
//            log.error("Error while processing file");
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            log.error("Error while processing file " + e.getMessage());
//            return ResponseEntity.internalServerError().build();
//        }
    }
}
