package be.contacts.dto;

public class CompanyDto {
    private String address;
    private String vatNr;

    //region getters/setters

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


    //endregion
}
