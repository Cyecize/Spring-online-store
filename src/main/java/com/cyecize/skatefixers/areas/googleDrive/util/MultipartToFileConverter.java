package com.cyecize.skatefixers.areas.googleDrive.util;

import com.cyecize.skatefixers.exceptions.JsonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class MultipartToFileConverter {
    public static File convert(MultipartFile multipartFile){
        try {
            File file = File.createTempFile("temp-file", multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new JsonException("Error uploading file!", new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
