package com.application.ttm.web.controller;

import com.application.ttm.ResponseUtils;
import com.application.ttm.entity.Godformula;
import com.application.ttm.service.GodformulaService;
import com.application.ttm.service.impl.GodformulaServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/10 23:47</p>
 * <p>@Version 1.0</p>
 */
@Controller
@RequestMapping("/godformula")
public class GodformulaController {

    @Autowired
    private GodformulaService godformulaService;

    @RequiresPermissions("godformula:view")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(Model model, @RequestParam Map<String, Object> param) {
        List<Godformula> godformulas = godformulaService.findList(param);
        model.addAttribute("godformulaList", godformulas);
        model.addAttribute("pageNum", godformulaService.getPageNum(param));
        model.addAttribute("numPerPage", godformulaService.getNumPerPage(param));
        model.addAttribute("totalCount", godformulaService.count());
        return "/godformula/list";
    }

    @RequiresPermissions("godformula:updateDistributed")
    @RequestMapping(value = "/{id}/updateDistributed", method = RequestMethod.GET)
    public String updateDistributedShow(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("op", "修改");
        Godformula godformula = godformulaService.findOne(id);
        model.addAttribute("godformula", godformula);
        model.addAttribute("distributionDetails", HtmlUtils.htmlUnescape(godformula.getDistributionDetails()));
        return "/godformula/updateDistributed";
    }

    /**
     * 保存式神描述
     * @param distributionDetails
     * @return
     */
    @RequiresPermissions("godformula:updateDistributed")
    @RequestMapping(value = "/{id}/updateDistributed", method = RequestMethod.POST)
    @ResponseBody
    public String updateDistributed(@PathVariable(value = "id") Integer id, @RequestParam(value = "distributionDetails") String distributionDetails) {
        System.out.println(id + " detail: " + distributionDetails);
        ((GodformulaServiceImpl) godformulaService).updateGodformulaDistributionDetails(id, distributionDetails);

        return ResponseUtils.successByDialogCloseCurrent();
    }

}
