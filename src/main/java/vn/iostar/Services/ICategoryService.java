package vn.iostar.Services;

import org.springframework.data.domain.Sort;
import vn.iostar.Entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> findAll();
    List<Category> findAll(Sort sort);
    <S extends Category> S save(S entity);
    Optional<Category> findById(int id);
    Optional<Category> findByName(String name);
    void deleteById(int id);
    long count();
}
