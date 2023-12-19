package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 *
 * Home page objects
 */
@Getter
public class LoginPage {
    private static final LoginPage Instance = new LoginPage();
    public static LoginPage loginPage() { return Instance; }

    private final Locator login = Locator.buildLocator()
            .web (By.xpath("//div[@class='hidden-xs hidden-sm']//a[@id='CC-loginHeader-login']"))
            .name ("Login")
            .build();
    private final Locator Username = Locator.buildLocator()
            .web (By.xpath("//input[contains(@id, 'login-input') and contains(@placeholder, 'Email Address')]"))
            .name ("Username")
            .build();
    private final Locator Password = Locator.buildLocator()
            .web (By.xpath("//input[contains(@type, 'password') and contains(@id, 'login-password')]"))
            .name ("Password")
            .build();
    private final Locator submit = Locator.buildLocator()
            .web (By.xpath("//button[contains(@id, 'LoginSubmit') and contains(@type, 'button')]"))
            .name ("Submit")
            .build();

    private final Locator welcome = Locator.buildLocator()
            .web (By.xpath("//span[@class='cc-header-firstName']"))
            .name ("Welcome FirstName")
            .build();
}



