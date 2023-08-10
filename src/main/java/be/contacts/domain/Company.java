package be.contacts.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String vatNr;

    @JsonIgnoreProperties("companies")
    @ManyToMany
    private Set<Contact> contacts = new HashSet<>();

    public Company() {
    }

    public Company(String address, String vatNr) {
        this.address = address;
        this.vatNr = vatNr;
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

    public String getVatNr() {
        return vatNr;
    }

    public void setVatNr(String vatNr) {
        this.vatNr = vatNr;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    //endregion
}
