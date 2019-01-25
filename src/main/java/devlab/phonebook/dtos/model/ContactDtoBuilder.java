package devlab.phonebook.dtos.model;

import java.util.ArrayList;
import java.util.List;

public class ContactDtoBuilder {

    private String name = "";
    private String surname = "";
    private String number = "";
    private String address = "";
    private String category = "";
    private int ranking = 0;
    private List<String> tags = new ArrayList<>();

    public ContactDtoBuilder() {
    }

    public ContactDtoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ContactDtoBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }


    public ContactDtoBuilder number(String number) {
        this.number = number;
        return this;
    }

    public ContactDtoBuilder category(String category) {
        this.category = category;
        return this;
    }

    public ContactDtoBuilder address(String address) {
        this.address = address;
        return this;
    }

    public ContactDtoBuilder ranking(int ranking) {
        this.ranking = ranking;
        return this;
    }

    public ContactDtoBuilder tags(List<String> tags) {
        this.tags = tags;
        return this;
    }


    public ContactDto build() {
        return new ContactDto(name, surname, number, address, category ,ranking, tags);
    }


}