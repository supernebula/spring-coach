package com.evol.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBase<T> implements Serializable {
    private static final long serialVersionUID = -4843054288180603924L;

    /**
     * 总页数
     */
    private Long pageTotal;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 当前页号
     */
    private Integer page;

    /**
     * 总记录数
     */
    private Long total=0L;

    /**
     * 数据
     */
    private List records;

    public static <T> PageBase<T> create(Long total,List<T> records) {
        PageBase pageInfo = new PageBase();
        pageInfo.setTotal(total);
        pageInfo.setRecords(records);
        return pageInfo;
    }
}