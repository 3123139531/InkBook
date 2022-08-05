package com.buaa.impl;

import com.buaa.mapper.DocumentMapper;
import com.buaa.pojo.Document;
import com.buaa.service.DocumentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public int createDocument(int tid, String name) {
        Document document = new Document();
        document.setTid(tid);
        document.setName(name);
        documentMapper.insertDocument(document);
        document = documentMapper.getDocumentByName(name);
        return document.getDid();
    }

    @Override
    public boolean setDocumentContent(int did, String content) {
        Document document = new Document();
        document.setDid(did);
        document.setContent(content);
        documentMapper.updateDocumentContent(document);
        return true;
    }
//
//    @Override
//    public void deleteDocumentById(int id) {
//        documentMapper.deleteDocumentById(id);
//    }
//
//    @Override
//    public void updateDocumentContent(int id, String content) {
//        documentMapper.updateDocumentContent(id, content);
//    }
//
//    @Override
//    public Document selectById(int id) {
//        return documentMapper.getDocumentById(id);
//    }
//
//    @Override
//    public List<Document> selectByTeam(int tid) {
//        return documentMapper.getDocumentByTeamId(tid);
//    }
}