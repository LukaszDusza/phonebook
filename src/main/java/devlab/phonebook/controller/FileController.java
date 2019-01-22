package devlab.phonebook.controller;


import devlab.phonebook.commons.model.MyFile;
import devlab.phonebook.service.ContactService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/dto/files")
public class FileController {


    private ContactService contactService;

    public FileController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/contacts/export")
    public void downloadFile(@RequestParam(name = "file") String file, HttpServletResponse response) {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file);
        response.setStatus(HttpServletResponse.SC_OK);

        try {
            InputStream is = new FileInputStream(contactService.getUploads() + file);
            FileCopyUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(404);
        }

    }

    @GetMapping("/list")
    public List<MyFile> getResources() {

        try {
            return Files.walk(Paths.get(contactService.getUploads()))
                    .filter(Files::isRegularFile)
                    .map(f -> {
                        try {
                            BasicFileAttributes bs = Files.readAttributes(f.toAbsolutePath(), BasicFileAttributes.class);
                            String time =
                                    bs.creationTime().toString().substring(0, 10) + " " +
                                            bs.creationTime().toString().substring(11, 19);
                            return new MyFile(f.getFileName().toString(), f.toAbsolutePath().toString(), time);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @GetMapping("/contacts/save")
    public void saveContactsToFile(@RequestParam(name = "file") String file) {
        contactService.saveContactsToXLSFile(file);
    }

}
