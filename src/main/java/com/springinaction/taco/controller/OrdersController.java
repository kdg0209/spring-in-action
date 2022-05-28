package com.springinaction.taco.controller;

import com.springinaction.taco.entity.Order;
import com.springinaction.taco.entity.User;
import com.springinaction.taco.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderRepository orderRepository;

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
		if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
		if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
		if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
		if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        return "/orders/orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "/orders/orderForm";
        }

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
