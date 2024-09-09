package project.projectspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String listRooms(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "list-rooms";
    }

    @GetMapping("/add")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("categories", categoryRepository.findAll());
        return "add-room";
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute Room room, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                // Save the file to the disk
                String imagePath = FileUploadUtil.saveFile(imageFile);

                // Construct the HTTP URL for the image path
                String httpImagePath = "/projectspring_projectspring/images" + imagePath;

                // Set the image path in the Room object
                room.setImagePath(httpImagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        roomRepository.save(room);
        return "redirect:/rooms/list";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id) {
        roomRepository.deleteById(id);
        return "redirect:/rooms/list";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Room room = roomRepository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Invalid room id: " + id));
        model.addAttribute("room", room);
        model.addAttribute("categories", categoryRepository.findAll());
        return "update-room"; // Créez la page "update-room.html" pour afficher le formulaire de mise à jour
    }
    @PostMapping("/update")
    public String updateRoom(@ModelAttribute Room room, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            // Enregistrer le fichier sur le disque
            String fileName = FileUploadUtil.saveFile(imageFile);
            // Définir le chemin d'accès de l'image dans l'objet Room en concaténant le nom de fichier avec le chemin du répertoire de téléchargement
            String imagePath = "/images/" + fileName;
            room.setImagePath(imagePath);

            // Assurez-vous que l'imagePath n'est pas nul avant de sauvegarder la chambre
            if(room.getImagePath() == null) {
                // Si imagePath est null, conservez l'ancien chemin d'accès à l'image
                Room existingRoom = roomRepository.findById(room.getId())
                                                  .orElseThrow(() -> new IllegalArgumentException("Invalid room id: " + room.getId()));
                room.setImagePath(existingRoom.getImagePath());
            }

            // Enregistrer la chambre dans la base de données
            roomRepository.save(room);

            // Rediriger vers la liste des chambres après la mise à jour réussie
            return "redirect:/rooms/list";
        } catch (Exception e) {
            // En cas d'erreur, afficher l'exception dans les logs
            e.printStackTrace();
            // Retourner une vue d'erreur ou rediriger vers une page d'erreur personnalisée
            return "error"; // Vous pouvez personnaliser cette partie selon vos besoins
        }
    }

    }









