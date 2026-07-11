package com.serenitydojo.playwright.utils;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import java.util.Map;

public final class RequestContextFactory {

  private RequestContextFactory() {
  }

  public static APIRequestContext create(Playwright playwright, TestConfig testConfig) {
    return playwright.request().newContext(
        new APIRequest.NewContextOptions()
            .setBaseURL(testConfig.getApiUrl())
            .setExtraHTTPHeaders(
                Map.of(
                    "Accept",
                    "application/json")));

  }
}
