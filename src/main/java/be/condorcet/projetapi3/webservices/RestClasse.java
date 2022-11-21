package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.services.InterfClasseService;
import be.condorcet.projetapi3.services.InterfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class RestClasse {

    @Autowired
    private InterfClasseService classeServiceImpl;
    //-------------------Retrouver la classe correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Classe> getClasse(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("recherche de la classe d' id " + id);
        Classe cl = new Classe(id,"",1,"",1,new ArrayList<>());
        Classe classe = classeServiceImpl.read(cl);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    @RequestMapping(value = "/specialite={specialite}", method = RequestMethod.GET)
    public ResponseEntity<List<Classe>> listClassesSpecialite(@PathVariable(value="specialite") String specialite) throws Exception{
        System.out.println("recherche de "+specialite);
        List<Classe> classes;
        classes = classeServiceImpl.read(specialite);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    //-------------------Retrouver la classe correspondant à un sigle unique donné--------------------------------------------------------
    @RequestMapping(value = "/sigle={sigle}", method = RequestMethod.GET)
    public ResponseEntity<Classe> getClasseUnique(@PathVariable(value = "sigle") String sigle) throws Exception{
        System.out.println("recherche de la classe ayant pour sigle : "+sigle);
        Classe classe = classeServiceImpl.rechClasse(sigle);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }


    //-------------------Retrouver toutes les classes --------------------------------------------------------
    @RequestMapping(value = "/all"
            ,method = RequestMethod.GET)
    public ResponseEntity<List<Classe>> listClasse() throws Exception{
        System.out.println
                ("recherche de toutes les classes");
        return new ResponseEntity<>(classeServiceImpl.all(), HttpStatus.OK);
    }



    //--------------Créer une classe--------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Classe> createClient(@RequestBody Classe classe) throws Exception {
        System.out.println("Création de la classe " + classe.getSpecialite());
        classeServiceImpl.create(classe);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }


    //-------------------Mettre à jour une classe d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Classe> majClasse(@PathVariable(value = "id") int id,@RequestBody Classe nouvcl) throws Exception{
        System.out.println("maj de la classe id = " + id);
        nouvcl.setIdclasse(id);
        Classe clact = classeServiceImpl.update(nouvcl);
        return new ResponseEntity<>(clact, HttpStatus.OK);
    }

    //-------------------Effacer une classe d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClasse(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la classe d'id " + id);
        Classe cl = new Classe(id,"",1,"",1,new ArrayList<>());
        classeServiceImpl.delete(cl);
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
    public ResponseEntity<Page<Classe>> listClasse(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les classes");
        return new ResponseEntity<>(classeServiceImpl.allp(pageable), HttpStatus.OK);
    }



}


