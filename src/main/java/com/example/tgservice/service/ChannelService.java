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
}
