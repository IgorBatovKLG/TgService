package com.example.tgservice.service;

import com.example.tgservice.repository.TgRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImgService {

    private final TgRepository tgRepository;

    public ImgService(TgRepository tgRepository) {
        this.tgRepository = tgRepository;
    }

    public List<Integer> getListChannelByCategory(int idCategory){
        return tgRepository.getListChannelByCategory(idCategory);
    }

    public void addUrlImgTgToBd(List<Integer> tgChannel){
        for (Integer integer : tgChannel) {
            //запрос последнего поста в тг

            //загружаем фором ссылки на картинки до экзепшена

            //отправляем картинки в бд
        }
    }


    public String getUrlImgTelegram(String tgChannel, int id){
        String url = "https://t.me/"+tgChannel+"/"+id+"?embed=1&mode=tme";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements tgmeWidgetMessagePhotoWrap = doc.getElementsByClass("tgme_widget_message_photo_wrap");
        String s = tgmeWidgetMessagePhotoWrap.get(0).toString();
        String[] split = s.split("background-image:url");
        String[] split1 = split[1].split("\">");
        String urlImg = split1[0].replace("('", "");
        urlImg = urlImg.replace("')", "");
        return urlImg;
    }
}
