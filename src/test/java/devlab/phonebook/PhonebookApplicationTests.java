package devlab.phonebook;

import devlab.phonebook.commons.exceptions.NotFoundException;
import devlab.phonebook.controller.ContactController;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;

import java.util.logging.Level;
import java.util.logging.Logger;


@RunWith(SpringRunner.class)
@WebMvcTest
public class PhonebookApplicationTests {

    private static final Logger LOGGER =  Logger.getLogger(PhonebookApplicationTests.class.getName());


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;


    @BeforeClass
    public static void beforeClass() {

        LOGGER.log(Level.INFO, "Start Testing...");
    }

    @Before
    public void beforeSetup() {
     //   LOGGER.log(Level.INFO, "Set Web Selennium Driver");

    }

    @Test
    public void whenFindByContactName() throws Exception {

        LOGGER.log(Level.INFO, "Start Mockito REST API test...");

        //given
        ContactDto mockContact = ContactDto
                .builder()
                .name("Maciej")
                .address("Gdynia")
                .category("work")
                .number("78965413")
                .ranking(3)
                .tags(Arrays.asList("horror",
                        "poland",
                        "party"))
                .surname("Nowacki")
                .build();

        //when
        Mockito.when(
                contactService.getContactDtoByNumber(Mockito.anyString())
        ).thenReturn(mockContact);

       // System.out.println(mockContact);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/api/dto/contacts/number")
                .param("number", "78965413")
                .contentType("application/json;charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    //    System.out.println(result.getResponse());

        String expected = "{\n" +
                "  \"name\": \"Maciej\",\n" +
                "  \"surname\": \"Nowacki\",\n" +
                "  \"number\": \"78965413\",\n" +
                "  \"address\": \"Gdynia\",\n" +
                "  \"category\": \"work\",\n" +
                "  \"ranking\": 3,\n" +
                "  \"tags\": [\n" +
                "    \"horror\",\n" +
                "    \"poland\",\n" +
                "    \"party\"\n" +
                "  ]\n" +
                "}";

        //then
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

//     todo - napisać testy Mockito


    }

//    @Test
//    public void testTitlePage() throws InterruptedException {
//
//        LOGGER.log(Level.INFO, "Start tile page test...");
//        Thread.sleep(2000);
//
//        //1
//        assertEquals(titlePage, wd.getTitle());
//
//    }

//    @Test
//    public void testContentInPage() throws InterruptedException {
//
//        LOGGER.log(Level.INFO, "Start test content on page...");
//
//        String text = wd.findElement(By.tagName("H3")).getText();
//        Thread.sleep(2000);
//        wd.close();
//
//        //1
//        assertEquals("Welcome to my PhoneBook App!", text);
//
//    }



}

