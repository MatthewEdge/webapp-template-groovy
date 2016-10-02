package edge.auth

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@Slf4j
class LoginHelper {

  @Autowired
  AuthenticationManager authenticationManager

  @Autowired
  UserDetailsService userDetailsService
  
  void autoLogin(String username, String password) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username)
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities())

    authenticationManager.authenticate(usernamePasswordAuthenticationToken)

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken)

      log.debug("Auto login for ${username} succeeded!")
    }
  }
}
