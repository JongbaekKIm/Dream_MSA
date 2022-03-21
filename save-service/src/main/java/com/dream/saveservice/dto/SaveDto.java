package com.dream.saveservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveDto {
	private int orderNo;
	private int proNo;
	private String userId;
	private double orderPrice;
	private Date orderDate;
	private Date endDate;
	private int status;
}