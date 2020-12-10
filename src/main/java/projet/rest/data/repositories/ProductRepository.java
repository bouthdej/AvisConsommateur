package projet.rest.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.rest.data.models.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
