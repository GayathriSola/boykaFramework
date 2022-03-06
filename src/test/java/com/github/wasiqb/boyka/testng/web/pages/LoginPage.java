/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.web.pages;

import static com.github.wasiqb.boyka.builders.Locator.createLocator;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Login page object for Sauce demo application.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
@Getter
public class LoginPage {
    /**
     * Login page object.
     *
     * @return {@link LoginPage}
     */
    public static LoginPage loginPage () {
        return new LoginPage ();
    }

    private final Locator loginBox    = createLocator ().web (id ("login_button_container"))
        .create ();
    private final Locator loginButton = createLocator ().web (id ("login-button"))
        .parent (this.loginBox)
        .create ();
    private final Locator password    = createLocator ().web (id ("password"))
        .parent (this.loginBox)
        .create ();
    private final Locator username    = createLocator ().web (id ("user-name"))
        .parent (this.loginBox)
        .create ();

    private LoginPage () {
        // Avoid explicit class initialisation.
    }
}