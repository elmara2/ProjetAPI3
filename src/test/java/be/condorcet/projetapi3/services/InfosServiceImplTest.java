package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InfosServiceImplTest {
    @Autowired
    private InfosServiceImpl infosServiceImpl;
    @Autowired
    private ClasseServiceImpl classeServiceImpl;
    @Autowired
    private CoursServiceImpl coursServiceImpl;
    @Autowired
    private SalleServiceImpl salleServiceImpl;
    @Autowired
    private EnseignantServiceImpl enseignantServiceImpl;
    Infos inf;
    Classe cl;
    Cours crs;
    Enseignant ens;
    Salle sal;
    @BeforeEach
    void setUp() {
        try{
            cl = new Classe(null,"SigleTest",2,"SpecialiteTest",23,new ArrayList<>());
            classeServiceImpl.create(cl);
            System.out.println("création de la classe : "+cl);
            crs = new Cours(null,"CodeTest","IntituleTest",new ArrayList<>());
            coursServiceImpl.create(crs);
            System.out.println("création du cours : "+crs);
            sal = new Salle(null,"SigleTest",23,new ArrayList<>(),new ArrayList<>());
            salleServiceImpl.create(sal);
            System.out.println("création de la salle : "+sal);
            ens = new Enseignant(null,"MatriculeTest","NomTest","PrenomTest","TelTest","ChargeSemTest",2500.23, LocalDate.now(),sal,new ArrayList<>());
            enseignantServiceImpl.create(ens);
            System.out.println("création de l'enseignant : "+ens);
            inf = new Infos(null,3,cl,crs,ens,sal);
            infosServiceImpl.create(inf);
            System.out.println("création de l'infos : "+cl);
        }
        catch (Exception e){
            System.out.println("erreur de création de l'infos : "+inf +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            infosServiceImpl.delete(inf);
            System.out.println("effacement de l'infos : "+inf);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de l'infos :"+inf+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(null,inf.getId_infos(),"id classe non incrémenté");
        assertEquals(3,inf.getNbreheures(),"nombre heures de l'infos non enregistré : "+inf.getNbreheures()+ " au lieu de 3");
    }

    @Test
    void read() {
        try{

            Infos inf2=infosServiceImpl.read(inf);
            assertEquals(3,inf2.getNbreheures(),"nombre heures différents "+3+"-"+inf2.getNbreheures());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            inf.setNbreheures(4);
            inf = infosServiceImpl.update(inf);
            assertEquals(4,inf.getNbreheures(),"nombre heures différents -"+inf.getNbreheures());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            infosServiceImpl.delete(inf);
            Assertions.assertThrows(Exception.class, () -> {
                infosServiceImpl.read(inf);
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Infos> linf = infosServiceImpl.all();
            assertNotEquals(0,linf.size(),"la liste ne contient aucun élément");
        }
        catch (Exception e){
            fail("erreur de recherche de tous les infos "+e);
        }
    }

    @Test
    void rechInfos(){
        List<Infos> linf = infosServiceImpl.read(23);
        boolean trouve=false;
        for(Infos i : linf){
            if(i.getNbreheures().equals(23)) trouve=true;
            else fail("un record ne correspond pas , nbreheures = "+i.getNbreheures());
        }
        assertTrue(trouve,"record nbreheures trouvé dans la liste");
    }
}