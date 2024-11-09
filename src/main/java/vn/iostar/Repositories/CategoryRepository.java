package vn.iostar.Repositories;

import org.springframework.data.jpa.repository.Query;
import vn.iostar.Entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String name);
    // Page<Category> findByCategoryNameContaining(String keyword, Pageable pageable);  //default

    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %:keyword%")
    Page<Category> findAllContaining(String keyword, Pageable pageable);    //custom
}
