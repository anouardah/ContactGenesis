package be.contacts.service;

import be.contacts.domain.Company;
import be.contacts.domain.Contact;
import be.contacts.dto.CompanyDto;
import be.contacts.exception.EntityNotFoundException;
import be.contacts.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContactService contactService;

    public Company getById(long id){
        return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Company getByVatNr(String vatNr){
        return companyRepository.findByVatNr(vatNr).orElseThrow(EntityNotFoundException::new);
    }

    public List<Company> getAll(){
        return companyRepository.findAll();
    }

    public Company create(CompanyDto companyDto){
        Company company = new Company(companyDto.getAddress(), companyDto.getVatNr());
        return companyRepository.save(company);
    }

    public Company update(long id, CompanyDto companyDto){
        Company company = getById(id);

        if(companyDto.getAddress() != null){
            company.setAddress(companyDto.getAddress());
        }

        if(companyDto.getVatNr() != null){
            company.setVatNr(companyDto.getVatNr());
        }

        return companyRepository.save(company);
    }

    public Company addContact(long companyId, long contactId){
        Company company = getById(companyId);
        Contact contact = contactService.getById(contactId);
        company.getContacts().add(contact);
        contact.getCompanies().add(company);

        return companyRepository.save(company);
    }
}
