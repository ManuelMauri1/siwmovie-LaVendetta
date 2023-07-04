package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotBlank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotBlank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank");
        User user = (User) target;
        if(userRepository.existsByEmail(user.getEmail()))
            errors.reject("user.email.duplicati");
    }
}
