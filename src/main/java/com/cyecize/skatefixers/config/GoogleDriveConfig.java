package com.cyecize.skatefixers.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.Collections;

@Component
public class GoogleDriveConfig {
    private final String googleDriveToken;

    private final String googleDriveAppName;

    public GoogleDriveConfig(
            @Value("${google.drive.auth.token}") String googleDriveToken,
            @Value("${google.drive.application.name}") String googleDriveAppName) {
        this.googleDriveToken = googleDriveToken;
        this.googleDriveAppName = googleDriveAppName;
    }

    @Bean
    public Drive drive() throws Exception {
        return this.getDriveService();
    }

    private Drive getDriveService() throws Exception {

        final HttpTransport httpTransport = new NetHttpTransport();
        final JacksonFactory jsonFactory = new JacksonFactory();
        final GoogleCredential credential = GoogleCredential
                .fromStream(new ByteArrayInputStream(this.googleDriveToken.getBytes()))
                .createScoped(Collections.singletonList(DriveScopes.DRIVE));

        return new Drive.Builder(httpTransport, jsonFactory, null)
                .setApplicationName(this.googleDriveAppName)
                .setHttpRequestInitializer(credential).build();
    }
}
