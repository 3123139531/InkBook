package com.buaa.pojo;

import lombok.Data;

@Data
public class Document {
    private int documentId;
    private String documentContent;
    private int documentTeamId;
}
