package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite  u);

    Universite updateUniversite (Universite  u, Integer id);
    public void removeUniversite (Integer id);

    Universite retrieveUniversite (Integer idUniversite);


    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) ;


}
