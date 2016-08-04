package edge

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Main entry point
 *
 * @author medge
 */
@SpringBootApplication
class AppMain {
  static void main(String[] args) {
    new SpringApplication(AppMain).run(args)
  }
}
