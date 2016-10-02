package edge

import edge.auth.User
import edge.auth.annotation.AuthUser
import groovy.transform.Canonical
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Canonical
@Controller
class IndexController {

  @GetMapping(["/"])
  String index(@AuthUser User user, Model model) {

    // If user has not logged in: make sure Login form shows
    if(!user) {
      model.addAttribute("loggedIn", false)
    }

    return "index"
  }

}
