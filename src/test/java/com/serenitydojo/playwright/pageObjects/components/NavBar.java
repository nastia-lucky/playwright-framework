package com.serenitydojo.playwright.pageObjects.components;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.pageObjects.BasePage;
import com.serenitydojo.playwright.pageObjects.forms.ContactForm;
import com.serenitydojo.playwright.pageObjects.forms.Form;
import io.qameta.allure.Step;

public class NavBar extends BasePage {

  public Form openForm(String links) {
    Form form = null;
    Locator link = getByText(links);
    switch (links) {
      case "Contact":
        form = new ContactForm(page);
    }
    link.click();
    return form;
  }


  @Inject
  public NavBar(Page page) {
    super(page);
  }

  @Step("Open cart")
  public void openCart() {
    logger.info("Open cart");
    getByTestId("nav-cart").click();
  }

}
