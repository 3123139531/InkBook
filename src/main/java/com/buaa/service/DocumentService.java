package com.buaa.service;

import com.buaa.pojo.Document;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DocumentService {
    public void createDocument(int tid, String content);
    void deleteDocumentById(int id);
    void updateDocumentContent(int id, String content);
    Document selectById(int id);
    List<Document> selectByTeam(int tid);

}
