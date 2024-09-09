package project.projectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/billings")
public class BillingController {

    @Autowired
    private BillingRepository billingRepository;

    @GetMapping("/list")
    public String listBillings(Model model) {
        List<Billing> billings = billingRepository.findAll();
        model.addAttribute("billings", billings);
        return "list-billings";
    }
    @GetMapping("/search")
    public String searchBillingsByName(@RequestParam("name") String name, Model model) {
        List<Billing> billings = billingRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("billings", billings);
        return "list-billings"; 
    }


    @GetMapping("/add")
    public String showAddBillingForm(Model model) {
        model.addAttribute("billing", new Billing());
        return "add-billing";
    }

    @PostMapping("/add")
    public String addBilling(@ModelAttribute Billing billing, BindingResult bindingResult) {
        // Validation des données
        if (bindingResult.hasErrors()) {
            // Si des erreurs de validation sont détectées, retourner à la page du formulaire avec les erreurs
            return "add-billing";
        }
        // Si la validation réussit, enregistrer la facture et rediriger vers la liste des factures
        billingRepository.save(billing);
        return "redirect:/billings/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBillingForm(@PathVariable("id") Long id, Model model) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid billing id: " + id));
        model.addAttribute("billing", billing);
        return "update-billing";
    }

    @PostMapping("/update")
    public String updateBilling(@ModelAttribute Billing billing) {
        billingRepository.save(billing);
        return "redirect:/billings/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBilling(@PathVariable("id") Long id) {
        billingRepository.deleteById(id);
        return "redirect:/billings/list";
    }
}

