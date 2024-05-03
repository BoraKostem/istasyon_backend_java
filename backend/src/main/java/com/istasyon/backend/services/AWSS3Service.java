package com.istasyon.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class AWSS3Service {
    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile file, String directoryPrefix) throws Exception {
        String fileName = directoryPrefix + System.currentTimeMillis() + "-" + file.getOriginalFilename();
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        // Generate the file URL
        String fileUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
        return fileUrl;
    }

    public String createFolder(String folderName) {
        String folderKey = "companies/" + folderName + "/";
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(folderKey)
                        .build(),
                RequestBody.fromBytes(new byte[0]));
        String folderUrl = "https://" + bucketName + ".s3.amazonaws.com/" + folderKey;
        return folderUrl;
    }
}
