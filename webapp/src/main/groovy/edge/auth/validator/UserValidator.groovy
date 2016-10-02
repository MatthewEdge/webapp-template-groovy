package edge.auth.validator

import edge.auth.User
import edge.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

/**
 * User Form validator
 */
@Component
public class UserValidator implements Validator {

  @Autowired
  private UserService userService

  @Override
  public boolean supports(Class<?> aClass) {
    return User.class == aClass
  }

  @Override
  public void validate(Object o, Errors errors) {
    User user = (User) o

    // Validate password first to defer call to the UserService
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty")

    if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
      errors.rejectValue("password", "Size.userForm.password")
    }

    if (user.getPasswordConfirm() != user.getPassword()) {
      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm")
    }

    // Validate username
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty")

    if (userService.findByUsername(user.getUsername()) != null) {
      errors.rejectValue("username", "Duplicate.userForm.username")
    }

  }
}
