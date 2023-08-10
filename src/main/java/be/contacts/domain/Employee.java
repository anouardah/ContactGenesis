package be.contacts.domain;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Contact{

    public Employee() {
        super(ContactType.EMPLOYEE);
    }
    public Employee(String address, String firstname, String lastname) {
        super(address, firstname, lastname, ContactType.EMPLOYEE);
    }

}
