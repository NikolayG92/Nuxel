package com.example.nuxel.userservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {


    String uploadImageToCurrentFolder(MultipartFile multipartFile, String folderName) throws IOException;
}
