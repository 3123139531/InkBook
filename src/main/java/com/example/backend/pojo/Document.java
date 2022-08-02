package com.example.backend.pojo;
import javax.persistence.*;


/**
 * 类 {@code Document} 文档记录层
 * <p>主要用于定义数据库中的文档表</p>
 * @ClassName Document
 */
public class Document {
    /** documentID 文档ID，为自增主键 */
    @Id //标明documentID为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键自增
    private int documentID;
    /**
     * documentContent 文档内容
     * documentUser 文档用户
     */
    private String documentContent;
    private String documentUser;
    private int doucuentUserID;
    private String documentTitle;

    public Document(){}

    /**
     * Document类的构造方法，用于创建实例.
     * @param documentContent 文档内容
     * @param documentUser 文档用户
     */
    public Document(String documentContent, String documentUser) {
        this.documentContent = documentContent;
        this.documentUser = documentUser;
    }

    /**
     * 通过getDocumentContent()获取文档内容
     * @return String类型 文档内容
     */
    public String getDocumentContent() {
        return documentContent;
    }

    /**
     * 通过setDocumentContent()设置文档内容
     * @param documentContent 文档内容
     */
    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    /**
     * 通过getDocumentUser()获取文档用户
     * @return String类型 文档用户
     */
    public String getDocumentUser() {
        return documentUser;
    }

    /**
     * 通过setDocumentUser()设置文档用户
     * @param documentUser 文档用户
     */
    public void setDocumentUser(String documentUser) {
        this.documentUser = documentUser;
    }


}
