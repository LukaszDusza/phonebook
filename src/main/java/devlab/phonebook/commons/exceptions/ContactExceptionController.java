package devlab.phonebook.commons.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactExceptionController {

    @ExceptionHandler(value = ContactNotfoundException.class)
    public ResponseEntity<ErrorMessage> exception(ContactNotfoundException exception) {
        return new ResponseEntity<>(new ErrorMessage("Contact not found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ContactAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> exception(ContactAlreadyExistException exception) {
        return new ResponseEntity<>(new ErrorMessage("Contact already exist", HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }
}
