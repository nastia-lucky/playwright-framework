package com.serenitydojo.playwright.api.models.users;

import com.serenitydojo.playwright.api.models.Address;

public class UserResponse  extends User{

  private String id;

  public UserResponse(String first_name, String last_name, Address address, String phone, String dob, String password, String email) {
    super(first_name, last_name, address, phone, dob, password, email);
  }

  public String getId() {
    return id;
  }
}
