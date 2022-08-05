package com.buaa.pojo;

import lombok.Data;

@Data
public class Document {
    private int did;
//    private int tid;
    private int pid;
    private String dName;
    private String dContent;
}
