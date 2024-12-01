package com.hamada.library.domain;


import jakarta.persistence.*;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patrons")
public class PatronEntity {



    public PatronEntity(String email, String contactInformation, String name, Long ID) {
        this.email = email;
        this.contactInformation = contactInformation;
        this.name = name;
        this.ID = ID;
    }

    public PatronEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank(message = "Name is required")
    private String name;

    @NotEmpty(message = "Contact Information is required")
    private String contactInformation;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
