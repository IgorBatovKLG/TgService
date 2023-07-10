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
    private final DownloadImg downloadImg;

    public ImgService(TgRepository tgRepository, DownloadImg downloadImg) {
        this.tgRepository = tgRepository;
        this.downloadImg = downloadImg;
    }

    public List<Integer> getListChannelByCategory(int idCategory){
        return tgRepository.getListChannelIdByCategory(idCategory);
    }

    public void addUrlImgTgToBd(List<Integer> tgChannel, int idCategory){
        for (Integer integer : tgChannel) {
            int countRecord = tgRepository.getCountRecord(integer)+1;
            boolean isAdd = true;
            int countfalse = 0;
            String channelUrlById = tgRepository.getChannelUrlById(integer);
            while (isAdd){
                try {
                    String urlImgTelegram = getUrlImgTelegram(channelUrlById, countRecord);
                    tgRepository.updateCountRecord(integer, countRecord++);
                    downloadImg.downloadImgByUrl(urlImgTelegram);
                    tgRepository.addNameImgTgToBd("Image"+(tgRepository.getCountImg()+1)+".jpg", idCategory);
                    countRecord++;
                }catch (Exception e){
                    System.out.println("Ошибка " + countRecord);
                    countRecord++;
                    if (countfalse>500){
                        isAdd = false;
                    }
                    countfalse++;
                }
            }
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
