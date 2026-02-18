package com.rays.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dao.RoleDao;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "Role")
public class RoleCtl {

	@Autowired
	public RoleService RoleService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody RoleForm form) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = new RoleDTO();

		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		long id = RoleService.add(dto);

		res.setSuccess(true);
		res.addMessage("Role added sucessfully");
		res.addData(dto);

		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody RoleForm form) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = new RoleDTO();
		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		RoleService.update(dto);

		res.setSuccess(true);
		res.addMessage("Role added sucessfully");
		res.addData(dto);

		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				RoleService.delete(id);

				res.addMessage("role deteted sucessfully");
				res.setSuccess(true);

			}

		} else {
			res.addMessage("select at least one record");
		}
		return res;

	}

	@PostMapping("get/{id}")
	public ORSResponse get(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = RoleService.findByPk(id);

		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);

		}

		return res;

	}

}
