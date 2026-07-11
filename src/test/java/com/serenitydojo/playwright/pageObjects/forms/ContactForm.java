package com.serenitydojo.playwright.pageObjects.forms;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.nio.file.Path;

public class ContactForm extends Form {


  @Inject
  public ContactForm(Page page) {
    super(page);
  }

  @Step("Set first name")
  public void setFirstName(String firstName) {
    logger.info("Set first name field with {}", firstName);
    getByLabel("First name").fill(firstName);
  }

  @Step("Set last name")
  public void setLastName(String lastName) {
    logger.info("Set last name field with {}", lastName);
    getByLabel("Last name").fill(lastName);
  }

  @Step("Set email")
  public void setEmail(String email) {
    logger.info("Set email field with {}", email);
    getByLabel("Email").fill(email);
  }

  @Step("Set message")
  public void setMessage(String message) {
    logger.info("Set message field with {}", message);
    getByLabel("Message").fill(message);
  }

  @Step("Set subject")
  public void setSubject(String subject) {
    logger.info("Set subject field with {}", subject);
    getByLabel("Subject").selectOption(subject);
  }

  @Step("Set attachment")
  public void setAttachment(Path fileToUpload) {
    logger.info("Set attachment");
    page.setInputFiles("#attachment", fileToUpload);
  }

  @Step("Submit form")
  public void submitForm() {
    logger.info("Submit form");
    getByText("Send").click();
  }

  @Step("Get alert message")
  public String getAlertMessage() {
    logger.info("Get alert message");
    return getAlertText();
  }

  @Step("Clear field")
  public void clearField(String fieldName) {
    logger.info("Clear {} field", fieldName);
    getByLabel(fieldName).clear();
  }
}
