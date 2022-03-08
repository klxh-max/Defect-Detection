package com.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@ApiModel("传入的历史数据查询要求")
@Data
@AllArgsConstructor
public class Statistics {

    @ApiModelProperty("主板编号")
    private Integer mainboardId;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("主板型号")
    private String mainboardModel;
    @ApiModelProperty("检测编号（缺陷数据编号）")
    private Integer defectId;
    @ApiModelProperty("检测人工号")
    private Integer directorId;
    @ApiModelProperty("检测方式")
    private String detectionType;
    @ApiModelProperty("检测结果（合格情况）")
    private String eligibleStatus;






}
