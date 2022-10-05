package fc.service.impl;

import fc.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void uploadToS3(MultipartFile file) {
        return;
    }
}
