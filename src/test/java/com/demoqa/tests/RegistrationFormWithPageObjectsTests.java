package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.testData.User;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.qameta.allure.selenide.AllureSelenide;

import static io.qameta.allure.Allure.step;

public class RegistrationFormWithPageObjectsTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterEach
    void addAttach() {
        Attach.addVideo();
        Attach.browserConsoleLogs();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
    }

    @Test
    void fillFormTest() {
        step("Fill the form fields", () -> {
            registrationFormPage.openPage()
                    .setFirstName(User.firstName)
                    .setLastName(User.lastName)
                    .setEmail(User.email)
                    .setGender(User.gender)
                    .setNumber(User.number)
                    .setBirthDate(User.day, User.month, User.year)
                    .setSubjects(User.subjects)
                    .setHobbies(User.hobbies)
                    .uploadPicture(User.picturePath)
                    .setAddress(User.address, User.state, User.city)
                    .clickSubmit();
        });

        step("Check result", () -> {
            registrationFormPage.checkResultsTableVisible()
                    .checkResult("Student Name", User.firstName + " " + User.lastName)
                    .checkResult("Student Email", User.email)
                    .checkResult("Gender", User.gender)
                    .checkResult("Mobile", User.number)
                    .checkResult("Date of Birth", User.birthDay)
                    .checkResult("Subjects", User.subjects)
                    .checkResult("Hobbies", User.hobbies)
                    .checkResult("Picture", User.picture)
                    .checkResult("Address", User.address)
                    .checkResult("State and City", User.state + " " + User.city);
        });
    }

    @Test
    void fillFormWithMinimumDataTest() {
        step("Fill the form fields", () -> {
            registrationFormPage.openPage()
                    .setFirstName(User.firstName)
                    .setLastName(User.lastName)
                    .setGender(User.gender)
                    .setNumber(User.number)
                    .clickSubmit();
        });

        step("Check result", () -> {
            registrationFormPage.checkResultsTableVisible()
                    .checkResult("Student Name", User.firstName + " " + User.lastName)
                    .checkResult("Gender", User.gender)
                    .checkResult("Mobile", User.number);
        });
    }
}
