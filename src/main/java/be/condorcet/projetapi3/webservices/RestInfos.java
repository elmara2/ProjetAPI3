package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.*;
import be.condorcet.projetapi3.services.InterfCoursService;
import be.condorcet.projetapi3.services.InterfEnseignantService;
import be.condorcet.projetapi3.services.InterfInfosService;
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
@RequestMapping("/infos")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class RestInfos {

    @Autowired
    private InterfInfosService infosServiceImpl;
    //-------------------Retrouver l'infos correspondant à un id donné( idclasse et idcours)--------------------------------------------------------
    @RequestMapping(value = "/idcl={idcl}/idcrs={idcrs}", method = RequestMethod.GET)
    public ResponseEntity<Infos> getInfos(@PathVariable(value = "idcl") int idcl,@PathVariable(value = "idcrs") int idcrs) throws Exception{
        System.out.println("recherche de l'infos d'idclasse " + idcl+" et d'idcours"+idcrs);
        Infos inf = new Infos(new InfosPK(idcl,idcrs),0,new Classe(idcl,"",0,"",0,new ArrayList<>()),new Cours(idcrs,"","",new ArrayList<>()),null,null);
        Infos infos = infosServiceImpl.read(inf);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    @RequestMapping(value = "/nbreheures={nbreheures}", method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> listInfosParHeure(@PathVariable(value="nbreheures") int nbreheures) throws Exception{
        System.out.println("recherche de "+nbreheures);
        List<Infos> infos;
        infos = infosServiceImpl.read(nbreheures);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    //-------------------Retrouver tous les infos --------------------------------------------------------
    @RequestMapping(value = "/all"
            ,method = RequestMethod.GET)
    public ResponseEntity<List<Infos>> listInfos() throws Exception{
        System.out.println("recherche de tous les infos");
        return new ResponseEntity<>(infosServiceImpl.all(), HttpStatus.OK);
    }



    //--------------Créer une infos--------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Infos> createInfos(@RequestBody Infos infos) throws Exception {
        System.out.println("Création de l'infos avec nombre heure" + infos.getNbreheures());
        infosServiceImpl.create(infos);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

    //-------------------Mettre à jour une infos d'un id donné(idclasse et idcours)--------------------------------------------------------
    @RequestMapping(value = "/idcl={idcl}/idcrs={idcrs}", method = RequestMethod.PUT)
    public ResponseEntity<Infos> majInfos(@PathVariable(value = "idcl") int idcl,@PathVariable(value = "idcrs") int idcrs,@RequestBody Infos nouvinf) throws Exception{
        System.out.println("maj de l'infos d'idclasse = " + idcl+" et d'idcours = "+idcrs);
        nouvinf.setId_infos(new InfosPK(idcl,idcrs));
        Infos infact = infosServiceImpl.update(nouvinf);
        return new ResponseEntity<>(infact, HttpStatus.OK);
    }


    @RequestMapping(value = "/idcl={idcl}/idcrs={idcrs}/idens={idens}", method = RequestMethod.PUT)
    public ResponseEntity<Infos> majEnsInfos(@PathVariable(value = "idcl") int idcl,@PathVariable(value = "idcrs") int idcrs,@PathVariable(value = "idens") int idens) throws Exception{
        System.out.println("maj de l'enseignant "+idens+" de l'infos d'idclasse = " + idcl+" et d'idcours = "+idcrs);
        Infos inf = new Infos(new InfosPK(idcl,idcrs),0,new Classe(idcl,"",0,"",0,new ArrayList<>()),new Cours(idcrs,"","",new ArrayList<>()),null,null);
        Enseignant ens = new Enseignant(idens,"","","","","",2500.23, LocalDate.of(2022,11,11),null,new ArrayList<>());
        inf = infosServiceImpl.modifEnseignant(inf,ens);
        return new ResponseEntity<>(inf, HttpStatus.OK);
    }

    @RequestMapping(value = "/idcl={idcl}/idcrs={idcrs}/idsal={idsal}", method = RequestMethod.PUT)
    public ResponseEntity<Infos> majSalInfos(@PathVariable(value = "idcl") int idcl,@PathVariable(value = "idcrs") int idcrs,@PathVariable(value = "idsal") int idsal) throws Exception{
        System.out.println("maj de la salle "+idsal+" de l'infos d'idclasse = " + idcl+" et d'idcours = "+idcrs);
        Infos inf = new Infos(new InfosPK(idcl,idcrs),0,new Classe(idcl,"",0,"",0,new ArrayList<>()),new Cours(idcrs,"","",new ArrayList<>()),null,null);
        Salle sal = new Salle(idsal,"",1,new ArrayList<>(),new ArrayList<>());
        inf = infosServiceImpl.modifSalle(inf,sal);
        return new ResponseEntity<>(inf, HttpStatus.OK);
    }

    @RequestMapping(value = "/idcl={idcl}/idcrs={idcrs}/n={n}", method = RequestMethod.PUT)
    public ResponseEntity<Infos> majNbreHeureInfos(@PathVariable(value = "idcl") int idcl,@PathVariable(value = "idcrs") int idcrs,@PathVariable(value = "n") int n) throws Exception{
        System.out.println("maj du nbre d(heure "+n+" de l'infos d'idclasse = " + idcl+" et d'idcours = "+idcrs);
        Infos inf = new Infos(new InfosPK(idcl,idcrs),n,new Classe(idcl,"",0,"",0,new ArrayList<>()),new Cours(idcrs,"","",new ArrayList<>()),null,null);
        inf.setNbreheures(n);
        return new ResponseEntity<>(inf, HttpStatus.OK);
    }



    //-------------------Effacer une infos d'un id donné(idclasse et idcours)--------------------------------------------------------
    @RequestMapping(value = "/idcl={idcl}/idcrs={idcrs}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteInfos(@PathVariable(value = "idcl") int idcl,@PathVariable(value = "idcrs") int idcrs) throws Exception{
        System.out.println("effacement de l'infos d'idclasse = " + idcl+" et d'idcours = "+idcrs);
        Infos inf = new Infos(new InfosPK(idcl,idcrs),0,new Classe(idcl,"",0,"",0,new ArrayList<>()),new Cours(idcrs,"","",new ArrayList<>()),null,null);
        infosServiceImpl.delete(inf);
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
    public ResponseEntity<Page<Infos>> listInfos(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les infos");
        return new ResponseEntity<>(infosServiceImpl.allp(pageable), HttpStatus.OK);
    }



}
