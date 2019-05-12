package com.application.ttm.shiro.web.vo;

/**
 * 250数据
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/12 14:32</p>
 * <p>@Version 1.0</p>
 */
public class DoubanMovieTop250RsVo {

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String smallImage;

    /**
     * 豆瓣 id
     */
    private Long doubenInTheatersId;

    /**
     * 时间
     */
    private String year;

    /**
     * 星星个数
     */
    private String stars;

    /**
     * 电影评分
     */
    private Integer average;

    /**
     * 原标题
     */
    private String originalTitle;

    public DoubanMovieTop250RsVo() {}

    public DoubanMovieTop250RsVo(String title, String smallImage, Long doubenInTheatersId, String year, String stars, Integer average, String originalTitle) {
        this.title = title;
        this.smallImage = smallImage;
        this.doubenInTheatersId = doubenInTheatersId;
        this.year = year;
        this.stars = stars;
        this.average = average;
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public Long getDoubenInTheatersId() {
        return doubenInTheatersId;
    }

    public void setDoubenInTheatersId(Long doubenInTheatersId) {
        this.doubenInTheatersId = doubenInTheatersId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

}
