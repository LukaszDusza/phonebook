package devlab.phonebook.controller;

import devlab.phonebook.dtos.model.TagDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dto")
public class TagControllerDto {

    ContactService contactService;

    public TagControllerDto(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/tags")
    public ResponseEntity<List<TagDto>>addTagsToContact(@RequestParam String phone, @RequestBody List<String> tags) {
        boolean result = contactService.addTagsToContact(phone, tags);

        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
