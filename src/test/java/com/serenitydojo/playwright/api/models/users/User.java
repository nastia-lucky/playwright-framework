package com.serenitydojo.playwright.api.models.users;

import com.serenitydojo.playwright.api.models.Address;

import java.util.Objects;

public class User {

  private String first_name;
  private String last_name;
  private Address address;
  private String phone;
  private String dob;
  private String password;
  private String email;


  public String getPassword() {
    return password;
  }


  public String getEmail() {
    return email;
  }


  public User(String first_name, String last_name, Address address, String phone, String dob, String password, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.address = address;
    this.phone = phone;
    this.dob = dob;
    this.password = password;
    this.email = email;
  }


  public User withPassword(String password) {
    return new com.serenitydojo.playwright.api.models.users.User(this.first_name, this.last_name, this.address
        , this.phone, this.dob, password, this.email);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    User user = (User) object;
    return Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name, address, phone, dob, password, email);
  }
}
