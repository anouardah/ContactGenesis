package be.contacts.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;


    @ManyToMany
    @JsonIgnoreProperties("contacts")
    private Set<Company> companies = new HashSet<>();

    private final ContactType type;

    public Contact(ContactType type) {
        this.type = type;
    }

    public Contact(String address, String firstname, String lastname, ContactType type) {
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    //region getters/setters
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public ContactType getType() {
        return type;
    }

    //endregion
}
