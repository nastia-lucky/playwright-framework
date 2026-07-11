package com.serenitydojo.playwright.pageObjects.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.pageObjects.BasePage;
import io.qameta.allure.Step;

public class ProductDetailsPage extends BasePage {

  @Inject
  public ProductDetailsPage(Page page) {
    super(page);
  }

  @Step("Increase quantity to")
  public void increaseQuantityTo(int quantity) {
    logger.info("Increase quantity to {}", quantity);
    for (int i = 1; i < quantity; i++) {
      getByTestId("increase-quantity").click();
    }
  }

  @Step("Add to cart")
  public void addToCart() {
    logger.info("Add product to cart");
    page.waitForResponse(
        response -> response.url().contains("/carts") && response.request().method().equals("POST"),
        () -> {
          getByText("Add to cart").click();
          getByRole(AriaRole.ALERT).click();
        }
    );
  }
}
