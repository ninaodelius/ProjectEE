package se.nina.projectee.flash;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "flashes")
public class FlashModel{

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50)
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;



}
