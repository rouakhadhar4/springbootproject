package project.projectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock-medicaments")
public class StockMedicamentsController {

    @Autowired
    private StockMedicamentsRepository stockMedicamentsRepository;

    @GetMapping("/list")
    public String listStockMedicaments(Model model) {
        List<StockMedicaments> stockMedicamentsList = stockMedicamentsRepository.findAll();
        model.addAttribute("stockMedicamentsList", stockMedicamentsList);
        return "list-stock-medicaments";
    }

    @GetMapping("/add")
    public String showAddStockMedicamentsForm(Model model) {
        model.addAttribute("stockMedicaments", new StockMedicaments());
        return "add-stock-medicaments";
    }

    @PostMapping("/add")
    public String addStockMedicaments(@ModelAttribute StockMedicaments stockMedicaments) {
        stockMedicamentsRepository.save(stockMedicaments);
        return "redirect:/stock-medicaments/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateStockMedicamentsForm(@PathVariable("id") Long id, Model model) {
        StockMedicaments stockMedicaments = stockMedicamentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid stock medicaments id: " + id));
        model.addAttribute("stockMedicaments", stockMedicaments);
        return "update-stock-medicaments";
    }

    @PostMapping("/update")
    public String updateStockMedicaments(@ModelAttribute StockMedicaments stockMedicaments) {
        // Récupérer l'objet stockMedicaments correspondant à l'ID
        StockMedicaments existingStockMedicaments = stockMedicamentsRepository.findById(stockMedicaments.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid stock medicaments id: " + stockMedicaments.getId()));

        // Mettre à jour les propriétés de l'objet avec les nouvelles valeurs
        existingStockMedicaments.setName(stockMedicaments.getName());
        existingStockMedicaments.setQuantity(stockMedicaments.getQuantity());
        existingStockMedicaments.setExpirationDate(stockMedicaments.getExpirationDate());
        existingStockMedicaments.setManufacturer(stockMedicaments.getManufacturer());
        existingStockMedicaments.setUnitPrice(stockMedicaments.getUnitPrice());

        // Sauvegarder l'objet mis à jour dans le repository
        stockMedicamentsRepository.save(existingStockMedicaments);

        // Rediriger vers la liste des stockMedicaments
        return "redirect:/stock-medicaments/list";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteStockMedicaments(@PathVariable("id") Long id) {
        stockMedicamentsRepository.deleteById(id);
        return "redirect:/stock-medicaments/list";
    }
}

