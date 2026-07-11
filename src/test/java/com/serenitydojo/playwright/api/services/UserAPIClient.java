package com.serenitydojo.playwright.api.services;

import com.google.inject.Inject;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.serenitydojo.playwright.api.models.users.User;
import com.serenitydojo.playwright.utils.TestConfig;

public class UserAPIClient extends BaseApiClient {

  @Inject
  private TestConfig testConfig;


  @Inject
  public UserAPIClient(APIRequestContext requestContext) {
    super(requestContext);
  }

  public APIResponse registerUser(User user) {
    String url = testConfig.getApiUrl() + "users/register";
    APIResponse response = post(user, url);
    return response;
  }

}
