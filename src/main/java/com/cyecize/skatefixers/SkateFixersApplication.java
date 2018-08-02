package com.cyecize.skatefixers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.swing.*;

@SpringBootApplication
public class SkateFixersApplication {

    @Value("${server.port}")
    private String port;

    @PostConstruct
    public void onStartup(){
        Timer timer = new Timer(2000, e -> {
            System.out.println(String.format("Listening:http://localhost:%s", port));
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(SkateFixersApplication.class, args);
    }
}
