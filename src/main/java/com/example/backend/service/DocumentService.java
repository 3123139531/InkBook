package com.example.backend.service;

import com.example.backend.mapper.DocumentMapper;
import com.example.backend.pojo.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类 {@code DocumentService} 文档服务层.
 * <p>主要用于定义数据库中的文档表</p>
 */
@Service
public class DocumentService{
    @Autowired
    private DocumentMapper documentMapper;

    public Document selectDocumentByDocumentID(int documentID){
        return documentMapper.selectDocumentByDocumentID(documentID);
    }

    public int insertDocument(Document document){
        return documentMapper.insertDocument(document);
    }

    public int updateDocument(Document document){
        return documentMapper.updateDocument(document);
    }

    public int deleteDocument(int documentID){
        return documentMapper.deleteDocument(documentID);
    }


}
