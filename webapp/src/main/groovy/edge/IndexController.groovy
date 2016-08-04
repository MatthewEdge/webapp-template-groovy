package edge

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author medge
 */
@Controller
class IndexController {

  @GetMapping("/")
  String index() {
    "index"
  }

}
