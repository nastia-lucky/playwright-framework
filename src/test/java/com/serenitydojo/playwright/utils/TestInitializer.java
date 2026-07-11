package com.serenitydojo.playwright.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.di.TestModule;
import net.datafaker.Faker;

public class TestInitializer {

  private TestInitializer(){}

  public static void inject(
      Object test,
      Page page,
      APIRequestContext requestContext,
      Faker faker,
      TestConfig config) {

    Injector injector =
        Guice.createInjector(
            new TestModule(
                page,
                requestContext,
                faker,
                config));

    injector.injectMembers(test);
  }
}
