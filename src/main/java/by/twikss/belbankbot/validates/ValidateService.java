package by.twikss.belbankbot.validates;

import by.twikss.belbankbot.registration.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@Component
public class ValidateService {


    public String validateClient(Client client) {
        String errors = null;
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        for (ConstraintViolation<Client> violation : violations) {
            errors = violation.getMessage();
        }
        return errors;
    }
}
