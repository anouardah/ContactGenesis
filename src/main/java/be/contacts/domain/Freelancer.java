package be.contacts.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Freelancer extends Contact{
    @Column(nullable = false)
    private String VatNr;

    public Freelancer() {
        super(ContactType.FREELANCER);
    }

    public Freelancer(String address, String firstname, String lastname, String vatNr) {
        super(address, firstname, lastname, ContactType.FREELANCER);
        this.VatNr = vatNr;
    }
    
    //region getters/setters
    public String getVatNr() {
        return VatNr;
    }

    public void setVatNr(String vatNr) {
        VatNr = vatNr;
    }
    //endregion
}
