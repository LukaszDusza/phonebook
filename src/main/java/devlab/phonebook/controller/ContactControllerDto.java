package devlab.phonebook.controller;


import devlab.phonebook.commons.exceptions.NotFoundException;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dto")
public class ContactControllerDto {

    private ContactService contactService;


    public ContactControllerDto(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<ContactDto> getAllDtoContacts() {
        return contactService.getAllDtoContactsTwo();
    }

    @GetMapping("/contacts/{name}")
    public List<ContactDto> getAllContacts(@PathVariable String name) {
        return contactService.getContactsDtoByName(name);
    }

    @ResponseBody
    @GetMapping("/contacts/number")
    public ResponseEntity<?> getContactDtoByNumber(@RequestParam String number) {
        try {
            return new ResponseEntity<>(contactService.getContactDtoByNumber(number), HttpStatus.OK); //200
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); //404
        }
    }


    @PostMapping("/contacts")
    public void addNew(@RequestBody ContactDto contactDto) {
        contactService.addNewContactDTO(contactDto);
    }

    @PutMapping("/contacts")
    public ResponseEntity<?> updateContact(@RequestParam(name = "phone") String phone, @RequestParam(name = "city") String city) {
        boolean result = contactService.updateAddressByPhone(phone, city);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/contacts")
    public ResponseEntity<?> deleteContact(@RequestParam(name = "phone") String phone) {

        try {
            return ResponseEntity.ok().body(contactService.deleteContactByPhone(phone));

        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @GetMapping("/contacts/tag")
    public List<ContactDto> getContactsByTag(@RequestParam String title) {
        return contactService.getContactsDtoByTag(title);
    }

    //metoda dodajaca naglowek do odpowiedzi
    @GetMapping("/contacts/header")
    public ResponseEntity<List<Contact>> getContactsResponse() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Przykladowy-naglowek",
                "przykladowa-wartosc-naglowka");

        return ResponseEntity.ok().headers(responseHeaders).body(contactService.getContacts());
    }

}
