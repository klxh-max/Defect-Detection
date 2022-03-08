package com.Vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("历史数据")
@AllArgsConstructor
public class DetectionVo {

    @ApiModelProperty("主板编号")
    private Integer mainboardId;
    @ApiModelProperty("主板型号")
    private String mainboardModel;
    @ApiModelProperty("检测时间（缺陷数据生成时间）")
    private Date gmtCreate;
    @ApiModelProperty("检测编号（缺陷数据编号）")
    private Integer defectId;
    @ApiModelProperty("检测结果（合格情况）")
    private String eligibleStatus;
    @ApiModelProperty("检测方式")
    private String detectionType;
    @ApiModelProperty("检测者工号")
    private Integer directorId;
    @ApiModelProperty("检测标注图  待定")
    private String picture;
    @ApiModelProperty("修改时间")
    private Date gmtModified;

    public DetectionVo(){};
}
