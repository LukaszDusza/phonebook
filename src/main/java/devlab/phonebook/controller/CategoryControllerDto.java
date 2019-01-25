package devlab.phonebook.controller;


import devlab.phonebook.commons.exceptions.NotFoundException;
import devlab.phonebook.dtos.model.CategoryDto;
import devlab.phonebook.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dto")
public class CategoryControllerDto {

    private ContactService contactService;

    public CategoryControllerDto(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/categories")
    public List<CategoryDto> getAllCategoriesDto() {
        return contactService.getCategoriesDto();
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<CategoryDto> getAllCategoriesDtoWithContactsDto(@PathVariable String title) throws NotFoundException {
        return new ResponseEntity<>(contactService.getCategoryWithContactsDto(title), HttpStatus.OK);
    }

}
