package loc.ecs.repository;

import loc.ecs.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "product", path = "/product")
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query(value = "SELECT * FROM ecs_products ORDER BY product_id DESC LIMIT 10;", nativeQuery = true)
    List<Products> getLastTenRows();
}
