package fc.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public void uploadToS3(MultipartFile file);
}
