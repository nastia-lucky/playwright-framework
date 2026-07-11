package com.serenitydojo.playwright.utils;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class HeadlessChromeOptions implements OptionsFactory {
  @Override
  public Options getOptions() {
    return new Options().setLaunchOptions(
        new BrowserType.LaunchOptions()
            .setHeadless(true))
        .setTestIdAttribute("data-test");
  }
}
