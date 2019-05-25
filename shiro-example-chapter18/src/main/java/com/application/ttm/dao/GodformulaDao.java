package com.application.ttm.dao;

import com.application.ttm.entity.Godformula;

import java.util.List;

/**
 * mysql 式神数据持久
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/10 23:07</p>
 * <p>@Version 1.0</p>
 */
public interface GodformulaDao {

    public Godformula createGodformula(Godformula godformula);
    public Godformula updateGodformula(Godformula godformula);
    public boolean deleteGodformula(Integer id);

    public Godformula findOne(Integer id);
    public List<Godformula> findAll();
    public List<Godformula> findList(int first, int pageSize);
    int count();

}
