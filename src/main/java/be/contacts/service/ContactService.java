package be.contacts.service;

import be.contacts.domain.Contact;
import be.contacts.domain.Employee;
import be.contacts.domain.Freelancer;
import be.contacts.dto.ContactDto;
import be.contacts.exception.EntityNotFoundException;
import be.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact getById(long id){
        return contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Contact> getAll(){
        return contactRepository.findAll();
    }

    public Contact create(ContactDto contactDto){

        Contact contact;
        if(contactDto.getVatNr() == null){
            contact = new Employee(contactDto.getAddress(), contactDto.getFirstname(), contactDto.getLastname());
        } else {
            contact = new Freelancer(contactDto.getAddress(), contactDto.getFirstname(), contactDto.getLastname(), contactDto.getVatNr());
        }

        return contactRepository.save(contact);
    }

    public Contact update(long id, ContactDto contactDto){
        Contact contact = getById(id);

        if(contactDto.getAddress() != null){
            contact.setAddress(contactDto.getAddress());
        }

        if(contactDto.getFirstname() != null){
            contact.setFirstname(contactDto.getFirstname());
        }

        if(contactDto.getLastname() != null){
            contact.setLastname(contactDto.getLastname());
        }

        if(contactDto.getVatNr() != null && contact instanceof Freelancer){
            ((Freelancer)contact).setVatNr(contactDto.getVatNr());
        }

        return contactRepository.save(contact);
    }

    public void delete(long id){
        Contact contact = getById(id);
        contactRepository.delete(contact);
    }
}
