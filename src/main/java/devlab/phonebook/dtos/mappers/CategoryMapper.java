package devlab.phonebook.dtos.mappers;

import devlab.phonebook.commons.mapper.Mapper;
import devlab.phonebook.dtos.model.CategoryDto;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

//    private ContactMapper contactMapper = new ContactMapper();

    @Override
    public CategoryDto map(Category from) {

        return new CategoryDto(
                from.getTitle(),
                new ArrayList<>()
        );
    }

//    public CategoryDto mapWithContacts(Category from) {
//
//        List<ContactDto> contacts = from.getContacts()
//                .stream()
//                .map( c ->  contactMapper.map(c))
//                .collect(Collectors.toList());
//
//        return new CategoryDto(
//                from.getTitle(),
//                contacts
//        );
//    }

}
