package com.application.ttm.entity;

/**
 * 式神表
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-10</p>
 * <p>@Version 1.0</p>
 **/
public class Godformula {

    private Integer id;

    /**
     * 式神名称
     */
    private String name;

    /**
     * 稀有度
     */
    private Integer rarity;

    /**
     * 图片
     */
    private String image;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 式神id 网易唯一标识
     */
    private Integer godformulaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getGodformulaId() {
        return godformulaId;
    }

    public void setGodformulaId(Integer godformulaId) {
        this.godformulaId = godformulaId;
    }
}
