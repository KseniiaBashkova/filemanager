package cz.mpsv.filebox.rest.api;

import cz.mpsv.filebox.api.DownloadApiDelegate;
import cz.mpsv.filebox.service.FileManagerService;
import cz.mpsv.filebox.service.ServiceTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

@Service
@RequiredArgsConstructor
@Slf4j
public class DownloadFileApiDelegateImpl implements DownloadApiDelegate {

    private final FileManagerService fileManagerService;

    private final ServiceTest service;

    @Value("https://stfilebox.blob.core.windows.net/cfilebox/perf.csv")
    private Resource blobFile;

    @Override
    public ResponseEntity<String> downloadCsvKeyGet(String key) {
        log.info("Download File");
        service.listFields();
        try {
            return ResponseEntity.ok(StreamUtils.copyToString(this.blobFile.getInputStream(), Charset.defaultCharset()));
        } catch (Exception e) {
            log.error("Cant read file " + e.getMessage());
            return null;
        }
//        return fileManagerService.downloadFile(key);
    }
}
