package devlab.phonebook.dtos.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContactDto {

    private String name;
    private String surname;
    private String number;
    private String address;
    private String category;
    private int ranking;
    private List<String> tags = new ArrayList<>();

}



