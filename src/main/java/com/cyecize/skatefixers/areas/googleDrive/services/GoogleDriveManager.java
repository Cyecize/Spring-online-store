package com.cyecize.skatefixers.areas.googleDrive.services;

import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.List;

public interface GoogleDriveManager {

    List<File> findAllFilesByFolder(String folderId);

    File findFileById(String id);

    String uploadFile(java.io.File file, String folderId, String fileName) throws IOException;
}
