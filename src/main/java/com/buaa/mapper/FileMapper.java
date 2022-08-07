package com.buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buaa.pojo.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    @Insert("insert into file (f_name) values (#{fName})")
    @Options(useGeneratedKeys = true, keyProperty = "fid")
    void insertFile(File file);
}
