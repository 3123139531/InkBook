package com.buaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buaa.mapper.PageMapper;
import com.buaa.pojo.MyPage;
import com.buaa.service.PageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PageServiceImpl implements PageService {

    @Resource
    PageMapper pageMapper;

    @Override
    public void createPage(MyPage page) {
        pageMapper.createPage(page);
    }

    @Override
    public void deletePage(int pgId) {
        pageMapper.deletePage(pgId);
    }

    @Override
    public boolean updateName(int pgId, String name) {
        MyPage page = new MyPage();
        page.setPgPid(pgId);
        page.setPgName(name);
        pageMapper.renameById(page);
        return true;
    }

    @Override
    public boolean updateContent(int pgId, String content) {
        MyPage page = new MyPage();
        page.setPgPid(pgId);
        page.setPgContent(content);
        pageMapper.updateById(page);
        return true;
    }

    @Override
    public boolean checkNameRepeat(String name, int pgPid) {
        MyPage page = new MyPage();
        page.setPgName(name);
        page.setPgPid(pgPid);
        return pageMapper.selectPageByName(page) != null;
    }

    @Override
    public MyPage getPageById(int id) {
        return pageMapper.selectPageById(id);
    }

    @Override
    public MyPage getPageByName(String name, int pgPid) {
        MyPage page = new MyPage();
        page.setPgName(name);
        page.setPgPid(pgPid);
        return pageMapper.selectPageByName(page);
    }
}
