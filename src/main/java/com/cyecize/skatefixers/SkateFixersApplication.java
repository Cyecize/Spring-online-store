package com.cyecize.skatefixers;

import com.cyecize.skatefixers.areas.products.entities.Brand;
import com.cyecize.skatefixers.areas.users.entities.UserRole;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import com.cyecize.skatefixers.areas.users.repositories.RoleRepository;
import com.cyecize.skatefixers.util.ModelMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.management.relation.Role;
import javax.swing.*;
import java.util.Scanner;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SkateFixersApplication {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMerger modelMerger;


    @Value("${server.port}")
    private String port;

    @PostConstruct
    public void onStartup(){
        Timer timer = new Timer(2000, e -> {
            System.out.println(String.format("Listening:http://localhost:%s", port));
        });
        timer.setRepeats(false);
        timer.start();
        this.initRoles();
    }

    public static void main(String[] args) {
        SpringApplication.run(SkateFixersApplication.class, args);
    }

    private void initRoles(){
        if(this.roleRepository.findAll().size() < 1){
            UserRole role1 = new UserRole();
            UserRole role2 = new UserRole();
            UserRole role3 = new UserRole();

            role1.setAuthority(UserRoleType.ROLE_ADMIN);
            role2.setAuthority(UserRoleType.ROLE_WORKER);
            role3.setAuthority(UserRoleType.ROLE_USER);

            this.roleRepository.save(role1);
            this.roleRepository.save(role2);
            this.roleRepository.save(role3);
            this.roleRepository.flush();
        }
    }
}
