package com.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Image {

  private Integer dataId;
  private String imageName;
  private String imageType;
  private Date gmtCreate;
  private Date gmtModified;
  private Integer isDeleted;
  private BigDecimal imageSize;
  private Integer directorId;
  private String imageUrl;


}
