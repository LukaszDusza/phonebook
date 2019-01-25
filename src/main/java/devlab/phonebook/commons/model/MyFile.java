package devlab.phonebook.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MyFile {
    private String name;
    private String fullPath;
    private String time;

    public MyFile(String name, String fullPath) {
        this.name = name;
        this.fullPath = fullPath;
    }

    public MyFile(String name, String fullPath, String time) {
        this.name = name;
        this.fullPath = fullPath;
        this.time = time;
    }

    public MyFile() {
    }
}
