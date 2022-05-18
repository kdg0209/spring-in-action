package com.springinaction.taco.controller;

import com.springinaction.taco.entity.Ingredient;
import com.springinaction.taco.entity.Taco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("A", "A WRAP Tortilla", Ingredient.Type.WRAP),
                new Ingredient("B", "B CHEESE Tortilla", Ingredient.Type.CHEESE),
                new Ingredient("C", "C PROTEIN Tortilla", Ingredient.Type.PROTEIN),
                new Ingredient("D", "D VEGGIES Tortilla", Ingredient.Type.VEGGIES),
                new Ingredient("E", "E WRAP Tortilla", Ingredient.Type.WRAP),
                new Ingredient("F", "F CHEESE Tortilla", Ingredient.Type.CHEESE),
                new Ingredient("G", "G PROTEIN Tortilla", Ingredient.Type.PROTEIN),
                new Ingredient("H", "H PROTEIN Tortilla", Ingredient.Type.PROTEIN)
        );

        Ingredient.Type[] types = Ingredient.Type.values();

        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByTpe(ingredients, type));
        }

        model.addAttribute("taco", new Taco());
        return "/main/design";
    }

    @PostMapping
    public String processDesign(Taco taco) {

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByTpe(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(item -> item.getType().equals(type)).collect(Collectors.toList());
    }
}
