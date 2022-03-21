package com.dream.listservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ListDto {
	private int orderNo;
	private int proNo;
	private String userId;
	private double orderPrice;
	private Date orderDate;
	private Date endDate;
	private int status;
}
