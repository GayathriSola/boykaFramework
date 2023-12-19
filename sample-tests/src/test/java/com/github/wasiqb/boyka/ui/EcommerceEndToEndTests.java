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

package com.github.wasiqb.boyka.ui;

import static com.github.wasiqb.boyka.actions.drivers.NavigateActions.navigate;
import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.ui.actions.CheckoutPageActions.checkoutPageActions;
import static com.github.wasiqb.boyka.ui.actions.ConfirmOrderPageActions.confirmOrderPageActions;
import static com.github.wasiqb.boyka.ui.actions.HomePageActions.homePageActions;
import static com.github.wasiqb.boyka.ui.actions.OrderSuccessPageActions.orderSuccessPageActions;
import static com.github.wasiqb.boyka.ui.actions.LoginPageActions.loginPageActions;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.ui.data.BillingData;
import com.github.wasiqb.boyka.ui.data.LoginData;
import com.github.wasiqb.boyka.ui.data.TestDataBuilder;
import org.testng.annotations.*;

/**
 * End to End tests for LambdaTest Ecommerce Playground website.
 *
 * @author Faisal Khatri
 * @since 8/4/2022
 **/
public class EcommerceEndToEndTests {

    @AfterMethod(alwaysRun = true)
    public void afterMethod () {
        onWindow ().takeScreenshot ();
    }
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "driverKey" })
    public void setupTestClass (final String driverKey) {
        //final String url = "https://ecommerce-playground.lambdatest.io/";
        final String url = "https://a1553334c1tst-store.occa.ocs.oraclecloud.com/home";
        createSession ("User 1", PlatformType.WEB, driverKey);
        navigate ().to (url);
    }

    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        clearSession ();
    }

    @Test (description = "Test login functionality")
    public void testUserLogin () {
        loginPageActions().login();
    }
}
