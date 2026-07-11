package com.serenitydojo.playwright.utils;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import org.assertj.core.util.Paths;

import java.nio.file.Path;

public class TracingManager {

  public static void start(BrowserContext context) {
    context.tracing().start(
        new Tracing.StartOptions()
            .setScreenshots(true)
            .setSnapshots(true)
            .setSources(true));
  }

  public static void stop(BrowserContext context, Path path){
    context.tracing().stop(
        new Tracing.StopOptions()
            .setPath(path)
    );
  }
}
