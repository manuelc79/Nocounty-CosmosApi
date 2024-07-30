package com.cosmos_api.Cosmos.API.FileStorageService.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        
        Path filePath = Paths.get(uploadDir, fileName);

        Files.copy(file.getInputStream(), filePath);
        
        return "/files/" + fileName;
    }
}
