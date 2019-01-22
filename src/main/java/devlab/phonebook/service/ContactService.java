package devlab.phonebook.service;

import devlab.phonebook.commons.xlsCreator.CreatorXLS;
import devlab.phonebook.dtos.mappers.CategoryMapper;
import devlab.phonebook.dtos.mappers.ContactMapper;
import devlab.phonebook.dtos.model.CategoryDto;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.dtos.model.TagDto;
import devlab.phonebook.model.*;
import devlab.phonebook.repository.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.ServletContext;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// adnotacja logiki biznesowej. Adnotacja Service, Controller, Repository,
// to jest to samo co adnotacja Component plus dodatkowe funkcjonaloności.
// oznaczamy, że ta klasa to wartwa biznesowan aplikacji i służy do pobierania danych z bazy danych
// i manipulowania numi.
@Service
public class ContactService {

      private final static Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private ContactRepository contactRepository;
    private AddressRepository addressRepository;
    private CategoryRepository categoryRepository;
    private RankingRepository rankingRepository;
    private TagRepository tagRepository;
    private ContactMapper contactMapper;
    private CategoryMapper categoryMapper;

    private String uploads;
    private ServletContext servletContext;

    // @Autowired - adnotacja ta pozwala nam, na automatyczne tworzenie obiektów, które są zależnościami tej klasy.
    // Czyli do stworzenia obiektu klasy ContactService potrzebujemy posiadać obiekty repozytorów.
    // Autowired, to Dependency Injection, czyli wstrzykiwanie zależnośći. Spring storzy obiekty tych zależności.
    // Najlepiej korzystać z tej unkcji na konstruktorze klasy. Moża natomiast również oznaczyć ta adnotacją pole klasy oraz metodę.
    public ContactService(ContactRepository contactRepository,
                          AddressRepository addressRepository,
                          CategoryRepository categoryRepository,
                          RankingRepository rankingRepository,
                          TagRepository tagRepository,
                          ContactMapper contactMapper,
                          CategoryMapper categoryMapper,
                          ServletContext servletContext) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.categoryRepository = categoryRepository;
        this.rankingRepository = rankingRepository;
        this.tagRepository = tagRepository;
        this.contactMapper = contactMapper;
        this.categoryMapper = categoryMapper;
        this.servletContext = servletContext;
        createDirectory();
    }

    private void createDirectory() {
        uploads = servletContext.getRealPath("/uploads/");
        System.out.println(uploads);
        Path path = Paths.get(uploads);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public void addNewContact(Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> getContactsByName(String name) {
        return contactRepository.getContactsByName(name);
    }

    //DTO
    public List<ContactDto> getAllDtoContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactDto> contactsDto = new ArrayList<>();
        for (Contact c : contacts) {
            contactsDto.add(contactMapper.map(c));
        }
        return contactsDto;
    }

    //DTO
    public List<ContactDto> getContactsDtoByName(String name) {
        List<ContactDto> contactsDto = new ArrayList<>();
        for (Contact c : contactRepository.getContactsByName(name)) {
            contactsDto.add(contactMapper.map(c));
        }
        return contactsDto;
    }

    //DTO
    public boolean updateAddressByPhone(String phone, String city) {

        Optional<Contact> contactOptional = contactRepository.findContactByPhoneNumber(phone);

        if (contactOptional.isPresent()) {
            Optional<Address> addr = addressRepository.findByCity(city);

            if (addr.isPresent()) {
                contactOptional.get().setAddress(addr.get());
                contactRepository.save(contactOptional.get());

            } else {
                Address address = new Address();
                address.setCity(city);
                contactOptional.get().setAddress(addressRepository.save(address));
                contactRepository.save(contactOptional.get());
            }
            return true;
        }
        return false;

    }

    //DTO
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
        Set<Tag> emptyTags = new HashSet<>();
        contact.setTags(emptyTags);

        //save
        contactRepository.save(contact);
    }

    //DTO
    public boolean deleteContactByPhone(String phone) {
        Optional<Contact> contactOptional = contactRepository.findContactByPhoneNumber(phone);

        if (contactOptional.isPresent()) {
            contactRepository.deleteById(contactOptional.get().getId());
            return true;
        }
        return false;
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    //DTO
    public List<CategoryDto> getCategoriesDto() {

        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category c : categoryRepository.findAll()) {
            categoryDtos.add(categoryMapper.map(c));
        }
        return categoryDtos;
    }

    //DTO
    public CategoryDto getCategoryWithContactsDto(String title) {

        Optional<Category> category = categoryRepository.findByTitle(title);

        if (category.isPresent()) {
            List<ContactDto> contactDtos = new ArrayList<>();
            for (Contact c : contactRepository.findContactsByCategory(title)) {
                contactDtos.add(contactMapper.map(c));
            }
            return new CategoryDto(title, contactDtos);
        }
        return null;
    }




    public boolean addTagsToContact(String phone, List<String> tagsList) {
        Optional<Contact> contact = contactRepository.findContactByPhoneNumber(phone);
        if (contact.isPresent()) {

            for (String t : tagsList) {

                Optional<Tag> tagOptional = tagRepository.findByTitle(t);

                if (!tagOptional.isPresent()) {
                    contact.get().getTags().add(tagRepository.save(new Tag(t)));
                } else {
                    contact.get().getTags().add(tagOptional.get());
                }
            }

            contactRepository.save(contact.get());
            return true;
        }
        return false;
    }

    //DTO
    public List<Contact> getContactsByTag(String title) {
        return contactRepository.getContactsByTag(title);
    }

    public List<ContactDto> getContactsDtoByTag(String title) {

        List<ContactDto> contactDtos = new ArrayList<>();

        for (Contact c : contactRepository.getContactsByTag(title)) {
            contactDtos.add(contactMapper.map(c));
        }
        return contactDtos;
    }

    public void saveContactsToXLSFile(String file) {
        LOGGER.log(Level.INFO, uploads);
        CreatorXLS<ContactDto> creatorXLS = new CreatorXLS<>(ContactDto.class);
        List<ContactDto> contactDtos = new ArrayList<>();

     //   contactRepository.findAll().stream().map( c -> contactDtos.add(contactMapper.map(c)));

        for (Contact c:  contactRepository.findAll()) {
            contactDtos.add(contactMapper.map(c));
        }

        try {
            creatorXLS.createFile(contactDtos, uploads, file);
        } catch (IOException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String getUploads() {
        return uploads;
    }
}
