package com.example.tgservice.service;

import com.example.tgservice.repository.TgRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private final TgRepository tgRepository;
    private final TelegramBotUploader telegramBotUploader;

    public ChannelService(TgRepository tgRepository, TelegramBotUploader telegramBotUploader) {
        this.tgRepository = tgRepository;
        this.telegramBotUploader = telegramBotUploader;
    }

    //@vbsdkjvnjsdk
    public void updateImageToTgChannel(String channel, int category){
        List<String> list = tgRepository.getImgNames(category, 1);
        telegramBotUploader.imgUpdate(list.get(0), channel);
        tgRepository.updateOffImg(Integer.parseInt(list.get(0)));

    }

    public void whileChannelService(){
        while (true){
            /*
            1.сервис который загружает картинки
            2.сервис добавляет записи на канал
                2.1 загружает список каналов по категориям
                2.2 выбирает канал
                2.3 подгружает картинку под канал
                2.4 отправляет картинку на канал
                2.5 зануляем картинку
             */
        }
    }
}
