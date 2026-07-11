package com.serenitydojo.playwright.api.models.users;

import java.util.List;

public class ValidationErrorResponse {

  private List<String> first_name;


  public List<String> getFirst_name() {
    return first_name;
  }

  public String getMessage() {
    return first_name.get(0);
  }
}
