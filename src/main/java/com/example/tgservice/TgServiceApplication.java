package com.example.tgservice;

import com.example.tgservice.repository.TgRepository;
import com.example.tgservice.service.ChannelService;
import com.example.tgservice.service.DownloadImg;
import com.example.tgservice.service.ImgService;
import com.example.tgservice.service.TelegramBotUploader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class TgServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TgServiceApplication.class, args);
        List<Integer> listChannelByCategory = run.getBean(ImgService.class).getListChannelByCategory(1);
        run.getBean(ImgService.class).addUrlImgTgToBd(listChannelByCategory, 1);
         //run.getBean(TelegramBotUploader.class).imgUpdate("Image47.jpg");
        //run.getBean(ChannelService.class).updateImageToTgChannel("@vbsdkjvnjsdk", 1);
    }

}
