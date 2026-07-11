package com.serenitydojo.playwright.pageObjects.components;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.pageObjects.BasePage;
import io.qameta.allure.Step;

import java.util.List;

public class ProductsList extends BasePage {

  @Inject
  public ProductsList(Page page) {
    super(page);
  }

  @Step("Get product names")
  public List<String> getProductsNames() {
    List<String> productsNames = getByTestId("product-name").allInnerTexts();
    logger.info("Get product names {}", productsNames);
    return productsNames;
  }

  @Step("View product details")
  public void viewProductDetails(String productName) {
    logger.info("Open product details page for product {}", productName);
    page.locator(".card").getByText(productName).click();
  }


  @Step("Get search completed message")
  public String getSearchCompletedMessage() {
    logger.info("Get search completed message");
    return getTextByTestId("search_completed");
  }


}
