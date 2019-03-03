package devlab.phonebook.excercise;


import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {

        List<ContactDto> source = Arrays.asList(
                 ContactDto.builder()
                .address("acv")
                .category("kids")
                .name("Lukasz")
                .build(),
                ContactDto.builder()
                        .address("acv")
                        .category("friends")
                        .name("Brtek")
                        .build()
        );



        List<ContactDto> contacts = new ArrayList<>();


                source.stream()
                .filter(c -> c.getCategory().equals("friends"))
                .map(contacts::add)
                .collect(Collectors.toList());

        System.out.println(contacts);


        ExampleInterface exmp = () -> contacts.get(0);

        System.out.println(exmp.getContact());

    }
}
