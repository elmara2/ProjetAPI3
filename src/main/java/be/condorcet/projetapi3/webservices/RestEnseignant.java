package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.services.InterfClasseService;
import be.condorcet.projetapi3.services.InterfEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/enseignants")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class RestEnseignant {

    @Autowired
    private InterfEnseignantService enseignantServiceImpl;
    //-------------------Retrouver l'enseignant correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Enseignant> getEnseignant(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("recherche de l'enseignant d' id " + id);
        Enseignant ens = new Enseignant(id,"","","","","",2500.23,LocalDate.of(2022,11,11),null,new ArrayList<>());
        Enseignant enseignant = enseignantServiceImpl.read(ens);
        return new ResponseEntity<>(enseignant, HttpStatus.OK);
    }

    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Enseignant>> listEnseignantNom(@PathVariable(value="nom") String nom) throws Exception{
        System.out.println("recherche de "+nom);
        List<Enseignant> enseignants;
        enseignants = enseignantServiceImpl.read(nom);
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }

    //-------------------Retrouver l'enseignant correspondant à un matricule unique donné--------------------------------------------------------
    @RequestMapping(value = "/matricule={matricule}", method = RequestMethod.GET)
    public ResponseEntity<Enseignant> getEnseignantUnique(@PathVariable(value = "matricule") String matricule) throws Exception{
        System.out.println("recherche de l'enseignant ayant pour matricule : "+matricule);
        Enseignant enseignant = enseignantServiceImpl.rechEnseignant(matricule);
        return new ResponseEntity<>(enseignant, HttpStatus.OK);
    }


    //-------------------Retrouver tous les enseignants --------------------------------------------------------
    @RequestMapping(value = "/all"
            ,method = RequestMethod.GET)
    public ResponseEntity<List<Enseignant>> listEnseignant() throws Exception{
        System.out.println
                ("recherche de tous les enseignants");
        return new ResponseEntity<>(enseignantServiceImpl.all(), HttpStatus.OK);
    }



    //--------------Créer un enseignant--------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) throws Exception {
        System.out.println("Création de l'enseignant " + enseignant.getNom());
        enseignantServiceImpl.create(enseignant);
        return new ResponseEntity<>(enseignant, HttpStatus.OK);
    }


    //-------------------Mettre à jour un enseignant d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Enseignant> majEnseignant(@PathVariable(value = "id") int id,@RequestBody Enseignant nouvens) throws Exception{
        System.out.println("maj de l'enseignant id = " + id);
        nouvens.setIdenseignant(id);
        Enseignant ensact = enseignantServiceImpl.update(nouvens);
        return new ResponseEntity<>(ensact, HttpStatus.OK);
    }

    //-------------------Effacer un enseignant d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEnseignant(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de l'enseignant d'id " + id);
        Enseignant ens = new Enseignant(id,"","","","","",200,LocalDate.of(2022,11,11),null,new ArrayList<>());
        enseignantServiceImpl.delete(ens);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

    //--------Retrouver tous les enseignants triés et par pages--------------------------------
    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Enseignant>> listEnseignant(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les enseignants");
        return new ResponseEntity<>(enseignantServiceImpl.allp(pageable), HttpStatus.OK);
    }

}
