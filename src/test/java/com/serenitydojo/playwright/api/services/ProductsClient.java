package com.serenitydojo.playwright.api.services;

import com.google.inject.Inject;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.serenitydojo.playwright.api.models.products.Product;
import com.serenitydojo.playwright.api.models.products.ProductsResponse;

import java.util.List;

public class ProductsClient extends BaseApiClient {

  @Inject
  public ProductsClient(APIRequestContext requestContext) {
    super(requestContext);
  }

  public List<Product> getProducts() {
    APIResponse response = get("/products?page=2");
    return parse(response, ProductsResponse.class).getData();
  }
}
