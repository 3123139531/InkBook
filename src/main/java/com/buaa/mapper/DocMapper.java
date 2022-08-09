package com.buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buaa.pojo.Doc;
import com.buaa.pojo.Document;
import org.apache.ibatis.annotations.*;


@Mapper
public interface DocMapper extends BaseMapper<Doc> {
    @Insert("insert into doc (d_name,d_fid,d_tid) values (#{dName},#{dFid},#{dTid})")
    void insertDoc(Doc doc);
    @Options(useGeneratedKeys = true,keyProperty = "docid")

    @Select("select * from doc where d_name = #{dName} and d_tid = #{dTid}")
    Doc selectByName(Doc doc);

    @Update("update doc set d_name = #{dName} where doc_id = #{docid}")
    void renameDoc(Doc doc);

}
