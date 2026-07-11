package com.serenitydojo.playwright.pageObjects.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.api.models.users.User;
import com.serenitydojo.playwright.pageObjects.BasePage;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {


  @Inject
  public LoginPage(Page page) {
    super(page);
  }

  public void open() {
    open("auth/login");
  }

  @Step("Login to account")
  public void loginAs(User user) {
    logger.info("Login with email {} and password {}", user.getEmail(), user.getPassword());
    getByPlaceholder("Your email").fill(user.getEmail());
    getByPlaceholder("Your password").fill(user.getPassword());
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
  }


  @Step("Get title")
  public String title() {
    logger.info("Get page title");
    return getTitle();

  }

  @Step("Get login error message")
  public String loginErrorMessage() {
    logger.info("Get login message");
    return getTextByTestId("login-error");
  }
}
