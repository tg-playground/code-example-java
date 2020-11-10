package com.taogen.demo.springbootcrud.core.filehandling.config;

import com.taogen.demo.springbootcrud.core.filehandling.properites.FileStorageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Taogen
 */
@Configuration
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class FileConfiguration {
}
