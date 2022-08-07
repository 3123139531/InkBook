package com.buaa.pojo;

import com.buaa.pojo.Document;
import lombok.Data;

import java.util.List;


@Data
public class File {
    private int fid;
    private int docNUm;
    private String fName;
    private List<Document> documents;
}
