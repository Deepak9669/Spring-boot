package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.form.CollegeForm;
import com.rays.service.CollegeService;

@RestController
@RequestMapping(value = "college")
public class CollegeCtl extends BaseCtl {

	@Autowired
	public CollegeService collegeService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid CollegeForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {

			return res;
		}

		CollegeDTO dto = new CollegeDTO();

		dto = (CollegeDTO) form.getDto();

		long id = collegeService.add(dto);

		res.setSuccess(true);
		res.addMessage("College added sucessfully");
		res.addData(dto);

		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid CollegeForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {

			return res;
		}

		CollegeDTO dto = new CollegeDTO();

		dto = (CollegeDTO) form.getDto();

		long id = collegeService.add(dto);

		res.setSuccess(true);
		res.addMessage("College updated sucessfully");

		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				collegeService.delete(id);

				res.setSuccess(true);
				res.addMessage("College delete sucessfullyy");
			}

		} else {
			res.addMessage("select at least one record");

		}
		return res;

	}

	@GetMapping("get/{id}")

	public ORSResponse get(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();

		CollegeDTO dto = collegeService.findByPK(id);

		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);

		}
		return res;

	}

	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })

	public ORSResponse search(@RequestBody CollegeForm form, @PathVariable(required = false) int pageNo) {

		ORSResponse res = new ORSResponse();

		int pageSize = 5;

		CollegeDTO dto = new CollegeDTO();

		List<CollegeDTO> list = collegeService.search(dto, pageNo, pageSize);

		if (list.size() > 0) {
			res.addData(list);
			res.setSuccess(true);
			return res;

		}
		return res;

	}

}
