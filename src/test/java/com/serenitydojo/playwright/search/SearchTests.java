package com.serenitydojo.playwright.search;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.annotations.Regression;
import com.serenitydojo.playwright.annotations.Smoke;
import com.serenitydojo.playwright.annotations.UITest;
import com.serenitydojo.playwright.fixtures.PlaywriteBaseClass;
import com.serenitydojo.playwright.pageObjects.components.ProductsList;
import com.serenitydojo.playwright.pageObjects.components.SearchComponent;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Feature("Search tests")
public class SearchTests extends PlaywriteBaseClass {

  @Inject
  private SearchComponent searchComponent;

  @Inject
  private ProductsList productsList;


  @Test
  public void tryPageObjects() throws InterruptedException {
    searchComponent.searchBy("tape");
    var matchingProducts = productsList.getProductsNames();
    Assertions.assertThat(matchingProducts).contains("Measuring Tape");
  }

  @UITest @Regression @Smoke
  @Test
  public void noSearchMatch() {
    searchComponent.searchBy("unknown ");
    var matchingProducts = productsList.getProductsNames();
    Assertions.assertThat(matchingProducts).isEmpty();
    Assertions.assertThat(productsList.getSearchCompletedMessage())
        .contains("There are no products found.");

  }

  @UITest @Regression
  @Test
  public void clearingSearchResult() {
    searchComponent.searchBy("saw");
    var matchingFilteredProducts = productsList.getProductsNames();
    Assertions.assertThat(matchingFilteredProducts).hasSize(2);
    searchComponent.clearSearch();
    var matchingProducts = productsList.getProductsNames();
    Assertions.assertThat(matchingProducts).hasSize(9);
  }

}
