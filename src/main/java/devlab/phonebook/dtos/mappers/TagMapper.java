package devlab.phonebook.dtos.mappers;

import devlab.phonebook.commons.mapper.Mapper;
import devlab.phonebook.dtos.model.TagDto;
import devlab.phonebook.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements Mapper<Tag, TagDto> {

    @Override
    public TagDto map(Tag from) {

        return TagDto
                .builder()
                .title(from.getTitle())
                .build();
    }
}
