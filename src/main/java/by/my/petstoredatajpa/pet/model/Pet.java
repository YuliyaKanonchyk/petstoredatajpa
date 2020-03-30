package by.my.petstoredatajpa.pet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
    @NotBlank
    @Size(min = 2, max = 10)
    private String petName;

    @NotBlank
    @OneToMany
    private List<Tag> tag;

    @NotBlank
    private Status status;

    public enum Status {
        available,
        pending,
        sold
    }
}
