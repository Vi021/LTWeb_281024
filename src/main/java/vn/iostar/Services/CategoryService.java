package vn.iostar.Services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.iostar.Entities.Category;
import vn.iostar.Repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    CategoryRepository catRepos;

    @Override
    public List<Category> findAll() {
        return catRepos.findAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return catRepos.findAll(sort);
    }

    @Override
    public <S extends Category> S save(S entity) {
        return catRepos.save(entity);
    }

    @Override
    public Optional<Category> findById(int id) {
        return catRepos.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return catRepos.findByCategoryName(name);
    }

    @Override
    public void deleteById(int id) {
        catRepos.deleteById(id);
    }

    @Override
    public long count() {
        return catRepos.count();
    }

    public Page<Category> findAllContaining(String keyword, Pageable pageable) {
        return catRepos.findAllContaining(keyword, pageable);
    }

    public Page<Category> findAll(Pageable pageable) {
        return catRepos.findAll(pageable);
    }
}
