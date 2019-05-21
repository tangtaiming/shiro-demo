package com.application.ttm.entity;

/**
 * 豆瓣电影
 */
public class DoubanMovie {

    private Long id;    //编号
    private String title;   //标题
    private String smallImage;  //图片
    private Long doubenIntheatersId;    //豆瓣电影id
    private String year;    //年份
    private String stars;   //星级
    private Integer average;    //xx
    private String createDate;  //创建时间
    private Long creator;   //创建人
    private String originalTitle;   //原标题
    private String summary; //详情
    private int intheaters; //热映
    private int top250; //top250

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIntheaters() {
        return intheaters;
    }

    public void setIntheaters(int intheaters) {
        this.intheaters = intheaters;
    }

    public int getTop250() {
        return top250;
    }

    public void setTop250(int top250) {
        this.top250 = top250;
    }
}
