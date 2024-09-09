package project.projectspring;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/formulaire")
public class FormulaireController {

    @Autowired
    private FormulaireRepository formulaireRepository;

    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("formulaire", new Formulaire());
        return "ajouterFormulaire";
    }

    @PostMapping("/ajouter")
    public String ajouterFormulaire(@ModelAttribute("formulaire") Formulaire formulaire) {
        formulaireRepository.save(formulaire);
        return "redirect:/formulaire/ajouter";
    }
}
