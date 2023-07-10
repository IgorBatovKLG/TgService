package com.example.tgservice.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

@Service
public class TelegramBotUploader {
    private static final String BOT_TOKEN = "5996665327:AAEH4kdUgeoCQfpuphWfxXa2eJr5vsOMzHU";

    public void imgUpdate(String name, String CHANNEL_CHAT_ID) {
        String photoPath = "E:/Img/Image"+name + ".jpg";
        String apiUrl = String.format("https://api.telegram.org/bot%s/sendPhoto", BOT_TOKEN);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            String boundary = "------------------------" + System.currentTimeMillis();
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            File photoFile = new File(photoPath);
            byte[] photoBytes = Files.readAllBytes(photoFile.toPath());

            StringBuilder requestBody = new StringBuilder();
            requestBody.append("--").append(boundary).append("\r\n")
                    .append("Content-Disposition: form-data; name=\"chat_id\"\r\n\r\n")
                    .append(CHANNEL_CHAT_ID).append("\r\n")
                    .append("--").append(boundary).append("\r\n")
                    .append("Content-Disposition: form-data; name=\"photo\"; filename=\"").append(photoFile.getName()).append("\"\r\n")
                    .append("Content-Type: image/jpeg\r\n\r\n");

            byte[] requestBodyBytes = requestBody.toString().getBytes();
            byte[] boundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes();

            connection.getOutputStream().write(requestBodyBytes);
            connection.getOutputStream().write(photoBytes);
            connection.getOutputStream().write(boundaryBytes);

            int responseCode = connection.getResponseCode();
            // Обработка ответа (если необходимо)

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки
        }
    }
}
