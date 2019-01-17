package devlab.phonebook.controller;

import devlab.phonebook.model.Category;
import devlab.phonebook.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private ContactService contactService;

    public CategoryController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return contactService.getCategories();
    }
}
