package com.application.ttm.shiro.entity;

/**
 * 豆瓣电影
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-09</p>
 * <p>@Version 1.0</p>
 **/
public class DoubanMovie {

    private Integer id;

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
     * 创建时间
     */
    private String createDate;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 原标题
     */
    private String originalTitle;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 热映 0 代表不是热映 / 1 代表热映
     */
    private int intheaters;

    /**
     * top 250 0 代表不是top 250 / 1 代表热映 top 250
     */
    private int top250;

    public DoubanMovie() {}

//    doubanMovie.setTitle(intheatersRsVo.getTitle());
//            doubanMovie.setDoubenInTheatersId(intheatersRsVo.getDoubenInTheatersId());
//            doubanMovie.setAverage(intheatersRsVo.getAverage());
//            doubanMovie.setOriginalTitle(intheatersRsVo.getOriginalTitle());
//            doubanMovie.setSmallImage(intheatersRsVo.getSmallImage());
//            doubanMovie.setStars(intheatersRsVo.getStars());
//            doubanMovie.setYear(intheatersRsVo.getYear());


    /**
     *
     * @param title
     * @param smallImage
     * @param doubenInTheatersId
     * @param year
     * @param stars
     * @param average
     * @param originalTitle
     */
    public DoubanMovie(String title, String smallImage, Long doubenInTheatersId, String year, String stars, Integer average, String originalTitle) {
        this.title = title;
        this.smallImage = smallImage;
        this.doubenInTheatersId = doubenInTheatersId;
        this.year = year;
        this.stars = stars;
        this.average = average;
        this.originalTitle = originalTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "DoubanMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", smallImage='" + smallImage + '\'' +
                ", doubenInTheatersId=" + doubenInTheatersId +
                ", year='" + year + '\'' +
                ", stars='" + stars + '\'' +
                ", average=" + average +
                ", createDate='" + createDate + '\'' +
                ", creator=" + creator +
                ", originalTitle='" + originalTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", intheaters=" + intheaters +
                ", top250=" + top250 +
                '}';
    }
}
