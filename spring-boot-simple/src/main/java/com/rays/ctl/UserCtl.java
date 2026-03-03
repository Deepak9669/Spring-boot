package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropDownListInt;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.RoleService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserCtl extends BaseCtl {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public AttachmentService attachmentService;

    // ================= PRELOAD =================
    @GetMapping("preload")
    public ORSResponse preload() {
        List<DropDownListInt> list = roleService.search(null, 0, 0);
        ORSResponse res = new ORSResponse();
        res.addResult("roleList", list);
        res.setSuccess(true);
        return res;
    }

    // ================= SAVE / UPDATE =================
    @PostMapping("save")
    public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

        ORSResponse res = validate(bindingResult);
        if (!res.isSuccess()) {
            return res;
        }

        UserDTO dto = (UserDTO) form.getDto();

        if (dto.getId() != null && dto.getId() > 0) {

            // 🔥 Fetch existing user
            UserDTO existingUser = userService.findByPk(dto.getId());

            if (existingUser != null) {

                // Preserve image if not coming from frontend
                if (dto.getImageId() == null) {
                    dto.setImageId(existingUser.getImageId());
                }

                userService.update(dto);

                res.addData(dto.getId());
                res.addMessage("Data Updated Successfully..!!");
                res.setSuccess(true);
            }

        } else {

            long pk = userService.add(dto);
            res.addData(pk);
            res.addMessage("Data Added Successfully..!!");
            res.setSuccess(true);
        }

        return res;
    }

    // ================= DELETE =================
    @GetMapping("delete/{ids}")
    public ORSResponse delete(@PathVariable long[] ids) {

        ORSResponse res = new ORSResponse();

        for (long id : ids) {
            userService.delete(id);
        }

        res.addMessage("Data deleted successfully");
        res.setSuccess(true);

        return res;
    }

    // ================= GET =================
    @GetMapping("get/{id}")
    public ORSResponse get(@PathVariable long id) {

        ORSResponse res = new ORSResponse();
        UserDTO dto = userService.findByPk(id);

        if (dto != null) {
            res.setSuccess(true);
        }

        res.addData(dto);
        return res;
    }

    // ================= SEARCH =================
    @PostMapping("search/{pageNo}")
    public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

        ORSResponse res = new ORSResponse();
        UserDTO dto = (UserDTO) form.getDto();

        int pageSize = 3;

        List list = userService.search(dto, pageNo, pageSize);

        if (list != null && list.size() > 0) {
            res.setSuccess(true);
        }

        res.addData(list);
        return res;
    }

    // ================= UPLOAD PROFILE PIC =================
    @PostMapping("/profilePic/{userId}")
    public ORSResponse uploadPic(@PathVariable Long userId,
                                 @RequestParam("file") MultipartFile file) {

        ORSResponse res = new ORSResponse();

        if (userId == null || userId <= 0) {
            res.addMessage("Invalid User Id");
            res.setSuccess(false);
            return res;
        }

        try {

            UserDTO userDto = userService.findByPk(userId);

            if (userDto == null) {
                res.addMessage("User not found");
                res.setSuccess(false);
                return res;
            }

            AttachmentDTO attachmentDto = new AttachmentDTO(file);
            attachmentDto.setDescription("Profile Pic");
            attachmentDto.setUserId(userId);

            // Replace old image
            if (userDto.getImageId() != null && userDto.getImageId() > 0) {
                attachmentDto.setId(userDto.getImageId());
            }

            Long imageId = attachmentService.save(attachmentDto);

            if (userDto.getImageId() == null) {
                userDto.setImageId(imageId);
                userService.update(userDto);
            }

            res.addResult("imageId", imageId);
            res.addResult("userId", userId);
            res.setSuccess(true);

        } catch (Exception e) {
            e.printStackTrace();
            res.addMessage("Error uploading image");
            res.setSuccess(false);
        }

        return res;
    }

    // ================= DOWNLOAD PROFILE PIC =================
    @GetMapping("/profilePic/{userId}")
    public void downloadPic(@PathVariable Long userId,
                            HttpServletResponse response) {

        try {

            UserDTO userDto = userService.findByPk(userId);

            if (userDto == null || userDto.getImageId() == null) {
                response.getWriter().write("ERROR: File not found");
                return;
            }

            AttachmentDTO attachmentDTO =
                    attachmentService.findById(userDto.getImageId());

            if (attachmentDTO != null) {

                response.setContentType(attachmentDTO.getType());
                OutputStream out = response.getOutputStream();
                out.write(attachmentDTO.getDoc());
                out.close();

            } else {
                response.getWriter().write("ERROR: File not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}