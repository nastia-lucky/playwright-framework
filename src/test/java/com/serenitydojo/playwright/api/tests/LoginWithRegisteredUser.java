package com.serenitydojo.playwright.api.tests;

import com.google.inject.Inject;
import com.serenitydojo.playwright.annotations.ApiTest;
import com.serenitydojo.playwright.annotations.Regression;
import com.serenitydojo.playwright.annotations.Smoke;
import com.serenitydojo.playwright.api.models.users.User;
import com.serenitydojo.playwright.api.services.UserAPIClient;
import com.serenitydojo.playwright.factories.UserFactory;
import com.serenitydojo.playwright.fixtures.PlaywriteBaseClass;
import com.serenitydojo.playwright.pageObjects.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginWithRegisteredUser extends PlaywriteBaseClass {

  @Inject
  private UserAPIClient userAPIClient;

  @Inject
  private LoginPage loginPage;


  @Inject
  private UserFactory userFactory;


  @Smoke
  @Regression
  @ApiTest
  @Test
  public void loginWithRegisteredUser() {
    User user = userFactory.randomUser();
    userAPIClient.registerUser(user);
    loginPage.open();
    loginPage.loginAs(user);
    assertThat(loginPage.title()).isEqualTo("My account");

  }

  @Regression
  @ApiTest
  @Test
  public void shouldRejectUserWithIncorrectPassword() {
    User user = userFactory.randomUser();
    userAPIClient.registerUser(user);
    loginPage.open();
    loginPage.loginAs(user.withPassword("wrong password"));
    assertThat(loginPage.loginErrorMessage()).isEqualTo("Invalid email or password");
  }

}
