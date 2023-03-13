package cz.mpsv.filebox;

import cz.mpsv.filebox.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class FileboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileboxApplication.class, args);
    }

}
