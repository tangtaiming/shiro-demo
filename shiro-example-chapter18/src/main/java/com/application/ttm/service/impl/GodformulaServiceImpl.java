package com.application.ttm.service.impl;

import com.application.ttm.dao.GodformulaDao;
import com.application.ttm.entity.Godformula;
import com.application.ttm.service.GodformulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/10 23:44</p>
 * <p>@Version 1.0</p>
 */
@Service
public class GodformulaServiceImpl implements GodformulaService {

    @Autowired
    private GodformulaDao godformulaDao;

    @Override
    public Godformula createGodformula(Godformula godformula) {
        return godformulaDao.createGodformula(godformula);
    }

    @Override
    public Godformula updateGodformula(Godformula godformula) {
        return godformulaDao.updateGodformula(godformula);
    }

    @Override
    public boolean deleteGodformula(Integer id) {
        return godformulaDao.deleteGodformula(id);
    }

    @Override
    public Godformula findOne(Integer id) {
        return godformulaDao.findOne(id);
    }

    @Override
    public List<Godformula> findAll() {
        return godformulaDao.findAll();
    }

}
