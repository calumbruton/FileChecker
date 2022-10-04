package service.impl;

import service.FileUploadService;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void uploadToS3(MultipartFile file) {
        return;
    }
}
