package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Salle;
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
class SalleServiceImplTest {

    @Autowired
    private SalleServiceImpl salleServiceImpl;

    Salle sal;

    @BeforeEach
    void setUp() {
        try{
            sal = new Salle(null,"SigleTest",23,new ArrayList<>(),new ArrayList<>());
            salleServiceImpl.create(sal);
            System.out.println("création de la salle : "+sal);
        }
        catch (Exception e){
            System.out.println("erreur de création de la salle : "+sal +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            salleServiceImpl.delete(sal);
            System.out.println("effacement de la salle : "+sal);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de la salle :"+sal+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,sal.getIdsalle(),"id salle non incrémenté");
        assertEquals("SigleTest",sal.getSigle(),"sigle de la salle non enregistré : "+sal.getSigle()+ " au lieu de SigleTest");
        assertEquals(23,sal.getCapacite(),"capacite de la salle non enregistré : "+sal.getCapacite()+" au lieu de CapaciteTest");
    }

    @Test
    void read() {
        try{
            Salle sal2=salleServiceImpl.read(sal);
            assertEquals("SigleTest",sal2.getSigle(),"sigles différents "+"SigleTest"+"-"+sal2.getSigle());
            assertEquals(23,sal2.getCapacite(),"capacités différentes "+"CapaciteTest"+"-"+sal2.getCapacite());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            sal.setSigle("SigleTest2");
            sal.setCapacite(13);
            sal = salleServiceImpl.update(sal);
            assertEquals("SigleTest2",sal.getSigle(),"sigles différents "+"SigleTest2-"+sal.getSigle());
            assertEquals(13,sal.getCapacite(),"capacites différentes "+"CapaciteTest2-"+sal.getCapacite());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            salleServiceImpl.delete(sal);
            Assertions.assertThrows(Exception.class, () -> {
                salleServiceImpl.read(sal);
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Salle> lsal = salleServiceImpl.all();
            assertNotEquals(0,lsal.size(),"la liste ne contient aucun élément");
        }
        catch (Exception e){
            fail("erreur de recherche de toutes les salles "+e);
        }
    }

    @Test
    void rechSalle(){
        List<Salle> lsal = salleServiceImpl.read(23);
        boolean trouve=false;
        for(Salle s : lsal){
            if(s.getCapacite().equals(23)) trouve=true;
            else fail("un record ne correspond pas , capacite = "+s.getCapacite());
        }
        assertTrue(trouve,"record capacite trouvé dans la liste");
    }
}