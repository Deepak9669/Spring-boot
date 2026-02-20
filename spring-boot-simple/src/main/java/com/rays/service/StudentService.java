package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.StudentDao;

@Service
@Transactional
public class StudentService {
	
	StudentDao studentDao;

}
