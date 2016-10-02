package edge.auth

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Registered User's repository
 */
interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username)
}
