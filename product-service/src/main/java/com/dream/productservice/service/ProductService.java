package com.dream.productservice.service;

import java.util.List;

import com.dream.productservice.dto.Message;
import com.dream.productservice.dto.ProductDto;

public interface ProductService {
	public List<ProductDto> getProductList() throws Exception;
	
	//kafka 를 이용해 consumer로 보내기 위한 proNo을 db에서 뺴내기 위한 작업
//	public Message selectOne(int proNo);
}
