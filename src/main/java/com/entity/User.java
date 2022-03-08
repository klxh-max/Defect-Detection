package com.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("用户")
@Data
public class User {

  @TableId
  private Integer userId;
  @ApiModelProperty("用户昵称")
  private String username;
  @ApiModelProperty("用户密码")
  private String password;
  @ApiModelProperty("用户身份")
  private String userStatus;
  @ApiModelProperty("用户真实姓名")
  private String userRealname;
  private Integer isDeleted;
  private Date gmtCreate;
  private Date gmtModified;
  @ApiModelProperty("用户手机号")
  private String phone;
  @ApiModelProperty("用户地址")
  private String address;
  @ApiModelProperty("用户邮箱")
  private String email;
  @ApiModelProperty("用户性别")
  private Integer sex;

}
