package edge.auth.service

import edge.auth.User

interface UserService {

  /**
   * Persist a new User to the UserRepository
   * @param user
   */
  void save(User user)

  /**
   * Fetch a User by username
   *
   * @param username String
   * @return User
   */
  User findByUsername(String username)

}
