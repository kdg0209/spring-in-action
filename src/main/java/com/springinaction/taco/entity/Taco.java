package com.springinaction.taco.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "이름의 최소 길이는 5입니다.")
    private String name;

    @Size(min = 1, message = "하나 이상을 선택해야 합니다.")
    private List<String> ingredients;
}
