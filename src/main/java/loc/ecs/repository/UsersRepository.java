package loc.ecs.repository;

import loc.ecs.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "/users")
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String userName);
}
