package com.application.ttm.service.impl;

import com.application.ttm.PageUtils;
import com.application.ttm.dao.GodformulaDao;
import com.application.ttm.entity.Godformula;
import com.application.ttm.service.GodformulaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Godformula> findList(Map<String, Object> param) {
        int pageSize = PageUtils.getNumPerPage(param);
        int first = (PageUtils.getPageNum(param) - 1) * pageSize;

        return godformulaDao.findList(first, pageSize);
    }

    @Override
    public int count() {
        return godformulaDao.count();
    }

    @Override
    public int getPageNum(Map<String, Object> param) {
        return PageUtils.getPageNum(param);
    }

    @Override
    public int getNumPerPage(Map<String, Object> param) {
        return PageUtils.getNumPerPage(param);
    }

    /**
     * 修改 式神分布详情
     * @param id
     * @param distributionDetails
     * @return
     */
    public Godformula updateGodformulaDistributionDetails(Integer id, String distributionDetails) {
        Godformula godformula = findOne(id);
        if (null == godformula) {
            return null;
        } else {
            godformula.setDistributionDetails(StringUtils.trim(distributionDetails));
            return updateGodformula(godformula);
        }
    }

}
