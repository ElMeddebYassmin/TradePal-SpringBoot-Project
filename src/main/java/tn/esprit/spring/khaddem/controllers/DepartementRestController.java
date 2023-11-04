package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.services.IDepartementService;

import java.util.List;

@RestController
@RequestMapping("/departement")
@Tag(name = "Departement", description = "Gestion des départements")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartementRestController {
    @Autowired
    IDepartementService departementService;
    // http://localhost:8089/Kaddem/departement/retrieve-all-departements
    @GetMapping("/retrieve-all-departements")
    @ResponseBody
    public List<Departement> getDepartements() {
        List<Departement> listDepartements = departementService.retrieveAllDepartements();
        return listDepartements;
    }

    // http://localhost:8089/Kaddem/departement/retrieve-departement/8
    @GetMapping("/retrieve-departement/{departement-id}")
    @ResponseBody
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }

    // http://localhost:8089/Kaddem/departement/add-departement
    @PostMapping("/add-departement")
    @ResponseBody
    public Departement addDepartement(@RequestBody Departement d) {
        departementService.addDepartement(d);
        return d;
    }

    // http://localhost:8089/Kaddem/departement/update-departement/{id}
    @PutMapping("/update-departement/{id}")
    @ResponseBody
    public Departement updateDepartement(@PathVariable Integer id, @RequestBody Departement updatedDepartement) {
        Departement existingDepartement = departementService.retrieveDepartement(id);
        existingDepartement.setNomDepart(updatedDepartement.getNomDepart());
        Departement updatedDepartementEntity = departementService.updateDepartement(existingDepartement, id);
        return updatedDepartementEntity;
    }

    // http://localhost:8089/Kaddem/Departement/remove-departement/{id}
    @DeleteMapping("/delete-departement/{idDepartement}")
    @ResponseBody
    public void removeDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        departementService.removeDepartement(idDepartement);
    }



    // http://localhost:8089/Kaddem/departement/retrieveDepartementsByDepartement/1
    @GetMapping("/retrieveDepartementsByDepartement/{idDepartement}")
    @ResponseBody
    public List<Departement> retrieveDepartementsByDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        List<Departement> listDepartements = departementService.retrieveDepartementsByUniversite(idDepartement);
        return listDepartements;
    }


}
