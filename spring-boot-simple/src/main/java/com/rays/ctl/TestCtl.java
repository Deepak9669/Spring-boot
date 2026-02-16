package com.rays.ctl;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "Test")
public class TestCtl {

	@GetMapping("display")
	public String display() {
		return "display method ..!!";
	}

	@PostMapping("submit")
	public String submit() {
		return "submmit method....!!!";
	}

	@GetMapping("TestDTO")
	public TestDTO testDTO() {

		TestDTO dto = new TestDTO();

		dto.setId(1);
		dto.setFirstName("Dev");
		dto.setLastName("Verma");
		dto.setLogin("dev@gmail.com");
		dto.setPassword("dev@123");
		dto.setAddress("sehore");
		dto.setDob(new Date());

		return dto;
	}

}
