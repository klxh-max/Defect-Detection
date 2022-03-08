package com.entity;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@ApiModel("缺陷数据")
@Data
@AllArgsConstructor
public class Defect {

  private Integer defectId;
  private String defectType;
  private String defectSize;
  private String defectLocation;
  private String defectDetail;
  private Integer mainboardId;
  private String mainboardModel;
  private Date gmtCreate;
  private Date gmtModified;
  private Integer isDeleted;
  private String eligibleStatus;
  private String detectionType;
  private Integer directorId;
  private Integer imageId;

  public Defect(){};

}
