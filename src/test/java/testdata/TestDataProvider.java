package testdata;

import com.github.javafaker.Faker;

public class TestDataProvider {
    private static final Faker FAKER = new Faker();

    public static String generateRandomName() {
        return FAKER.name().name();
    }

    public static String generateRandomPassword() {
        return FAKER.internet().password();
    }

    public static String generateRandomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String generateRandomUuid() {
        return FAKER.internet().uuid().replace("-", "");
    }
}