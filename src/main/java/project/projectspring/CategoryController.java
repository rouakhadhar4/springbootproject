package project.projectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @ResponseBody
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    @PostMapping("/add")
    public String createCategory(@ModelAttribute Category category, Model model) {

        if (category.getName() == null || category.getName().isEmpty()) {
            model.addAttribute("error", "Category name cannot be empty");
            return "add-category";
        }

        try {
            categoryRepository.save(category);
            return "redirect:/categories/list";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating category: " + e.getMessage());
            return "add-category";
        }
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }
    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category updatedCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        // Update category details
        category.setName(updatedCategory.getName());

        // Save the updated category
        categoryRepository.save(category);

        // Redirect to the list page after update
        return "redirect:/categories/list";
    }


    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        categoryRepository.delete(category);
        
        return "redirect:/categories/list";
    }




    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @GetMapping("/list")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "list-category";
    }
    @GetMapping("/update/{id}")
    public String showUpdateCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        model.addAttribute("category", category); // Ajouter l'objet category au modèle
        return "update-category";
    }
    @GetMapping("/search")
    public String searchCategories(@RequestParam("name") String name, Model model) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("categories", categories);
        return "list-category";
    }



}
