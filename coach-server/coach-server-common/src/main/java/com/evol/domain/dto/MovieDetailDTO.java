package com.evol.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovieDetailDTO {

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

}
