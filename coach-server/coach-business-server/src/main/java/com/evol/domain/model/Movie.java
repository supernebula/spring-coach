package com.evol.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private Integer id;

    private String name;

    private String foreignName;

    private Date releaseDate;

    private Integer minutes;

    private Integer discountCount;

    private Integer discountPrice;

    private String releaseRegion;

    private Integer spaceType;

    private String coverUri;

    private String images;

    private String description;

    private Integer ratings;

    private String language;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName == null ? null : foreignName.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getDiscountCount() {
        return discountCount;
    }

    public void setDiscountCount(Integer discountCount) {
        this.discountCount = discountCount;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getReleaseRegion() {
        return releaseRegion;
    }

    public void setReleaseRegion(String releaseRegion) {
        this.releaseRegion = releaseRegion == null ? null : releaseRegion.trim();
    }

    public Integer getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(Integer spaceType) {
        this.spaceType = spaceType;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri == null ? null : coverUri.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", foreignName=").append(foreignName);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", minutes=").append(minutes);
        sb.append(", discountCount=").append(discountCount);
        sb.append(", discountPrice=").append(discountPrice);
        sb.append(", releaseRegion=").append(releaseRegion);
        sb.append(", spaceType=").append(spaceType);
        sb.append(", coverUri=").append(coverUri);
        sb.append(", images=").append(images);
        sb.append(", description=").append(description);
        sb.append(", ratings=").append(ratings);
        sb.append(", language=").append(language);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}