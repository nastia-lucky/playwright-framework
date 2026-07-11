package com.serenitydojo.playwright.api.models.products;

public record Product(String name, Double price) {

  @Override
  public String name() {
    return name;
  }

  @Override
  public Double price() {
    return price;
  }
}
