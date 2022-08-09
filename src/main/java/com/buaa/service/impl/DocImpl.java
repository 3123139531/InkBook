package com.buaa.service.impl;

import com.buaa.pojo.Doc;
import com.buaa.service.DocService;
import com.buaa.mapper.DocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DocImpl implements DocService {


    @Autowired
    private DocMapper docMapper;

    @Override
    public int createDoc(int dfid, String name) {
        Doc doc = new Doc();
        doc.setDFid(dfid);
        doc.setDName(name);
        docMapper.insert(doc);
        doc = docMapper.selectByName(name);
        return doc.getDocid();
    }
}
