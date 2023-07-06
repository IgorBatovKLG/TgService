package com.example.tgservice.service;

import com.example.tgservice.repository.TgRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
@Service
public class DownloadImg {

    private final TgRepository tgRepository;

    public DownloadImg(TgRepository tgRepository) {
        this.tgRepository = tgRepository;
    }


    public void downloadImgByUrl(String imageUrl){
        {
            String savePath = "E:/Img/";
            String fileName = "Image"+(tgRepository.getCountImg()+1)+".jpg";

            try {
                URL url = new URL(imageUrl);

                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();

                Path outputPath = Path.of(savePath, fileName);

                Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);

                inputStream.close();

                System.out.println("Изображение успешно скачано и сохранено.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
