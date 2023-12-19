package com.github.wasiqb.boyka.ui.actions;


import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
import static com.github.wasiqb.boyka.ui.pages.LoginPage.loginPage;

/**
 * Login page actions
 */
public class LoginPageActions {
    private static final String           PASSWORD = "Password@1";
    private static final String           USERNAME = "vandana.echidna@gmail.com";
    private static final String           FIRSTNAME = "Vandana";

    public static LoginPageActions loginPageActions() { return new LoginPageActions(); }

    public void login() {
        withMouse(loginPage().getLogin()).click();
        onTextBox(loginPage().getUsername()).enterText(USERNAME);
        onTextBox(loginPage().getPassword()).enterText(PASSWORD);
        withMouse(loginPage().getSubmit()).click();
        onElement (loginPage ().getWelcome ()).verifyText ()
                .isEqualTo ("Welcome " + FIRSTNAME);
    }
}
