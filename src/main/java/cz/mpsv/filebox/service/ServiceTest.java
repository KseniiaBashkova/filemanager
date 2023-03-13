package cz.mpsv.filebox.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import cz.mpsv.filebox.property.AzureBlobProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class ServiceTest {


    @Autowired
    AzureBlobProperties azureBlobProperties;


    private BlobContainerClient blobContainerClient() {
        // create container if not exists
        // azure mpsv
//        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
//                .connectionString("BlobEndpoint=https://stfilebox.blob.core.windows.net/;QueueEndpoint=https://stfilebox.queue.core.windows.net/;FileEndpoint=https://stfilebox.file.core.windows.net/;TableEndpoint=https://stfilebox.table.core.windows.net/;SharedAccessSignature=sv=2021-12-02&ss=bfqt&srt=sco&sp=rwdlacupiytfx&se=2023-03-10T23:33:03Z&st=2023-03-10T15:33:03Z&spr=https,http&sig=0%2BcQ%2FbNCZECHmr%2FPtPfxV52%2FUDBFaTXpzYZ20rmgpAs%3D").buildClient();
//        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("cfilebox");

        // azure my
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString("BlobEndpoint=https://stfile.blob.core.windows.net/;QueueEndpoint=https://stfile.queue.core.windows.net/;FileEndpoint=https://stfile.file.core.windows.net/;TableEndpoint=https://stfile.table.core.windows.net/;SharedAccessSignature=sv=2021-12-02&ss=bfqt&srt=sco&sp=rwdlacupiytfx&se=2023-03-13T16:16:31Z&st=2023-03-13T08:16:31Z&spr=https,http&sig=tCyyjxKJl2L6xXLj8Nbyc%2FwqSh1fgKAwL86B7yTgrgs%3D").buildClient();
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("container");
        return blobContainerClient;
    }

    public List<String> listFields() {
        log.info("List fields");
        BlobContainerClient blobContainerClient = blobContainerClient();
        List<String> list = new ArrayList<>();

        for(BlobItem item : blobContainerClient.listBlobs()) {
            log.info("blob: " + item.getName());
            list.add(item.getName());
        }
        log.info("List fields end");
        return list;
    }
}
