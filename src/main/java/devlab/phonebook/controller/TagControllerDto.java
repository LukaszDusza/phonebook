package devlab.phonebook.controller;

import devlab.phonebook.dtos.model.TagDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/dto")
public class TagControllerDto {

    private ContactService contactService;

    public TagControllerDto(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("tags")
    public Collection<TagDto> getTags() {
        return contactService.getAllTags();
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
