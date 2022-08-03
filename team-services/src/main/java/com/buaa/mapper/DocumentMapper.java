package com.buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buaa.pojo.Document;
import com.buaa.pojo.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
public interface DocumentMapper extends BaseMapper<Document> {

    @Insert("insert into document (document_content, document_team_id) values (#{documentContent}, #{documentTeamId})")
    void insertDocument(Document document, int documentTeamId);

    @Select("select * from document where d_id = #{id}")
    Document getDocumentById(int id);

    @Select("select * from document where d_team_id = #{id}")
    List<Document> getDocumentByTeamId(int id);

    @Update("update document set document_content = #{documentContent} where d_id = #{documentId}")
    void updateDocumentContent(int documentId, String documentContent);

    @Delete("delete from document where d_id = #{id}")
    void deleteDocumentById(int id);


}