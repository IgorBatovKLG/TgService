package com.example.tgservice.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ImgService {

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
