package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursServiceImplTest {

    @Autowired
    private CoursServiceImpl coursServiceImpl;

    Cours crs;
    @BeforeEach
    void setUp() {
        try{
            crs = new Cours(null,"CodeTest","IntituleTest",new ArrayList<>());
            coursServiceImpl.create(crs);
            System.out.println("création du cours : "+crs);
        }
        catch (Exception e){
            System.out.println("erreur de création du cours : "+crs +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            coursServiceImpl.delete(crs);
            System.out.println("effacement du cours : "+crs);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement du cours :"+crs+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,crs.getIdcours(),"id cours non incrémenté");
        assertEquals("CodeTest",crs.getCode(),"code du cours non enregistré : "+crs.getCode()+ " au lieu de CodeTest");
        assertEquals("IntituleTest",crs.getIntitule(),"intitule du cours non enregistré : "+crs.getIntitule()+" au lieu de IntituleTest");
    }

    @Test
    void read() {
        try{
            Cours crs2=coursServiceImpl.read(crs);
            assertEquals("CodeTest",crs2.getCode(),"codes différents "+"CodeTest"+"-"+crs2.getCode());
            assertEquals("IntituleTest",crs2.getIntitule(),"intitule différents "+"IntituleTest"+"-"+crs2.getIntitule());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            crs.setCode("CodeTest2");
            crs.setIntitule("IntituleTest2");
            crs = coursServiceImpl.update(crs);
            assertEquals("CodeTest2",crs.getCode(),"codes différents "+"CodeTest2-"+crs.getCode());
            assertEquals("IntituleTest2",crs.getIntitule(),"intitules différents "+"IntituleTest2-"+crs.getIntitule());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            coursServiceImpl.delete(crs);
            Assertions.assertThrows(Exception.class, () -> {
                coursServiceImpl.read(crs);
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Cours> lcrs = coursServiceImpl.all();
            assertNotEquals(0,lcrs.size(),"la liste ne contient aucun élément");
        }catch (Exception e){ fail("erreur de recherche de tous les cours "+e);
        }
    }
}