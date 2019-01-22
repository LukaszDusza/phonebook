package devlab.phonebook.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MyFile {
    private String name;
    private String fullPath;
    private String time;

    public MyFile(String name, String fullPath) {
        this.name = name;
        this.fullPath = fullPath;
    }
}
