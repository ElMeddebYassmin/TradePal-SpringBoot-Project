package tn.esprit.spring.khaddem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Universite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniversite;
    private String nomUniv;
    private String adresse;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Integer getIdUniversite() {
        return idUniversite;
    }

    public void setIdUniversite(Integer idUniversite) {
        this.idUniversite = idUniversite;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Statut getStatut() {
        return statut;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    @OneToMany(cascade = CascadeType.ALL)
  //  @JsonIgnore
    private List<Departement>departements;
}
