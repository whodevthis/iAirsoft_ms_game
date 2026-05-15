package org.project.msgame;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGameApplication.class, args);
    }

}
