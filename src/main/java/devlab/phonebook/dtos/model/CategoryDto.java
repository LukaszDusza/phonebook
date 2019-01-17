package devlab.phonebook.dtos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {

    private String title;
    private List<ContactDto> contacts;
}
