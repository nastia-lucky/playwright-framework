package com.serenitydojo.playwright.catalog;

import com.google.inject.Inject;
import com.serenitydojo.playwright.annotations.Regression;
import com.serenitydojo.playwright.annotations.Smoke;
import com.serenitydojo.playwright.annotations.UITest;
import com.serenitydojo.playwright.fixtures.PlaywriteBaseClass;
import com.serenitydojo.playwright.pageObjects.components.CartLineItem;
import com.serenitydojo.playwright.pageObjects.components.NavBar;
import com.serenitydojo.playwright.pageObjects.components.ProductsList;
import com.serenitydojo.playwright.pageObjects.components.SearchComponent;
import com.serenitydojo.playwright.pageObjects.pages.CheckOutPage;
import com.serenitydojo.playwright.pageObjects.pages.ProductDetailsPage;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@Feature("Adding to cart tests")
public class AddingToCartTests extends PlaywriteBaseClass {

  @Inject
  private SearchComponent searchComponent;

  @Inject
  private ProductsList productsList;

  @Inject
  private ProductDetailsPage productDetailsPage;

  @Inject
  private NavBar navBar;

  @Inject
  private CheckOutPage checkOutPage;

  @Regression
  @Smoke
  @UITest
  @Test
  public void checkOutOneItem() {
    searchComponent.searchBy("pliers");
    productsList.viewProductDetails("Combination Pliers");
    productDetailsPage.increaseQuantityTo(3);
    productDetailsPage.addToCart();
    navBar.openCart();
    List<CartLineItem> cartItems = checkOutPage.getLineItems();
    Assertions.assertThat(cartItems)
        .hasSize(1)
        .first()
        .satisfies(
            item -> {
              Assertions.assertThat(item.title()).contains("Combination Pliers");
              Assertions.assertThat(item.quantity()).isEqualTo(3);
              Assertions.assertThat(item.total()).isEqualTo(item.quantity() * item.price());
            }
        );
  }

  @Regression
  @Smoke
  @UITest
  @Test
  public void checkOutMultipleItems() {
    productsList.viewProductDetails("Bolt Cutters");
    productDetailsPage.increaseQuantityTo(2);
    productDetailsPage.addToCart();
    navBar.openHomePage();
    productsList.viewProductDetails("Slip Joint Pliers");
    productDetailsPage.addToCart();
    navBar.openCart();

    List<CartLineItem> cartItems = checkOutPage.getLineItems();

    Assertions.assertThat(cartItems).hasSize(2);

    Assertions.assertThat(productsList.getProductsNames()).contains("Bolt Cutters", "Slip Joint Pliers");

    Assertions.assertThat(cartItems)
        .allSatisfy(
            item -> {
              Assertions.assertThat(item.quantity()).isGreaterThanOrEqualTo(1);
              Assertions.assertThat(item.price()).isGreaterThan(0.0);
              Assertions.assertThat(item.total()).isGreaterThan(0.0);
              Assertions.assertThat(item.total()).isEqualTo(item.quantity() * item.price());
            }
        );


  }

}
