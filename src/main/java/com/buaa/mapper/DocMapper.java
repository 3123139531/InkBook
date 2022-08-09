package com.buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buaa.pojo.Doc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface DocMapper extends BaseMapper<Doc> {
    @Insert("insert into doc (d_name,d_fid) values (#{dName},#{dFid})")
    void insertDoc(Doc doc);

    @Select("select * from doc where d_name = #{dName}")
    Doc selectByName(String dName);
}
