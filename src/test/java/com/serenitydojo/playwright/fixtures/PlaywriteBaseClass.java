package com.serenitydojo.playwright.fixtures;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.serenitydojo.playwright.utils.*;
import com.serenitydojo.playwright.utils.allure.AllureAttachments;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.MDC;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@UsePlaywright(HeadlessChromeOptions.class)
public abstract class PlaywriteBaseClass {

  protected static APIRequestContext requestContext;
  protected static Faker faker;
  protected Page page;
  protected static TestConfig testConfig;

  @BeforeAll
  public static void setUpRequestContext(Playwright playwright) {
    testConfig = new TestConfig();
    requestContext = RequestContextFactory.create(playwright, testConfig);
    faker = new Faker();
  }

  @BeforeEach
  public void setUp(TestInfo testInfo, Page page, BrowserContext context) {
    this.page = page;
    TestInitializer.inject(
        this,
        page,
        requestContext,
        faker,
        testConfig);
    TracingManager.start(context);
    this.page.navigate(testConfig.getUiUrl());
    MDC.put(
        "testName",
        testInfo.getTestMethod().get().getName());
  }


  @AfterEach
  public void tearDown(TestInfo testInfo, BrowserContext context) {
    AllureAttachments.screenshot(page);
    String traceName = testInfo.getDisplayName().replace(" ", "-").toLowerCase();
    Path tracePath = Paths.get("target/traces/trace " + traceName + ".zip");
    TracingManager.stop(context, tracePath);
    try {
      AllureAttachments.trace(tracePath);
    } catch (IOException e) {
      throw new RuntimeException("Couldn't attach Playwright trace", e);
    }
    MDC.clear();
  }

}
