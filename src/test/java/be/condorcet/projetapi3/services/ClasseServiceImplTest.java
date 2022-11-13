package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClasseServiceImplTest {

    @Autowired
    private ClasseServiceImpl classeServiceImpl;

    Classe cl;

    @BeforeEach
    void setUp() {
        try{
            cl = new Classe(null,"SigleTest",2,"SpecialiteTest",23,new ArrayList<>());
            classeServiceImpl.create(cl);
            System.out.println("création de la classe : "+cl);
        }
        catch (Exception e){
            System.out.println("erreur de création de la classe : "+cl +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            classeServiceImpl.delete(cl);
            System.out.println("effacement de la classe : "+cl);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de la classe :"+cl+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,cl.getIdclasse(),"id classe non incrémenté");
        assertEquals("SigleTest",cl.getSigle(),"sigle de la classe non enregistré : "+cl.getSigle()+ " au lieu de SigleTest");
        assertEquals(2,cl.getAnnee(),"annee de la classe non enregistré : "+cl.getAnnee()+" au lieu de 2");
        assertEquals("SpecialiteTest",cl.getSpecialite(),"specialite de la classe non enregistré : "+cl.getSpecialite()+" au lieu de SpecialiteTest");
        assertEquals(23,cl.getNbreeleves(),"nbreeleves de la classe non enregistré : "+cl.getNbreeleves()+" au lieu de 23");

    }

    @Test
    void read() {

        try{

            Classe cl2=classeServiceImpl.read(cl);
            assertEquals("SigleTest",cl2.getSigle(),"sigles différents "+"SigleTest"+"-"+cl2.getSigle());
            assertEquals(2,cl2.getAnnee(),"annee différents "+"AnneeTest"+"-"+cl2.getAnnee());
            assertEquals("SpecialiteTest",cl2.getSpecialite(),"specialite différents "+"SpecialiteTest"+"-"+cl2.getSpecialite());
            assertEquals(23,cl2.getNbreeleves(),"nbreeleves différents "+"NbreelevesTest"+"-"+cl2.getNbreeleves());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }

    }



    @Test
    void update() {
        try{
            cl.setSigle("SigleTest2");
            cl.setAnnee(2);
            cl.setSpecialite("SpecialiteTest2");
            cl.setNbreeleves(23);
            cl = classeServiceImpl.update(cl);
            assertEquals("SigleTest2",cl.getSigle(),"sigles différents "+"SigleTest2-"+cl.getSigle());
            assertEquals(2,cl.getAnnee(),"annees différentes "+"AnneeTest2-"+cl.getAnnee());
            assertEquals("SpecialiteTest2",cl.getSpecialite(),"specialites différentes "+"SpecialiteTest2-"+cl.getSpecialite());
            assertEquals(23,cl.getNbreeleves(),"nbre d'eleves différentes "+"NbreelevesTest-"+cl.getNbreeleves());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {

        try{
            classeServiceImpl.delete(cl);
            Assertions.assertThrows(Exception.class, () -> {
                classeServiceImpl.read(cl);
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }

    }

    @Test
    void all() {
        try {
            List<Classe> lc = classeServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun élément");
        }
        catch (Exception e){
            fail("erreur de recherche de tous les classes "+e);
        }
    }

    @Test
    void rechClasse(){

        List<Classe> lc = classeServiceImpl.read("SpecialiteTest");
        boolean trouve=false;
        for(Classe c : lc){
            if(c.getSpecialite().equals("SpecialiteTest")) trouve=true;
            else fail("un record ne correspond pas , specialite = "+c.getSpecialite());
        }
        assertTrue(trouve,"record specialite trouvé dans la liste");

    }
}