package web.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.cart.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cart;

    // Hiển thị giỏ hàng
    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("cart", cart);
        return "cart/index";
    }

    // Thêm vào giỏ
    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable Integer id) {
        cart.add(id);
        return "redirect:/cart/view";
    }

    // Xóa 1 sản phẩm
    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable Integer id) {
        cart.remove(id);
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/update/{id}")
    public String update(@PathVariable Integer id, @RequestParam Integer qty) {
        cart.update(id, qty);
        return "redirect:/cart/view";
    }

    // Xóa hết giỏ
    @RequestMapping("/cart/clear")
    public String clear() {
        cart.clear();
        return "redirect:/cart/view";
    }
}
