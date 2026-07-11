package com.serenitydojo.playwright.api.services;

import com.google.gson.Gson;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseApiClient {

  protected final APIRequestContext requestContext;
  protected Gson gson;
  protected final Logger logger =
      LoggerFactory.getLogger(
          getClass());

  public BaseApiClient(APIRequestContext requestContext) {
    this.requestContext = requestContext;
    this.gson = new Gson();
  }

  @Attachment(value = "API Response", type = "application/json")
  protected void logResponse(APIResponse response) {
    logger.info("Response status: {}", response.status());
    logger.info("Response body: {}", response.text());
  }


  @Attachment(value = "API Request", type = "application/json")
  protected void logRequest(Object o, String url, String type) {
    String json;
    if (o != null) {
      json = gson.toJson(o);
      logger.info(type+ "to"+ url);
      logger.info("Request body: {}", json);
    } else {
      logger.info(type+ "to"+ url);
    }
  }


  public APIResponse post(Object o, String url) {

    logRequest(o, url, "POST");

    APIResponse response = requestContext.post(url, RequestOptions.create()
        .setData(o)
        .setHeader("Accept", "application/json"));

    logResponse(response);

    return response;

  }

  public boolean isResponseCodeEqual(int expectedCode, APIResponse response) {
    return response.status() == expectedCode;
  }

  public <T> T parse(
      APIResponse response,
      Class<T> clazz) {
    return gson.fromJson(
        response.text(),
        clazz);
  }

  public APIResponse get(String url) {
    logRequest(null, url, "GET");
    APIResponse response = requestContext.get(url);
    logResponse(response);
    return response;
  }
}
