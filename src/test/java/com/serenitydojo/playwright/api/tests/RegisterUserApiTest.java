package com.serenitydojo.playwright.api.tests;

import com.google.inject.Inject;
import com.microsoft.playwright.APIResponse;
import com.serenitydojo.playwright.annotations.ApiTest;
import com.serenitydojo.playwright.annotations.Smoke;
import com.serenitydojo.playwright.api.models.Address;
import com.serenitydojo.playwright.api.models.users.User;
import com.serenitydojo.playwright.api.models.users.UserResponse;
import com.serenitydojo.playwright.api.models.users.ValidationErrorResponse;
import com.serenitydojo.playwright.api.services.UserAPIClient;
import com.serenitydojo.playwright.factories.UserFactory;
import com.serenitydojo.playwright.fixtures.PlaywriteBaseClass;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class RegisterUserApiTest extends PlaywriteBaseClass {

  @Inject
  private UserAPIClient userAPIClient;

  @Inject
  private UserFactory userFactory;

  @Smoke
  @ApiTest
  @Test
  void shouldRegisterUser() {
    User user = userFactory.randomUser();
    APIResponse response = userAPIClient.registerUser(user);
    User createdUser = userAPIClient.parse(response, User.class);
    String id = userAPIClient.parse(response, UserResponse.class).getId();


    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(userAPIClient.isResponseCodeEqual(201, response));
      softly.assertThat(createdUser)
          .as("Created user should match specified user ")
          .isEqualTo(user);
      softly.assertThat(id).isNotEmpty();
      softly.assertThat(id).isNotEqualTo(null);
      softly.assertThat(response.headers().get("content-type")).contains("application/json");
    });
  }

  @Test
  void firstNameShouldMandatory() {
    Address address = userFactory.getValidAddress();
    User user = new User(null, faker.name().lastName(), address
        , "0987654321",
        "1970-01-01", "SuperSecure@123", faker.internet().emailAddress());
    APIResponse response = userAPIClient.registerUser(user);
    ValidationErrorResponse validationErrorResponse = userAPIClient.parse(response, ValidationErrorResponse.class);
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(userAPIClient.isResponseCodeEqual(422, response));
      softly.assertThat(validationErrorResponse.getMessage()).isEqualTo("The first name field is required.");
    });

  }
}
