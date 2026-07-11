package com.serenitydojo.playwright.factories;

import com.google.inject.Inject;
import com.serenitydojo.playwright.api.models.Address;
import com.serenitydojo.playwright.api.models.users.User;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserFactory {

  private final Faker faker;

  @Inject
  public UserFactory(Faker faker) {
    this.faker = faker;
  }

  public Address getValidAddress() {
    return new Address(faker.address().streetAddress(), faker.address().buildingNumber(), faker.address().city(),
        faker.address().state(), faker.address().country(), faker.address().postcode());
  }

  public User randomUser() {
    int year = faker.number().numberBetween(1970, 2000);
    int month = faker.number().numberBetween(1, 12);
    int day = faker.number().numberBetween(1, 28);
    LocalDate date = LocalDate.of(year, month, day);
    String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    Address address = new Address(faker.address().streetAddress(), faker.address().buildingNumber(), faker.address().city(),
        faker.address().state(), faker.address().country(), faker.address().postcode());
    return
        new com.serenitydojo.playwright.api.models.users.User(faker.name().firstName(), faker.name().lastName(), address
            , "0987654321",
            formattedDate, "Super&&&&****)))Secure@123", faker.internet().emailAddress());
  }
}
