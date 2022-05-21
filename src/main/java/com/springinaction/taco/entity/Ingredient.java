package com.springinaction.taco.entity;

import com.springinaction.taco.entity.type.Type;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

}
