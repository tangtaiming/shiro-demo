package com.application.ttm.entity;

/**
 * 豆瓣电影
 */
public class DoubanIntheaters {

    private Long id;    //编号
    private String title;   //标题
    private String smallImage;  //图片
    private Long doubenIntheatersId;    //豆瓣电影id
    private String year;    //年份
    private String stars;   //星级
    private Integer average;    //xx

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDoubenIntheatersId() {
        return doubenIntheatersId;
    }

    public void setDoubenIntheatersId(Long doubenIntheatersId) {
        this.doubenIntheatersId = doubenIntheatersId;
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
}
