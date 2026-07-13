package com.serenitydojo.playwright.pageObjects;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.utils.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {


  @Inject
  private TestConfig testConfig;
  protected Page page;
  protected final Logger logger = LoggerFactory.getLogger(getClass());



  public BasePage(Page page) {
    this.page = page;
  }


  public void open(String uri) {
    page.navigate(testConfig.getUiUrl() + "/" + uri);
    logger.info("Open page {}", testConfig.getUiUrl() + "/" + uri);
  }

  public void openHomePage() {
    page.navigate(testConfig.getUiUrl());
    logger.info("Open home page {}", testConfig.getUiUrl());

  }

  public Locator getByLabel(String label) {
    logger.debug("Get by label {}", label);
    return page.getByLabel(label);
  }

  public Locator getByText(String text) {
    logger.debug("Get by text {}", text);
    return page.getByText(text);
  }

  public String getAlertText() {
    String text = page.getByRole(AriaRole.ALERT).textContent();
    logger.debug("Get alert text {}", text);
    return text;
  }

  public Locator getByPlaceholder(String placeholder) {
    logger.debug("Get by placeholder {}", placeholder);
    return page.getByPlaceholder(placeholder);
  }

  public String getTitle() {
    String title = page.getByTestId("page-title").textContent();
    logger.debug("Get title {}", title);
    return title;
  }

  public String getTextByTestId(String testId) {
    String text = page.getByTestId(testId).textContent();
    logger.debug("Get text {} by testId {}", text, testId);
    return text;
  }

  public Locator getByTestId(String testId) {
    logger.debug("Get by testId {}", testId);
    return page.getByTestId(testId);
  }

  public Locator getByRole(AriaRole role) {
    logger.debug("Get by role {}", role);
    return page.getByRole(role);
  }

  public Locator getByLocator(String locator) {
    logger.debug("Get by locator {}", locator);
    return page.locator(locator);
  }


}
