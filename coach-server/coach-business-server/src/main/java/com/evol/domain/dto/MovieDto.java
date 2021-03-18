package com.evol.domain.dto;

import lombok.Data;

@Data
public class MovieDto {

    private Integer id;

    private String name;

    private String foreignName;

    private Integer minutes;

    private Integer discountPrice;

    private Integer spaceType;

    private String coverUri;

    private String images;

}
