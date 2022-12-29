package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.Salle;
import be.condorcet.projetapi3.services.InterfCoursService;
import be.condorcet.projetapi3.services.InterfSalleService;
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
@RequestMapping("/salles")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class RestSalle {

    @Autowired
    private InterfSalleService salleServiceImpl;
    //-------------------Retrouver la salle correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Salle> getSalle(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("recherche de la salle d' id " + id);
        Salle sal = new Salle(id,"",1,new ArrayList<>(),new ArrayList<>());
        Salle salle = salleServiceImpl.read(sal);
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }

    @RequestMapping(value = "/capacite={capacite}", method = RequestMethod.GET)
    public ResponseEntity<List<Salle>> listSalleCapacite(@PathVariable(value="capacite") int capacite) throws Exception{
        System.out.println("recherche de salle avec capacite = "+capacite);
        List<Salle> salles;
        salles = salleServiceImpl.read(capacite);
        return new ResponseEntity<>(salles, HttpStatus.OK);
    }

    //-------------------Retrouver la salle correspondant à un sigle unique donné--------------------------------------------------------
    @RequestMapping(value = "/sigle={sigle}", method = RequestMethod.GET)
    public ResponseEntity<Salle> getSalleUnique(@PathVariable(value = "sigle") String sigle) throws Exception{
        System.out.println("recherche de la salle ayant pour sigle : "+sigle);
        Salle salle = salleServiceImpl.rechSalle(sigle);
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }


    //-------------------Retrouver toutes les salles --------------------------------------------------------
    @RequestMapping(value = "/all"
            ,method = RequestMethod.GET)
    public ResponseEntity<List<Salle>> listSalle() throws Exception{
        System.out.println
                ("recherche de toutes les salles");
        return new ResponseEntity<>(salleServiceImpl.all(), HttpStatus.OK);
    }



    //--------------Créer une salle--------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) throws Exception {
        System.out.println("Création de la salle avec pour capacite : " + salle.getCapacite());
        salleServiceImpl.create(salle);
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }


    //-------------------Mettre à jour une salle d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Salle> majSalle(@PathVariable(value = "id") int id,@RequestBody Salle nouvsal) throws Exception{
        System.out.println("maj de la salle d'id = " + id);
        nouvsal.setIdsalle(id);
        Salle salact = salleServiceImpl.update(nouvsal);
        return new ResponseEntity<>(salact, HttpStatus.OK);
    }

    //-------------------Effacer une salle d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSalle(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la salle d'id " + id);
        Salle sal = new Salle(id,"",1,new ArrayList<>(),new ArrayList<>());
        salleServiceImpl.delete(sal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

    //--------Retrouver toutes les classes triés et par pages--------------------------------
    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Salle>> listSalle(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les cours");
        return new ResponseEntity<>(salleServiceImpl.allp(pageable), HttpStatus.OK);
    }


    @RequestMapping(value = "/enseignants/idsalle={idsalle}",method = RequestMethod.GET)
    public ResponseEntity<List<Enseignant>> listEnseignant(@PathVariable(value = "idsalle") int idsalle) throws Exception{
        System.out.println("recherche de tous les enseignants de la salle d'id "+idsalle);
        Salle sal = new Salle(idsalle,"",1,new ArrayList<>(),new ArrayList<>());
        return new ResponseEntity<>(salleServiceImpl.listEnseignant(sal), HttpStatus.OK);
    }

    @RequestMapping(value = "/idsalle={idsalle}/idenseignant={idenseignant}",method = RequestMethod.PUT)
    public ResponseEntity<Void> ajoutEnseignant(@PathVariable(value = "idsalle") int idsalle,@PathVariable(value = "idenseignant") int idenseignant) throws Exception{
        System.out.println("ajout de l'enseignant d'id "+idenseignant+" a la salle d'id "+idsalle);
        Salle sal = new Salle(idsalle,"",1,new ArrayList<>(),new ArrayList<>());
        Enseignant ens = new Enseignant(idenseignant,"","","","","",2500.23, LocalDate.of(2022,11,11),null,new ArrayList<>());
        salleServiceImpl.ajoutEnseignant(ens,sal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/idsalle={idsalle}/idenseignant={idenseignant}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> suppEnseignant(@PathVariable(value = "idsalle") int idsalle,@PathVariable(value = "idenseignant") int idenseignant) throws Exception{
        System.out.println("suppression de l'enseignant d'id "+idenseignant+" de la salle d'id "+idsalle);
        Salle sal = new Salle(idsalle,"",1,new ArrayList<>(),new ArrayList<>());
        Enseignant ens = new Enseignant(idenseignant,"","","","","",2500.23, LocalDate.of(2022,11,11),null,new ArrayList<>());
        salleServiceImpl.suppEnseignant(ens,sal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/infos/idsalle={idsalle}",method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> InfosSalle(@PathVariable(value = "idsalle") int idsalle) throws Exception{
        System.out.println("infos de la salle d'id "+idsalle);
        Salle sal = new Salle(idsalle,"",1,new ArrayList<>(),new ArrayList<>());
        return new ResponseEntity<>(salleServiceImpl.readInfos(sal),HttpStatus.OK);
    }
}
