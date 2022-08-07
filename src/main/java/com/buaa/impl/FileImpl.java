package com.buaa.impl;


import com.buaa.pojo.File;
import com.buaa.mapper.FileMapper;
import com.buaa.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public int createFile(String name) {
        File file = new File();
        file.setFName(name);
        fileMapper.insertFile(file);
        return file.getFid();
    }

}
