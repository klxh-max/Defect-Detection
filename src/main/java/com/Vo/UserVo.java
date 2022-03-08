package com.Vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@ApiModel
@AllArgsConstructor
public class UserVo {

    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("用户昵称")
    private String username;
    @ApiModelProperty("用户身份")
    private String userStatus;
    @ApiModelProperty("用户真实姓名")
    private String userRealname;
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("用户地址")
    private String address;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户性别")
    private Integer sex;

    public UserVo(){};
}
