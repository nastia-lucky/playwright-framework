package com.serenitydojo.playwright.pageObjects.components;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.pageObjects.BasePage;
import io.qameta.allure.Step;

public class SearchComponent extends BasePage {


  @Inject
  public SearchComponent(Page page) {
    super(page);
  }

  @Step("Search by keyword")
  public void searchBy(String keyword) {
    logger.info("Search by keyword {}", keyword);
    getByPlaceholder("Search").fill(keyword);
    page.waitForResponse("**/products**", () -> {
      page.getByRole(
          AriaRole.BUTTON,
          new Page.GetByRoleOptions().setName("Search")
      ).click();
    });
  }

  @Step("Clear search field")
  public void clearSearch() {
    logger.info("Clear search");
    page.waitForResponse("**/products**", () -> {
      getByTestId("search-reset").click();
    });
  }
}
