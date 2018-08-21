package com.cyecize.skatefixers.config;

import com.cyecize.skatefixers.constants.WebConstants;
import com.cyecize.skatefixers.util.ModelMerger;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Configuration
public class BeanConfig {

    private static final JsonFactory GOODLE_DRIVE_JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final List<String> GOOGLE_DRIVE_AUTH_SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    private static final String GOOGLE_DRIVE_CREDENTIALS_DIR = WebConstants.SERVER_ROOT_FOLDER_PATH + "credentials.json";

    private static final String GOOGLE_DRIVE_TOKENS_SAVE_LOCATION = WebConstants.SERVER_ROOT_FOLDER_PATH + "google_drive_credentials";


    @Bean
    public ModelMerger modelMerger() {return new ModelMerger();}

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("cyecize@gmail.com");
        mailSender.setPassword("v1tal1t1");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


    @Bean
    public Drive getGoogleDriveService() throws GeneralSecurityException, IOException {
        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(transport, GOODLE_DRIVE_JSON_FACTORY, getCredentials(transport))
                .setApplicationName(WebConstants.APPLICATION_NAME)
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        File file = new File(GOOGLE_DRIVE_CREDENTIALS_DIR);
        InputStream in = new FileInputStream(file);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GOODLE_DRIVE_JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, GOODLE_DRIVE_JSON_FACTORY, clientSecrets, GOOGLE_DRIVE_AUTH_SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(
                                new java.io.File(GOOGLE_DRIVE_TOKENS_SAVE_LOCATION)
                        )
                )
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
}
