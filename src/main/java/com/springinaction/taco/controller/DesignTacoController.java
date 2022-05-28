package com.springinaction.taco.controller;

import com.springinaction.taco.entity.Ingredient;
import com.springinaction.taco.entity.Order;
import com.springinaction.taco.entity.Taco;
import com.springinaction.taco.entity.User;
import com.springinaction.taco.entity.type.Type;
import com.springinaction.taco.repository.IngredientRepository;
import com.springinaction.taco.repository.TacoRepository;
import com.springinaction.taco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(item -> ingredients.add(item));

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByTpe(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "/main/design";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "/main/design";
        }

        Taco savedTaco = tacoRepository.save(taco);
        order.addDesign(savedTaco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByTpe(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(item -> item.getType().equals(type)).collect(Collectors.toList());
    }
}
