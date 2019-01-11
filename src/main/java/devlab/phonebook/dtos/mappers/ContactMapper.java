package devlab.phonebook.dtos.mappers;

import devlab.phonebook.commons.mapper.Mapper;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ContactMapper implements Mapper<Contact, ContactDto> {


    @Override
    public ContactDto map(Contact from) {

        List<String> tags = from.getTags()
                .stream()
                .map(TagsToStringsList.INSTANCE)
                .collect(Collectors.toList());

        return new ContactDto(
                from.getName(),
                from.getSurname(),
                from.getNumber(),
                from.getAddress().getCity(),
                from.getCategory().getTitle(),
                from.getRanking().getNumber(),
                tags
        );
    }

    private enum TagsToStringsList implements Function<Tag, String> {
        INSTANCE;

        @Override
        public String apply(Tag tag) {
            return tag.getTitle();
        }
    }
}
