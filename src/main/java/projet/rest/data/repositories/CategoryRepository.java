package projet.rest.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.rest.data.models.CategoryEntity;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Integer>{

}
