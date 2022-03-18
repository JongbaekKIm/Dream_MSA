package com.dream.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.productservice.dto.Message;
import com.dream.productservice.dto.ProductDto;
import com.dream.productservice.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductMapper productMapper;

	@Override
	public List<ProductDto> getProductList() throws Exception {
		return productMapper.getProductList();
	}

	//kafka 를 이용해 consumer로 보내기 위한 proNo을 db에서 뺴내기 위한 작업
//	@Override
//	public Message selectOne(int proNo) {
//		// TODO Auto-generated method stub
//		return productMapper.selectOne(proNo);
//	}


}