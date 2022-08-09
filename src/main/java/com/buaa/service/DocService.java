package com.buaa.service;

public interface DocService {

    int createDoc(int dfid,int dtid, String name);
    boolean checkNameRepeat(int dtid, String name);
    boolean deleteDocById(int docid);
    boolean renameDoc(int docid,String name);
}
