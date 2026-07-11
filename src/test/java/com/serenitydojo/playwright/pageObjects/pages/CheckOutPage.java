package com.serenitydojo.playwright.pageObjects.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.pageObjects.BasePage;
import com.serenitydojo.playwright.pageObjects.components.CartLineItem;
import io.qameta.allure.Step;

import java.util.List;

public class CheckOutPage extends BasePage {


  @Inject
  public CheckOutPage(Page page) {
    super(page);
  }

  @Step("Get line items")
  public List<CartLineItem> getLineItems() {
    logger.info("Get line items");
    page.locator("app-cart tbody tr").first().waitFor();
    return page.locator("app-cart tbody tr")
        .all()
        .stream()
        .map(
            row -> {
              String title = trimmed(row.getByTestId("product-title").innerText());
              int quantity = Integer.parseInt(row.getByTestId("product-quantity").inputValue());
              double price = Double.parseDouble(price(row.getByTestId("product-price").innerText()));
              double linePrice = Double.parseDouble(price(row.getByTestId("line-price").innerText()));
              return new CartLineItem(title, quantity, price, linePrice);
            }
        ).toList();


  }

  private String trimmed(String value) {
    return value.strip().replaceAll("\u00A0", "");
  }

  private String price(String value) {
    return value.replace("$", "");
  }
}
