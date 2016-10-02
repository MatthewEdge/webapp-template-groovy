package edge

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args)
  }


//  @Configuration
//  static class MvcConfig extends WebMvcConfigurerAdapter {
//    void addViewControllers(ViewControllerRegistry registry) {
//      registry.addViewController("/login").setViewName("login");
//    }
//  }

}
