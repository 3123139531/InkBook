package com.buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buaa.pojo.Document;
import com.buaa.pojo.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
public interface DocumentMapper extends BaseMapper<Document> {

    @Insert("insert into document (t_id, content) values (#{tid}, #{content})")
    void insertDocument(Document document);

    @Select("select * from document where d_name = #{name}")
    Document getDocumentByName(String name);
}