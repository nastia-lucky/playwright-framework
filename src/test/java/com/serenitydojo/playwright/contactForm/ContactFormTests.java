package com.serenitydojo.playwright.contactForm;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.annotations.Regression;
import com.serenitydojo.playwright.annotations.UITest;
import com.serenitydojo.playwright.fixtures.PlaywriteBaseClass;
import com.serenitydojo.playwright.pageObjects.components.NavBar;
import com.serenitydojo.playwright.pageObjects.forms.ContactForm;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Feature("Contact form tests")
public class ContactFormTests extends PlaywriteBaseClass {

  @Inject
  private ContactForm contactForm;

  @Inject
  private NavBar navBar;


  @BeforeEach
  void setUp(Page page) {
    this.navBar.getByText("Contact").click();
  }

  @Regression
  @UITest
  @Test
  void fillTheContactForm() throws URISyntaxException {

    contactForm.setFirstName("Sarah-Jane");
    contactForm.setLastName("Smith");
    contactForm.setEmail("sarah@example.com");
    contactForm.setMessage("mmessage");
    contactForm.setSubject("Warranty");
    Path fileToUpload = Paths.get(ClassLoader.getSystemResource("data/sample-data.txt").toURI());
    contactForm.setAttachment(fileToUpload);

    contactForm.submitForm();

    Assertions.assertThat(contactForm.getAlertMessage())
        .contains("Thanks for your message! We will contact you shortly.");
  }

  @Regression
  @UITest
  @ParameterizedTest
  @ValueSource(strings = {"First Name", "Last Name", "Email", "Message"})
  void mandatoryFields(String field) {
    contactForm.setFirstName("Sarah-Jane");
    contactForm.setLastName("Smith");
    contactForm.setEmail("sarah@example.com");
    contactForm.setMessage("mmessage");
    contactForm.setSubject("Warranty");

    contactForm.getByLabel(field).clear();

    contactForm.clearField(field);

    contactForm.submitForm();

    var errorMessage = contactForm.getByRole(AriaRole.ALERT).getByText(field + " is required");

    assertThat(errorMessage).isVisible();


  }


  @Regression
  @UITest
  @Test
  void messageTooShort() {

    contactForm.setFirstName("Sarah-Jane");
    contactForm.setLastName("Smith");
    contactForm.setEmail("sarah@example.com");
    contactForm.setMessage("A short long message.");
    contactForm.setSubject("Warranty");

    contactForm.submitForm();

    assertThat(contactForm.getByRole(AriaRole.ALERT)).hasText("Message must be minimal 50 characters");
  }

  @ParameterizedTest(name = "'{arguments}' should be rejected")
  @ValueSource(strings = {"not-an-email", "not-an.email.com", "notanemail"})
  void invalidEmailField(String invalidEmail) {
    contactForm.setFirstName("Sarah-Jane");
    contactForm.setLastName("Smith");
    contactForm.setEmail(invalidEmail);
    contactForm.setMessage("A very long message to the warranty service about a warranty on a product!");
    contactForm.setSubject("Warranty");

    contactForm.submitForm();

    assertThat(contactForm.getByRole(AriaRole.ALERT)).hasText("Email format is invalid");
  }


}
