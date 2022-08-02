package com.example.backend.mapper;

import com.example.backend.pojo.Document;

/**
 * 类 {@code DocumentMapper} 文档映射层.
 * <p>主要用于定义数据库中的文档表</p>
 * @ClassName DocumentMapper
 */
public interface DocumentMapper {
    Document selectDocumentByDocumentID(int documentID);
    int insertDocument(Document document);
    int updateDocument(Document document);
    int deleteDocument(int documentID);
}
