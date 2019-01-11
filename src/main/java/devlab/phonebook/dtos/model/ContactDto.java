package devlab.phonebook.dtos.model;


import devlab.phonebook.model.Category;
import devlab.phonebook.model.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDto {

    private String name;
    private String surname;
    private String number;
    private String address;
    private String category;
    private int ranking;
    private List<String> tags = new ArrayList<>();

}

