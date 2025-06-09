package ru.praktikum.constants;

import org.apache.commons.lang3.RandomStringUtils;

public class RegisterUserData {

    public static final String NAME = RandomStringUtils.randomAlphabetic(7);
    public static final String EMAIL = RandomStringUtils.randomAlphabetic(7) + "@yandex.ru";
    public static final String VALID_PASSWORD = RandomStringUtils.randomNumeric(6);
    public static final String INVALID_PASSWORD = RandomStringUtils.randomNumeric(5);

}
