package com.serenitydojo.playwright.utils.allure;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AllureAttachments {

  private AllureAttachments() {
  }


  @Attachment(
      value = "Screenshot",
      type = "image/png")
  public static byte[] screenshot(Page page) {

    return page.screenshot();
  }

  @Attachment(
      value = "Playwright Trace",
      type = "application/zip")
  public static byte[] trace(Path trace)
      throws IOException {

    return Files.readAllBytes(trace);
  }

}
