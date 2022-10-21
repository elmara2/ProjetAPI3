package be.condorcet.projetapi3;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/classes")
public class GestClasse {
    @Autowired
    private ClasseRepository classeRepository;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche classe");
        try {
            Collection<Classe> lcl = classeRepository.findAll();
            model.put("mesClasses", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "allClasse";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String sigle,@RequestParam int annee,@RequestParam String specialite,
                         @RequestParam int nbreeleves,Map<String, Object> model){
        System.out.println("nouvelle classe ");
        Classe cl = new Classe(sigle);
        cl.setAnnee(annee);
        cl.setSpecialite(specialite);
        cl.setNbreeleves(nbreeleves);
        try {
            classeRepository.save(cl);
            System.out.println(cl);
            model.put("nouvclasse",cl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------" + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "newClasse";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idclasse, Map<String, Object> model){
        System.out.println("recherche de la classe "+idclasse);
        try {
            model.put("macl",classeRepository.findClasseByIdclasseLike(idclasse));
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affClasse";
    }

    @RequestMapping("/rechClass")
    public String rechClass(@RequestParam int idclasse, Map<String, Object> model){
        System.out.println("recherche de la classe "+idclasse);
        try{
            model.put("macl",classeRepository.findClasseByIdclasseLike(idclasse));
        }
        catch (Exception e){
            System.out.println("----------erreur lors de la recherche ----- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "editClasse";
    }
    @RequestMapping("/update")
    public String update(@RequestParam int idclasse,@RequestParam int annee,
                         @RequestParam String specialite,@RequestParam int nbreeleves,
                         Map<String ,Object> model){

        System.out.println("Modification de la classe");
        try {

            Optional<Classe> ocl = classeRepository.findById(idclasse);
            ocl.ifPresent((cl)->{
                cl=ocl.get();
                cl.setNbreeleves(nbreeleves);
                cl.setSpecialite(specialite);
                cl.setAnnee(annee);
                classeRepository.save(cl);
            });

            model.put("macl",classeRepository.findClasseByIdclasseLike(idclasse));
        } catch (Exception e) {
            System.out.println("----------erreur lors de la modification-------" + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affClasse";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int idclasse,
                         Map<String ,Object> model){
        System.out.println("Suppression de la classe pour id : "+idclasse);
        try {
            classeRepository.deleteById(idclasse);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la suppression-------" + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "deleteClasse";
    }
}
