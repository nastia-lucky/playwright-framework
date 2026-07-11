package com.serenitydojo.playwright.utils;

public class TestConfig {

  private final String uiUrl;
  private final String apiUrl;

  public TestConfig() {
    this.uiUrl = System.getProperty("ui.url");
    this.apiUrl = System.getProperty("api.url");
  }

  public String getUiUrl() {
    return uiUrl;
  }

  public String getApiUrl() {
    return apiUrl;
  }
}
