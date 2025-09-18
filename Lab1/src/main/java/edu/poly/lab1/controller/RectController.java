package edu.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest; // để lấy tham số từ form
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rectangle")
public class RectController {

    // Hiển thị form ban đầu
    @GetMapping("/form")
    public String form() {
        return "rectangle"; // mở templates/rectangle.html
    }

    // Xử lý khi người dùng submit form
    @PostMapping("/calc")
    public String calc(HttpServletRequest request, Model model) {
        // Lấy giá trị từ form (chuỗi)
        String w = request.getParameter("width");   // name="width" trong form
        String h = request.getParameter("height");  // name="height" trong form

        // Chuyển sang số (double)
        double width = Double.parseDouble(w);
        double height = Double.parseDouble(h);

        // Tính toán
        double area = width * height;
        double perimeter = 2 * (width + height);

        // Đưa dữ liệu sang view
        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("area", area);
        model.addAttribute("perimeter", perimeter);

        return "rectangle"; // render lại rectangle.html
    }
}
