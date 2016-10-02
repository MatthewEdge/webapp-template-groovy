package edge.auth.service

import edge.auth.Role
import edge.auth.User
import edge.auth.UserRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Service to
 */
@Service
@Slf4j
class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository

  @Autowired
  private PasswordEncoder passwordEncoder

  @Override
  public void save(User user) {

    log.debug("Saving new user ${user}")

    // Encrypt password before persisting
    def decorated = user.copyWith(
      password: passwordEncoder.encode(user.getPassword()),
      role: Role.USER
    )

    userRepository.save(decorated)
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
  }
}
