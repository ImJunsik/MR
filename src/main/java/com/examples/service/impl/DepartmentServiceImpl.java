package com.examples.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.examples.repository.DepartmentRepository;
import com.examples.service.DepartmentService;

@Service
public class DepartmentServiceImpl extends BaseService implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Map<String, Object>> getDepartmentList() {
		return departmentRepository.getDepartmentList();
	}
}