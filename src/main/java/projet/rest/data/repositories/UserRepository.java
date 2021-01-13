
package projet.rest.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.rest.data.models.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
