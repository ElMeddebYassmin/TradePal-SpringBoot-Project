package tn.esprit.spring.khaddem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
@Tag(name = "Equipe", description = "Gestion des equipes")
@CrossOrigin(origins = "http://localhost:4200")
public class EquipeRestController {
    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        List<Equipe> listEquipes = equipeService.retrieveAllEquipes();
        return listEquipes;
    }


    // http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/equipe/add-equipe
    /* cette méthode permet d'ajouter une équipe avec son détail*/
    @PostMapping("/add-equipe")
    @ResponseBody
    public Equipe addEquipe(@RequestBody Equipe e) {
        Equipe equipe = equipeService.addEquipe(e);
        return equipe;
    }

    // http://localhost:8089/Kaddem/equipe/update-equipe/{id}
    @PutMapping("/update-equipe/{idEquipe}")
    @ResponseBody
    public Equipe updateEquipe(@PathVariable("idEquipe") Integer idEquipe, @RequestBody Equipe updatedEquipe) {
        Equipe existingEquipe = equipeService.retrieveEquipe(idEquipe);

        existingEquipe.setNomEquipe(updatedEquipe.getNomEquipe());
        existingEquipe.setNiveau(updatedEquipe.getNiveau());

        Equipe updatedEquipeEntity = equipeService.updateEquipe(existingEquipe, idEquipe);
        return updatedEquipeEntity;
    }

    // http://localhost:8089/Kaddem/equipe/remove-equipe/{id}
    @DeleteMapping("/remove-equipe/{idEquipe}")
    @ResponseBody
    public void removeEquipe(@PathVariable("idEquipe") Integer idEquipe) {
        equipeService.removeEquipe(idEquipe);
    }


   // @Scheduled(cron="0 0 13 * * *")
    @Scheduled(cron="* * 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }

}
