package devlab.phonebook.service;

import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Address;
import devlab.phonebook.model.Category;
import devlab.phonebook.model.Contact;
import devlab.phonebook.model.Ranking;
import devlab.phonebook.repository.AddressRepository;
import devlab.phonebook.repository.CategoryRepository;
import devlab.phonebook.repository.ContactRepository;
import devlab.phonebook.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ContactService {

  //  private final static Logger LOGGER = Logger.getLogger(ContactService.class.getName());

    private ContactRepository contactRepository;
    private AddressRepository addressRepository;
    private CategoryRepository categoryRepository;
    private RankingRepository rankingRepository;

    public ContactService(ContactRepository contactRepository, AddressRepository addressRepository, CategoryRepository categoryRepository, RankingRepository rankingRepository) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.categoryRepository = categoryRepository;
        this.rankingRepository = rankingRepository;
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public void addNewContact(Contact contact) {
        contactRepository.save(contact);
    }


    public List<Contact> getContactsByname(String name) {
        return contactRepository.getContactsByName(name);
    }

    public void addNewContactDTO(ContactDto contactDto) {

        Contact contact = new Contact();

        Optional<Address> address = addressRepository.findByCity(contactDto.getAddress());
        Optional<Ranking> ranking = rankingRepository.findByNumber(contactDto.getRanking());
        Optional<Category> category = categoryRepository.findByTitle(contactDto.getCategory());

        //address
        if (address.isPresent()) {
            contact.setAddress(address.get());
        } else {
            Address addr = new Address();
            addr.setCity(contactDto.getAddress());
            contact.setAddress(addressRepository.save(addr));
        }

        //ranking
        if (ranking.isPresent()) {
            contact.setRanking(ranking.get());
        } else {
            Ranking rank = new Ranking();
            rank.setNumber(contactDto.getRanking());
            contact.setRanking(rankingRepository.save(rank));
        }

        //category
        if (category.isPresent()) {
            contact.setCategory(category.get());
        } else {
            Category cat = new Category();
            cat.setTitle(contactDto.getCategory());
            contact.setCategory(categoryRepository.save(cat));
        }

        //
        contact.setName(contactDto.getName());
        contact.setSurname(contactDto.getSurname());
        contact.setNumber(contactDto.getNumber());

        //tags
        contact.setTags(new ArrayList<>());

        contactRepository.save(contact);

    }


}
