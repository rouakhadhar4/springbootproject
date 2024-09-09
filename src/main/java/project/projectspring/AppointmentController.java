package project.projectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "add_appointment_form";
    }
    @PostMapping("/appointments/add")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        // Logique pour sauvegarder l'objet rendez-vous
        appointmentRepository.save(appointment);
        return "redirect:/appointments/new";
    }

    @PostMapping("/add")
    public String createAppointment(@ModelAttribute Appointment appointment, Model model) {
        try {
            appointmentRepository.save(appointment);
            return "redirect:/appointments";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating appointment: " + e.getMessage());
            return "add_appointment_form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditAppointmentForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
        model.addAttribute("appointment", appointment);
        return "edit_appointment_form";
    }

    @PostMapping("/{id}/update")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute Appointment updatedAppointment) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));

        appointment.setNom(updatedAppointment.getNom());
        appointment.setDate(updatedAppointment.getDate());
        appointment.setMessage(updatedAppointment.getMessage());

        appointmentRepository.save(appointment);

        return "redirect:/appointments";
    }

    @GetMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }

    @GetMapping
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();
        model.addAttribute("appointments", appointments);
        return "appointment_list";
    }
}
