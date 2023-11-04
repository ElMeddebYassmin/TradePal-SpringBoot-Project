package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
@Tag(name = "Etudiant", description = "Gestion des etudiants")
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantRestController {

    @Autowired
    IEtudiantService etudiantService;
    // http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-etudiants")
    @ResponseBody
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    // http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiantId}")
    @ResponseBody
    public Etudiant retrieveContrat(@PathVariable("etudiantId") Integer etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    // http://localhost:8089/Kaddem/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    @ResponseBody
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant= etudiantService.addEtudiant(e);
        return etudiant;
    }

    // http://localhost:8089/Kaddem/etudiant/update-etudiant/{id}
    @PutMapping("/update-etudiant/{idEtudiant}")
    @ResponseBody
    public Etudiant updateEtudiant(@PathVariable("idEtudiant") Integer idEtudiant, @RequestBody Etudiant updatedEtudiant) {
        Etudiant existingEtudiant = etudiantService.retrieveEtudiant(idEtudiant);
        existingEtudiant.setPrenomE(updatedEtudiant.getPrenomE());
        existingEtudiant.setNomE(updatedEtudiant.getNomE());
        existingEtudiant.setOp(updatedEtudiant.getOp());        
        Etudiant updatedEtudiantEntity = etudiantService.updateEtudiant(existingEtudiant, idEtudiant);
        return updatedEtudiantEntity;
    }
    
    // http://localhost:8089/Kaddem/etudiant/remove-etudiant/{id}
    @DeleteMapping("/remove-etudiant/{idEtudiant}")
    @ResponseBody
    public void removeEtudiant(@PathVariable("idEtudiant") Integer idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
    }

    // http://localhost:8089/Kaddem/etudiant/assignEtudiantToDepartement/1/1
    @PutMapping("/assignEtudiantToDepartement/{etudiantId}/{departementId}")
    @ResponseBody
    public void assignEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId
            ,@PathVariable("departementId") Integer departementId) {
        etudiantService.assignEtudiantToDepartement(etudiantId,departementId);
    }

    // http://localhost:8089/Kaddem/etudiant/findByDepartement/1
    @GetMapping("/findByDepartement/{departement-id}")
    @ResponseBody
    public List<Etudiant> findByDepartement(@PathVariable("departement-id") Integer departementId) {
        return etudiantService.findByDepartementIdDepartement(departementId);
    }

    // http://localhost:8089/Kaddem/etudiant/findByEquipesNiveau/JUNIOR
    @GetMapping("/findByEquipesNiveau/{niveau}")
    @ResponseBody
    public List<Etudiant> findByEquipesNiveau(@PathVariable("niveau") Niveau niveau) {
        return etudiantService.findByEquipesNiveau(niveau);
    }

    // http://localhost:8089/Kaddem/etudiant/retrieveEtudiantsByContratSpecialite/SECURITE
    @GetMapping("/retrieveEtudiantsByContratSpecialite/{specialite}")
    @ResponseBody
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(@PathVariable("specialite") Specialite specialite) {
        return etudiantService.retrieveEtudiantsByContratSpecialite(specialite);
    }

    // http://localhost:8089/Kaddem/etudiant/retrieveEtudiantsByContratSpecialiteSQL/SECURITE
    @GetMapping("/retrieveEtudiantsByContratSpecialiteSQL/{specialite}")
    @ResponseBody
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(@PathVariable("specialite") String specialite) {
        return etudiantService.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    // http://localhost:8089/Kaddem/etudiant/addAndAssignEtudiantToEquipeAndContract/1/1
    @PostMapping("/addAndAssignEtudiantToEquipeAndContract/{equipeId}/{contratId}")
    @ResponseBody
    public void addAndAssignEtudiantToEquipeAndContract(@RequestBody Etudiant etudiant,@PathVariable("contratId") Integer contratId,@PathVariable("equipeId") Integer equipeId) {
        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant,contratId,equipeId);
    }

    // http://localhost:8089/Kaddem/etudiant/getEtudiantsByDepartement/1
    @GetMapping("/getEtudiantsByDepartement/{idDepartement}")
    @ResponseBody
    public List<Etudiant> getEtudiantsByDepartement(@PathVariable("idDepartement") Integer idDepartement) {
        return etudiantService.getEtudiantsByDepartement(idDepartement);
    }
}
