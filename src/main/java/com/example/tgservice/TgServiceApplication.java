package com.example.tgservice;

import com.example.tgservice.repository.TgRepository;
import com.example.tgservice.service.DownloadImg;
import com.example.tgservice.service.ImgService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class TgServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TgServiceApplication.class, args);

    }

}
