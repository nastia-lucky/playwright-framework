package com.serenitydojo.playwright.api.models.products;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {

  @SerializedName("data")
  private List<Product> data;

  public List<Product> getData() {
    return data;
  }
}
