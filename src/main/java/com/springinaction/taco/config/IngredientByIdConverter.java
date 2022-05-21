package com.springinaction.taco.config;

import com.springinaction.taco.entity.Ingredient;
import com.springinaction.taco.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.isPresent() ? ingredient.get() : null;
    }
}
