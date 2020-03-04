package com.common.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Order {
  private Integer id;
  private String userId;
  private String commodityCode;
  private Integer count;
  private BigDecimal money;
}
