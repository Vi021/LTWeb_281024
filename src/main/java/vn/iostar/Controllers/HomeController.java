package vn.iostar.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import vn.iostar.Entities.Category;
import vn.iostar.Services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Controller
public class HomeController {
    @Autowired
    CategoryService catService;

    @GetMapping("/")
    public String home(ModelMap model) {
//        long count = catService.count();
//        int currentPage = 1;
//        int pageSize = 10;
//
//        Pageable pageable = PageRequest.of(0, pageSize, Sort.by("categoryName"));
//        Page<Category> resultPage = catService.findAll(pageable);
//
//        int totalPages = resultPage.getTotalPages();
//        if (totalPages > 0) {
//            long start = 1;
//            long end = Math.min(currentPage + 2, totalPages);
//
//            if (totalPages > count) {
//                if (end == totalPages)
//                {
//                    start = end - count;
//                }
//                else {
//                    end = start + count;
//                }
//            }
//
//            List<Long> pageNumbers = LongStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        model.addAttribute("categoryPage", resultPage);
//        return "category/list";
        return "home";
    }
}
