package com.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Storage {
  private Long id;
  private String commodityCode;
  private Long count;


}
