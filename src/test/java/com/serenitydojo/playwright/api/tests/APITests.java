package com.serenitydojo.playwright.api.tests;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.serenitydojo.playwright.annotations.ApiTest;
import com.serenitydojo.playwright.annotations.Regression;
import com.serenitydojo.playwright.api.models.products.Product;
import com.serenitydojo.playwright.api.services.ProductsClient;
import com.serenitydojo.playwright.fixtures.PlaywriteBaseClass;
import com.serenitydojo.playwright.pageObjects.components.ProductsList;
import com.serenitydojo.playwright.pageObjects.components.SearchComponent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class APITests extends PlaywriteBaseClass {


  @Inject
  private SearchComponent searchComponent;

  @Inject
  private ProductsList productsList;

  @Inject
  private ProductsClient productsClient;

  @Test
  @ApiTest
  @Regression
  void checkKnownProducts() {

    List<Product> products =
        productsClient.getProducts();

    for (Product product : products) {

      searchComponent.searchBy(product.name());

      Locator productCard =
          productsList.getByLocator(".card")
              .filter(
                  new Locator.FilterOptions()
                      .setHasText(product.name())
                      .setHasText(
                          Double.toString(
                              product.price())));

      assertThat(productCard.isVisible());
    }
  }

}


