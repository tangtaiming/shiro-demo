package com.application.ttm.service;

import com.application.ttm.entity.Godformula;

import java.util.List;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/10 23:43</p>
 * <p>@Version 1.0</p>
 */
public interface GodformulaService extends PageService {

    public Godformula createGodformula(Godformula godformula);
    public Godformula updateGodformula(Godformula godformula);
    public boolean deleteGodformula(Integer id);

    public Godformula findOne(Integer id);
    public List<Godformula> findAll();
    public List<Godformula> findList(Map<String, Object> param);
    public int count();

}
