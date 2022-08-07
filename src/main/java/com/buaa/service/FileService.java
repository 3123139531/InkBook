package com.buaa.service;


import org.springframework.stereotype.Service;

@Service
public interface FileService {
    int createFile(String name);
}
