package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.services.IContratService;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
@Tag(name = "Contrat", description = "Gestion des contrats")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratRestController {
    IContratService contratService;

    // http://localhost:8089/Kaddem/contrat/retrieve-all-contrats
    @GetMapping("/retrieve-all-contrats")
    @ResponseBody
    public List<Contrat> getContrats() {
        List<Contrat> listContrats = contratService.retrieveAllContrats();
        return listContrats;
    }

    // http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
    @GetMapping("/retrieve-contrat/{contrat-id}")
    @ResponseBody
    public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
        return contratService.retrieveContrat(contratId);
    }

    // http://localhost:8089/Kaddem/contrat/add-contrat
    @PostMapping("/add-contrat")
    @ResponseBody
    public Contrat addContrat(@RequestBody Contrat c) {
        Contrat contrat = contratService.addContrat(c);
        return contrat;
    }

    // http://localhost:8089/Kaddem/contrat/update-contrat/{id}
    @PutMapping("/update-contrat/{idContrat}")
    @ResponseBody
    public Contrat updateContrat(@PathVariable("idContrat") Integer idContrat, @RequestBody Contrat updatedContrat) {
        Contrat existingContrat = contratService.retrieveContrat(idContrat);


        existingContrat.setDateDebutContrat(updatedContrat.getDateDebutContrat());
        existingContrat.setDateFinContrat(updatedContrat.getDateFinContrat());
        existingContrat.setSpecialite(updatedContrat.getSpecialite());
        existingContrat.setArchived(updatedContrat.getArchived());
        existingContrat.setMontantContrat(updatedContrat.getMontantContrat());

        Contrat updatedContratEntity = contratService.updateContrat(existingContrat, idContrat);
        return updatedContratEntity;
    }

    // http://localhost:8089/Kaddem/contrat/remove-contrat/{id}
    @DeleteMapping("/remove-contrat/{idContrat}")
    @ResponseBody
    public void removeContrat(@PathVariable("idContrat") Integer idContrat) {
        contratService.removeContrat(idContrat);
    }



    // http://localhost:8089/Kaddem/contrat/addAndAffectContratToEtudiant/salah/ahmed
    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
    @ResponseBody
    public Contrat addAndAffectContratToEtudiant(@RequestBody Contrat contrat,@PathVariable("nomE") String nomE,@PathVariable("prenomE") String prenomE) {
        Contrat c= contratService.addAndAffectContratToEtudiant(contrat,nomE,prenomE);
        return c;
    }

    //The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.nbContratsValides(startDate, endDate);
    }

    //Only no-arg methods may be annotated with @Scheduled
    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
  //  @Scheduled(cron="45 * * * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    @PutMapping(value = "/majStatusContrat")
    public void majStatusContrat (){
        contratService.retrieveAndUpdateStatusContrat();
    }

    //public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

    @GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    @ResponseBody
    public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                    @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }

}
