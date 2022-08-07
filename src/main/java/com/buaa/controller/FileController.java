package com.buaa.controller;


import com.buaa.controller.utils.R;
import com.buaa.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/createFile")
    public R createFile(String name) {
        int fid = fileService.createFile(name);
        return new R(true, fid, "新文件夹创建成功！");
    }
}
