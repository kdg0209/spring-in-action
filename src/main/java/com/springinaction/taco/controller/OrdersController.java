package com.springinaction.taco.controller;

import com.springinaction.taco.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());

        return "/orders/orderForm";
    }

    @PostMapping
    public String processOrder(Order order) {

        return "redirect:/";
    }
}
