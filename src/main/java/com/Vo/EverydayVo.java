package com.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 多日数据中希望获得的每日数据
 */
@Data
@AllArgsConstructor
@ApiModel("多日数据中希望获得的每日数据")
public class EverydayVo {

    @ApiModelProperty("区间中某一日时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    @ApiModelProperty("合格率")
    private Double pass;
    @ApiModelProperty("主板外观缺陷数")
    private Integer appearance;
    @ApiModelProperty("主板固定螺丝缺陷数")
    private Integer screw;
    @ApiModelProperty("CPU风扇外观缺陷数")
    private Integer CPUAppearance;
    @ApiModelProperty("CPU固定螺丝缺陷数")
    private Integer CPUScrew;
    @ApiModelProperty("CPU风扇接线缺陷数")
    private Integer wiring;

    public EverydayVo(){};
}
