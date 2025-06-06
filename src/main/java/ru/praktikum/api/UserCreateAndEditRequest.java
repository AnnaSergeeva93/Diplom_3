package ru.praktikum.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserCreateAndEditRequest {
    private String email;
    private String password;
    private String name;
}
