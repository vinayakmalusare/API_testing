package data;

import net.datafaker.Faker;

public class PartialUpdateDataBuilder {
    private static final Faker FAKER = new Faker();
    public static PartialUpdate getPartialData(){

        return PartialUpdate.builder().firstname(FAKER.name().firstName())
                .lastname(FAKER.name().lastName()).build();
    }
}
