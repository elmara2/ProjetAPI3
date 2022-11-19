package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Cours;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/salles")
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

}
