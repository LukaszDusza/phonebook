package devlab.phonebook.service;

import devlab.phonebook.model.Contact;
import devlab.phonebook.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Contact addNewContact(Contact contact) {
        return contactRepository.save(contact);
    }


}
