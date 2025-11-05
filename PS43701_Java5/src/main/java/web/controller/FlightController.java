package web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.entity.Flight;
import web.service.FlightService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightController {

    private final FlightService service;

    @GetMapping
    public String index(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Flight> items = service.searchByAirline(q);
        model.addAttribute("items", items);
        model.addAttribute("item", new Flight());
        model.addAttribute("q", q == null ? "" : q);
        return "flight/index";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("item") Flight item,
                         BindingResult br, Model model,
                         @RequestParam(value="q", required=false) String q) {

        if (br.hasErrors()) {
            model.addAttribute("items", service.searchByAirline(q));
            model.addAttribute("q", q == null ? "" : q);
            return "flight/index";
        }
        service.create(item);
        return "redirect:/flights";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       @RequestParam(value="q", required=false) String q,
                       Model model) {
        var item = service.findById(id).orElse(new Flight());
        model.addAttribute("item", item);
        model.addAttribute("items", service.searchByAirline(q));
        model.addAttribute("q", q == null ? "" : q);
        return "flight/index";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") Flight item,
                         BindingResult br, Model model,
                         @RequestParam(value="q", required=false) String q) {

        if (br.hasErrors()) {
            model.addAttribute("items", service.searchByAirline(q));
            model.addAttribute("q", q == null ? "" : q);
            return "flight/index";
        }
        service.update(item);
        return "redirect:/flights";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/flights";
    }
    @GetMapping("/delete")
    public String deletes(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/flights";
    }

}
