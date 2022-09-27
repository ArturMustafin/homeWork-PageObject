package com.demoqa.testData;

import com.demoqa.utils.RandomUtil;
import com.github.javafaker.Faker;

public class User {

    private static final Faker faker = new Faker();

    public static String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = faker.demographic().sex(),
            number = faker.phoneNumber().subscriberNumber(10),
            day = String.valueOf(faker.number().numberBetween(10, 31)),
            month = RandomUtil.getRandomMonth(),
            year = String.valueOf(faker.number().numberBetween(1900, 2000)),
            subjects = "Math",
            hobbies = "Sports",
            picturePath = "img/1.png",
            picture = "1.png",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi",
            birthDay = day + " " + month + "," + year;
}
