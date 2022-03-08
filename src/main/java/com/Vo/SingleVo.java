package com.Vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("单日数据信息")
public class SingleVo {

    @ApiModelProperty("累计检测主板数")
    private Integer mainboardTotal;
    @ApiModelProperty("缺陷主板数")
    private Integer defectTotal;
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

    public SingleVo(){};
}
