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
    public void createDocument(int tid, String content) {
        Document document = new Document();
        document.setDocumentTeamId(tid);
        document.setDocumentContent(content);
        documentMapper.insertDocument(document, tid);
    }

    @Override
    public void deleteDocumentById(int id) {
        documentMapper.deleteDocumentById(id);
    }

    @Override
    public void updateDocumentContent(int id, String content) {
        documentMapper.updateDocumentContent(id, content);
    }

    @Override
    public Document selectById(int id) {
        return documentMapper.getDocumentById(id);
    }

    @Override
    public List<Document> selectByTeam(int tid) {
        return documentMapper.getDocumentByTeamId(tid);
    }
}
