package cz.mpsv.filebox.rest.api;

import cz.mpsv.filebox.api.DeleteApiDelegate;
import cz.mpsv.filebox.service.FileManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteFileApiDelegateImpl implements DeleteApiDelegate {

    private final FileManagerService fileManagerService;

    @Override
    public ResponseEntity<Void> deleteCsvKeyDelete(String key) {
        fileManagerService.deleteFile(key);
        return ResponseEntity.ok().build();
    }
}
