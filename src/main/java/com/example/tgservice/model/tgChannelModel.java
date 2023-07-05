package com.example.tgservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tgChannelModel {

    String url;
    int category;
    int countRecord;
}
