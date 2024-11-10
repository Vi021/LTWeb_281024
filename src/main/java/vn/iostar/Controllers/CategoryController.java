package vn.iostar.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.iostar.Entities.Category;
import vn.iostar.Services.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping({"/categories", "/category/*"})
public class CategoryController {
    @Autowired
    CategoryService catService;

    @GetMapping
    public String getCategories(ModelMap model) {
        int count = (int) catService.count();
        int currentPage = 1;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(0, pageSize, Sort.by("categoryName"));
        Page<Category> resultPage = catService.findAll(pageable);

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = 1;
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > count) {
                if (end == totalPages)
                {
                    start = end - count;
                }
                else {
                    end = start + count;
                }
            }

            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("categoryPage", resultPage);
        return "category/list";
    }

    @GetMapping("/category/new")
    public String newCategory(ModelMap model) {
        model.addAttribute("category", new Category());
        return "category/category-add";
    }

    @PostMapping("category/add")
    public String addCategory(@Valid Category category, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "category/category-add";
        }

        catService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/update/{id}")
    public String updateCategory(@PathVariable int id, ModelMap model) {
        Category category = catService.findById(id).orElseThrow(()->new RuntimeException("No category found"));
        model.addAttribute("category", category);
        return "category/category-edit";
    }

    @PostMapping("/category/edit/{id}")
    public String editCategory(@PathVariable int id, @Valid Category category, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "category/category-edit";
        }

        catService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/remove/{id}")
    public String removeCategory(@PathVariable int id) {
        catService.deleteById(id);
        return "redirect:/categories";
    }

    @RequestMapping("/category/searchpaginated")
    public String searchPaginated(ModelMap model,
                                  @RequestParam(name="name", required=false) String categoryName,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        int count = (int) catService.count();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<Category> resultPage;
        if(StringUtils.hasText(categoryName)) {
            resultPage = catService.findAllContaining(categoryName, pageable);
            model.addAttribute("categoryPage", resultPage);
        }
        else {
            resultPage = catService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = 1;
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > count) {
                if (end == totalPages) {
                    start = end - count;
                }
                else {
                    end = start + count;
                }
            }

            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("categoryPage", resultPage);
        return "category/list";
    }
}
