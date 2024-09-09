package project.projectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import io.micrometer.common.util.StringUtils;
import java.time.LocalDate; 

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String listMedicalRecords(Model model) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        model.addAttribute("medicalRecords", medicalRecords);
        return "list-medical-records";
    }

    @GetMapping("/add")
    public String showAddMedicalRecordForm(Model model) {
        model.addAttribute("medicalRecord", new MedicalRecord());
        model.addAttribute("categories", categoryRepository.findAll());
        return "add-medical-record";
    }

    @PostMapping("/add")
    public String addMedicalRecord(@ModelAttribute MedicalRecord medicalRecord) {
        // Définir la date sur la date actuelle (système)
        medicalRecord.setDate(LocalDate.now());

        // Sauvegarder le MedicalRecord
        medicalRecordRepository.save(medicalRecord);

        // Rediriger vers la liste des enregistrements médicaux
        return "redirect:/medical-records/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateMedicalRecordForm(@PathVariable("id") Long id, Model model) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid medical record id: " + id));
        model.addAttribute("medicalRecord", medicalRecord);
        model.addAttribute("categories", categoryRepository.findAll());
        return "update-medical-record";
    }
    @PostMapping("/update")
    public String updateMedicalRecord(@ModelAttribute MedicalRecord medicalRecord) {
        // Récupérer la date du formulaire
        LocalDate date = medicalRecord.getDate();
        
        // Vérifier si la date est null
        if (date == null) {
            // Si la date est null, récupérer la date actuelle
            date = LocalDate.now();
        }
        
        // Définir la date mise à jour dans l'objet MedicalRecord
        medicalRecord.setDate(date);

        // Sauvegarder le MedicalRecord mis à jour
        medicalRecordRepository.save(medicalRecord);

        // Rediriger vers la liste des enregistrements médicaux
        return "redirect:/medical-records/list";
    }





    @DeleteMapping("/delete/{id}")
    public String deleteMedicalRecord(@PathVariable("id") Long id) {
        medicalRecordRepository.deleteById(id);
        return "redirect:/medical-records/list";
    }
}
