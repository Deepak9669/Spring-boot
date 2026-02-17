package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RoleList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "ors")
public class OrsCtl {

	@GetMapping
	public ORSResponse disply() {
		ORSResponse res = new ORSResponse();
		return res;
	}

	@GetMapping("display1")
	public ORSResponse disply1() {
		ORSResponse res = new ORSResponse();
		res.addMessage("user restered sucessfully");
		res.setSuccess(true);

		return res;
	}

	@GetMapping("display2")
	public ORSResponse display2() {
		ORSResponse res = new ORSResponse();

		Map<String, String> map = new HashMap<String, String>();

		map.put("firstName", "firstName is required");
		map.put("lastName", "lastName is required");
		map.put("login", "login is required");
		map.put("password", "password is required");

		res.addInputError(map);
		return res;
	}

	@GetMapping("display3")
	public ORSResponse display3() {
		ORSResponse res = new ORSResponse();

		List list = new ArrayList();

		TestDTO dto = new TestDTO();

		dto.setFirstName("Ram");
		dto.setLastName("lala");
		dto.setLogin("ram@gmail.com");
		dto.setPassword("ram@123");

		list.add(dto);

		TestDTO dto1 = new TestDTO();

		dto1.setFirstName("Ram");
		dto1.setLastName("lala");
		dto1.setLogin("ram@gmail.com");
		dto1.setPassword("ram@123");

		list.add(dto1);

		res.addData(list);

		res.setSuccess(true);

		return res;
	}

	@GetMapping("display4")
	public ORSResponse display4() {
		ORSResponse res = new ORSResponse();

		RoleList roleList = new RoleList();

		roleList.add("Admin");
		roleList.add("Student");
		roleList.add("Faculty");
		roleList.add("College");
		roleList.add("Kiosk");

		res.addResult("roleList", roleList);

		res.setSuccess(true);
		return res;
	}

}
