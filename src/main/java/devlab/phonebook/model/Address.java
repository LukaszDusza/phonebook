package devlab.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;

    @JsonIgnore
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Contact contact;
}
