package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.service.AuthService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        authService.validateAdmin();
        File imageFile = convertMultipartToFile(multipartFile);
        var response = cloudinary.uploader().upload(imageFile, ObjectUtils.asMap("use_filename", true, "unique_filename", true));
        boolean isDeleted = imageFile.delete();
        log.info(isDeleted ? "File uploaded and deleted" : "File does not exist");
        return response.get("secure_url").toString();
    }

    private File convertMultipartToFile(MultipartFile imageFile) throws IOException {
        String file = imageFile.getOriginalFilename();
        assert file != null;
        File convertFile = new File(file);
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(imageFile.getBytes());
        fileOutputStream.close();
        return convertFile;
    }
}
