package com.dream.manage.service;

import org.springframework.stereotype.Service;

import com.dream.manage.dto.ManageDto;
import com.dream.manage.mapper.ManageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManageServiceImpl implements ManageService{
	private final ManageMapper mapper;

	@Override
	public void register(ManageDto dto) {
		// TODO Auto-generated method stub
		log.info("서비스시작전");
		mapper.register(dto);
		log.info("서비스시작끝");
	}
}