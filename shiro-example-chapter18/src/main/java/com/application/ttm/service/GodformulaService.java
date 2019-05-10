package com.application.ttm.service;

import com.application.ttm.entity.Godformula;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/10 23:43</p>
 * <p>@Version 1.0</p>
 */
public interface GodformulaService {

    public Godformula createGodformula(Godformula godformula);
    public Godformula updateGodformula(Godformula godformula);
    public boolean deleteGodformula(Integer id);

    public Godformula findOne(Integer id);
    public List<Godformula> findAll();

}
