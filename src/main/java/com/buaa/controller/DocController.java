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
        int dtid = doc.getDTid();
        String name = doc.getDName();
        if(docService.checkNameRepeat(dtid, name))
            return new R(false, "组内已有同名文档，请改名！");
        return new R(true, docService.createDoc(dfid,dtid, name), "新文档创建成功！");
    }

    @PutMapping("/name")
    public R renameDoc(@RequestBody Doc doc) {
        return new R(docService.renameDoc(doc.getDocid(),doc.getDName()),null,"文档已经重命名");
    }


    @DeleteMapping("/{docid}")
    public R deleteDoc(@PathVariable int docid){
        return new R(docService.deleteDocById(docid),"null","文档删除成功");
    }

}
