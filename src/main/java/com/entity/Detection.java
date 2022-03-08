package com.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Detection {

  private Integer mainboardId;
  private String mainboardModel;
  private Date gmtCreate;
  private Integer isDeleted;
  private String eligibleStatus;
  private String detectionType;
  private Integer inspectorId;
  private Integer imageId;


}
