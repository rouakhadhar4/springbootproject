package project.projectspring;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;





import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "formulaire")
public class Formulaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private boolean serviceEstBien;

    @Column
    private String comportementPatient;

    @Column
    private String commentaires;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isServiceEstBien() {
        return serviceEstBien;
    }

    public void setServiceEstBien(boolean serviceEstBien) {
        this.serviceEstBien = serviceEstBien;
    }

    public String getComportementPatient() {
        return comportementPatient;
    }

    public void setComportementPatient(String comportementPatient) {
        this.comportementPatient = comportementPatient;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }
}