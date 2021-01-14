package com.taogen.example.cas.crm;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Taogen
 */
@SpringBootApplication
@EnableCasClient
public class CrmSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmSysApplication.class, args);
    }
}
