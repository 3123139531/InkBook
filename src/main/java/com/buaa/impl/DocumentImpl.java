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
    public int createDocument(int pid, String name) {
        Document document = new Document();
        document.setPid(pid);
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

    @Override
    public boolean renameDocument(int did, String name) {
        Document document = new Document();
        document.setDid(did);
        document.setName(name);
        documentMapper.updateDocumentName(document);
        return true;
    }

    @Override
    public List<Document> selectByProject(int pid) {
        return documentMapper.selectByProject(pid);


    }

    @Override
    public Document selectByDid(int did) {
        return documentMapper.selectByDid(did);
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