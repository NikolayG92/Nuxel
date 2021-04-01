package com.example.nuxel.adsservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadImage(MultipartFile multipartfile) throws IOException;

    String uploadImageToCurrentFolder(MultipartFile multipartFile, String folderName) throws IOException;
}
