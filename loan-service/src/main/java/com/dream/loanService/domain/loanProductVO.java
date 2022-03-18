package com.dream.loanService.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class loanProductVO {
	
	private int proNo;	//상품 번호
	private String proName;	//상품 이름
	private double proLimit;	// 대출 한도
	private String description;	// 상품 설명
	private int term;	//대출 기간
}
