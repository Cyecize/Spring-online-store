package com.cyecize.skatefixers.areas.googleDrive.services;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveManagerImpl implements GoogleDriveManager {

    private static final String MAIN_FOLDER_ID = "1t5yGW7Eo31aXD90cyzcDKGkkfx8Wa5wJ";

    private final Drive driveService;

    @Autowired
    public GoogleDriveManagerImpl(Drive driveService) {
        this.driveService = driveService;
    }

    @Override
    public List<File> findAllFilesByFolder(String folderId) {
        try {
            FileList result = this.driveService.files().list()
                    .setQ(String.format("'%s' in parents", folderId))
                    .setFields("nextPageToken, files")
                    .execute();
            List<File> files = result.getFiles();
            if (files == null)
                new ArrayList<>();
            return files;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public File findFileById(String id) {
        try {
            return this.driveService.files().get(id).execute();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String uploadFile(java.io.File file, String folderId, String fileName) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setParents(Collections.singletonList(folderId));
        FileContent mediaContent = new FileContent("image/jpeg", file);
        File googleFile = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id, parents")
                .execute();
        return googleFile.getId();
    }

}
