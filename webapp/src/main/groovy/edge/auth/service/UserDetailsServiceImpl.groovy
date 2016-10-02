package edge.auth.service

import edge.auth.User
import edge.auth.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Fetch user details from the app's UserRepository
 */
@Service
class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository

  @Override
  @Transactional(readOnly = true)
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)

    if(!user)
      throw new UsernameNotFoundException("User ${username} not found!")

    // Transform user Roles into a Set<GrantedAuthority>
    Set<GrantedAuthority> authorities =
      user.getRole()
        .collect { role -> new SimpleGrantedAuthority(role.name()) }

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities)
  }
}
