package com.serenitydojo.playwright.di;

import com.google.inject.AbstractModule;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.utils.TestConfig;
import net.datafaker.Faker;

public class TestModule extends AbstractModule {

  private final Page page;
  private final APIRequestContext requestContext;
  private final TestConfig testConfig;


  public TestModule(
      Page page,
      APIRequestContext requestContext,
      TestConfig testConfig) {

    this.page = page;
    this.requestContext = requestContext;
    this.testConfig = testConfig;
  }

  @Override
  protected void configure() {
    bind(Page.class).toInstance(page);
    bind(APIRequestContext.class).toInstance(requestContext);
    bind(Faker.class).toInstance(new Faker());
    bind(TestConfig.class).toInstance(testConfig);
  }
}
