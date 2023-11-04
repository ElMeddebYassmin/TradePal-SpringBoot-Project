package tn.esprit.spring.khaddem.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.services.IUniversiteService;
import java.util.List;

@Tag(name = "Gestion des universités")
@RestController
@RequestMapping("/universite")
@Tag(name = "Universite", description = "Gestion des universités")
@CrossOrigin(origins = "http://localhost:4200")
public class UniversiteRestController {
    @Autowired
    IUniversiteService universiteService;

    // http://localhost:8089/Kaddem/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    @Operation(description = "récupérer la liste des universités")
    @ResponseBody
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        return listUniversites;
    }

    // http://localhost:8089/Kaddem/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universiteId}")
    @Operation(description = "récupérer une université par son id")
    @ResponseBody
    public Universite retrieveUniversite(@PathVariable("universiteId") Integer universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    // http://localhost:8089/Kaddem/universite/add-universite
    @PostMapping("/add-universite")
    @ResponseBody
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversite(u);
        return universite;
    }

    // http://localhost:8089/Kaddem/universite/update-universite/{id}
    @PutMapping("/update-universite/{idUniversite}")
    @ResponseBody
    Universite updateUniversite(@PathVariable("idUniversite") Integer idUniversite, @RequestBody Universite updatedUniversite) {
        Universite existingUniversite = universiteService.retrieveUniversite(idUniversite);
        existingUniversite.setNomUniv(updatedUniversite.getNomUniv());
        existingUniversite.setAdresse(updatedUniversite.getAdresse());
        existingUniversite.setStatut(updatedUniversite.getStatut());
        existingUniversite.setDateCreation(updatedUniversite.getDateCreation());
        Universite updatedUniversiteEntity = universiteService.updateUniversite(existingUniversite, idUniversite);
        return updatedUniversiteEntity;
    }

    // http://localhost:8089/Kaddem/universite/remove-universite/{id}
    @DeleteMapping("/remove-universite/{idUniversite}")
    @Operation(description = "supprimer une université par ID")
    @ResponseBody
    public void removeUniversite(@PathVariable("idUniversite") Integer idUniversite) {
        universiteService.removeUniversite(idUniversite);
    }

    // http://localhost:8089/Kaddem/universite/assignUniversiteToDepartement/1/1
    @PutMapping("/assignUniversiteToDepartement/{universiteId}/{departementId}")
    @Operation(description = "assigner une université à un département")
    @ResponseBody
    public void assignUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId,@PathVariable("departementId") Integer departementId) {
        universiteService.assignUniversiteToDepartement(universiteId,departementId);
    }
}
