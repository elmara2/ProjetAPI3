package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.services.InterfCoursService;
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
@RequestMapping("/cours")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class RestCours {

    @Autowired
    private InterfCoursService coursServiceImpl;
    //-------------------Retrouver le cours correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCours(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("recherche du cours d' id " + id);
        Cours crs = new Cours(id,"","",new ArrayList<>());
        Cours cours = coursServiceImpl.read(crs);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }

    @RequestMapping(value = "/intitule={intitule}", method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCoursIntitule(@PathVariable(value="intitule") String intitule) throws Exception{
        System.out.println("recherche de "+intitule);
        List<Cours> cours;
        cours = coursServiceImpl.read(intitule);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }

    //-------------------Retrouver le cours correspondant à un code unique donné--------------------------------------------------------
    @RequestMapping(value = "/code={code}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCoursUnique(@PathVariable(value = "code") String code) throws Exception{
        System.out.println("recherche du cours ayant pour code : "+code);
        Cours cours = coursServiceImpl.rechCours(code);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }


    //-------------------Retrouver tous les cours --------------------------------------------------------
    @RequestMapping(value = "/all"
            ,method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCours() throws Exception{
        System.out.println
                ("recherche de tous les cours");
        return new ResponseEntity<>(coursServiceImpl.all(), HttpStatus.OK);
    }



    //--------------Créer un cours--------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours) throws Exception {
        System.out.println("Création du cours " + cours.getIntitule());
        coursServiceImpl.create(cours);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }


    //-------------------Mettre à jour un cours d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cours> majCours(@PathVariable(value = "id") int id,@RequestBody Cours nouvcrs) throws Exception{
        System.out.println("maj du cours d'id = " + id);
        nouvcrs.setIdcours(id);
        Cours crsact = coursServiceImpl.update(nouvcrs);
        return new ResponseEntity<>(crsact, HttpStatus.OK);
    }

    //-------------------Effacer un cours d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCours(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du cours d'id " + id);
        Cours crs = new Cours(id,"","",new ArrayList<>());
        coursServiceImpl.delete(crs);
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
    public ResponseEntity<Page<Cours>> listCours(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les cours");
        return new ResponseEntity<>(coursServiceImpl.allp(pageable), HttpStatus.OK);
    }


    @RequestMapping(value = "/infos/idcours={idcours}",method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> InfosCours(@PathVariable(value = "idcours") int idcours) throws Exception{
        System.out.println("infos de cours d'id "+idcours);
        Cours crs = new Cours(idcours,"","",new ArrayList<>());
        return new ResponseEntity<>(coursServiceImpl.readInfos(crs),HttpStatus.OK);
    }

}
