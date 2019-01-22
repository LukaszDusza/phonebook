package devlab.phonebook.commons.xlsCreator;


import devlab.phonebook.dtos.model.ContactDto;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CreatorXLS<Person> creatorXLS = new CreatorXLS<>(Person.class);

        List<Person> persons = Arrays.asList(
                new Person("Adam", "Kowalski", 24),
                new Person("Monika", "Kowalska", 22)
        );


        try {
            creatorXLS.createFile(persons,"src/main/resources/", "persons");

        } catch (IOException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
