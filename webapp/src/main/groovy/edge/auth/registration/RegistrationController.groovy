package edge.auth.registration

import edge.auth.LoginHelper
import edge.auth.User
import edge.auth.service.UserService
import edge.auth.validator.UserValidator
import groovy.transform.Canonical
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

/**
 * User Registration controller
 *
 * @author medge
 */
@Canonical
@Controller
@Slf4j
class RegistrationController {

  @Autowired UserValidator userValidator

  @Autowired UserService userService

  @Autowired LoginHelper loginHelper

  @GetMapping("/registration")
  String registration(Model model) {
    model.addAttribute("userForm", new User())

    return "registration"
  }

  @PostMapping("/registration")
  String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

    // Additional validations not found on the User model
    userValidator.validate(userForm, bindingResult)

    if (bindingResult.hasErrors()) {
      log.debug("Registration form has errors: ${bindingResult.allErrors.toListString()}")

      return "registration"
    }

    // All is well. Persist the new user and auto-login for convenience
    userService.save(userForm)
    loginHelper.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm())

    return "redirect:/welcome"
  }
}
