package web.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.Staff;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!!!!!!");
        return "demo/staff-create";
    }

    @PostMapping("/save")
    public String create(Model model,
                         @Valid @ModelAttribute("staff") Staff staff, // bật validate
                         Errors errors,
                         @RequestPart(value="photo_file", required=false) MultipartFile photoFile) {

        // nếu có upload file
        if(photoFile != null && !photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        // xử lý message
        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi bên dưới!");
        } else {
            model.addAttribute("message", "Xin chào " + staff.getFullname() + " - Dữ liệu đã hợp lệ!");
        }

        return "demo/staff-create";
    }
}
