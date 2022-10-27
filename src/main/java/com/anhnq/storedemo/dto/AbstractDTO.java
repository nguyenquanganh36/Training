package com.anhnq.storedemo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AbstractDTO {

    private Long id;

    private String createBy;

    private Date createDate;

    private String modifiedBy;

    private Date modifiedDate;
}
