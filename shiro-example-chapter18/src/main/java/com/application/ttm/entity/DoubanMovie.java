package com.application.ttm.entity;


import javax.persistence.*;

/**
 * 豆瓣电影
 */
@Entity
@Table(name = "sys_douban_movie")
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

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "small_image")
    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @Column(name = "douben_intheaters_id")
    public Long getDoubenIntheatersId() {
        return doubenIntheatersId;
    }

    public void setDoubenIntheatersId(Long doubenIntheatersId) {
        this.doubenIntheatersId = doubenIntheatersId;
    }

    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "stars")
    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    @Column(name = "average")
    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    @Column(name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "creator")
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Column(name = "original_title")
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(name = "intheaters")
    public int getIntheaters() {
        return intheaters;
    }

    public void setIntheaters(int intheaters) {
        this.intheaters = intheaters;
    }

    @Column(name = "top250")
    public int getTop250() {
        return top250;
    }

    public void setTop250(int top250) {
        this.top250 = top250;
    }
}
