package edge.auth

import groovy.transform.Canonical
import org.hibernate.validator.constraints.Length

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Transient
import javax.validation.constraints.NotNull

/**
 * Model representing a registered User in the db
 */
@Canonical
@Entity
class User {

  @Id
  @GeneratedValue
  Long id

  @NotNull
  @Length(min = 6, max = 255)
  String username

  @NotNull
  String password

  @Transient
  String passwordConfirm

  @Enumerated(EnumType.STRING)
  Role role

}
