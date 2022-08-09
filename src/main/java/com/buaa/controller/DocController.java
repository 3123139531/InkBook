package com.buaa.controller;


import com.buaa.controller.utils.R;
import com.buaa.pojo.Doc;
import com.buaa.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docs")
@CrossOrigin

public class DocController {
    @Autowired
    private DocService docService;


    @PostMapping
    public R createDoc(@RequestBody Doc doc) {
        int dfid = doc.getDFid();
        String name = doc.getDName();
        return new R(true, docService.createDoc(dfid, name), "新文档创建成功！");
    }
}
