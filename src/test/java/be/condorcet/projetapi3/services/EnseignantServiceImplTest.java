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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnseignantServiceImplTest {

    private Salle sal;
    @Autowired
    private EnseignantServiceImpl enseignantServiceImpl;
    Enseignant ens;
    @Autowired
    private SalleServiceImpl salleServiceImpl;

    @BeforeEach
    void setUp() {
        try{
            sal = new Salle(null,"SigleTest",1000,new ArrayList<>(),new ArrayList<>());
            salleServiceImpl.create(sal);
            System.out.println("création de la salle : "+sal);
            ens = new Enseignant(null,"MatriculeTest","NomTest","PrenomTest","TelTest","ChargeSemTest",2500.23, LocalDate.now(),sal,new ArrayList<>());
            enseignantServiceImpl.create(ens);
            System.out.println("création de l'enseignant : "+ens);
        }
        catch (Exception e){
            System.out.println("erreur de création de l'enseignant : "+ens +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            enseignantServiceImpl.delete(ens);
            System.out.println("effacement de l'enseignant : "+ens);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de l'enseignant :"+ens+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,ens.getIdenseignant(),"id enseignant non incrémenté");
        assertEquals("MatriculeTest",ens.getMatricule(),"matricule de l'enseignant non enregistré : "+ens.getMatricule()+ " au lieu de MatriculeTest");
        assertEquals("NomTest",ens.getNom(),"nom de l'enseignant non enregistré : "+ens.getNom()+" au lieu de NomTest");
        assertEquals("PrenomTest",ens.getPrenom(),"prenom de l'enseignant non enregistré : "+ens.getPrenom()+" au lieu de PrenomTest");
        assertEquals("TelTest",ens.getTel(),"telephone de l'enseignant non enregistré : "+ens.getTel()+" au lieu de TelTest");
        assertEquals("ChargeSemTest",ens.getChargesem(),"chargesem de l'enseignant non enregistré : "+ens.getChargesem()+ " au lieu de ChargeSemTest");
        assertEquals("SalaireMensuTest",ens.getSalairemensu(),"salairemensu de l'enseignant non enregistré : "+ens.getSalairemensu()+" au lieu de SalaireMensuTest");
        assertEquals("DateEngag",ens.getDateengag(),"date de l'enseignant non enregistré : "+ens.getDateengag()+" au lieu de DateEngagTest");

    }

    @Test
    void read() {
        try{
            Enseignant ens2=enseignantServiceImpl.read(ens);
            assertEquals("MatriculeTest",ens2.getMatricule(),"matricules différents "+"MatriculeTest"+"-"+ens2.getMatricule());
            assertEquals("NomTest",ens2.getNom(),"noms différents "+"NomTest"+"-"+ens2.getNom());
            assertEquals("PrenomTest",ens2.getPrenom(),"prenoms différents "+"PrenomTest"+"-"+ens2.getPrenom());
            assertEquals("TelTest",ens2.getTel(),"telephones différents "+"TelTest"+"-"+ens2.getTel());

            assertEquals("ChargeSemTest",ens2.getChargesem(),"chargesem différentes "+"ChargeSemTest"+"-"+ens2.getChargesem());
            assertEquals("SalaireMensuTest",ens2.getSalairemensu(),"salairemensu différents "+"SalaireMensuTest"+"-"+ens2.getSalairemensu());
            assertEquals("DateEngag",ens2.getDateengag(),"dateengag différentes "+"DateEngag"+"-"+ens2.getDateengag());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {

        try{
            ens.setMatricule("MatriculeTest2");
            ens.setNom("NomTest2");
            ens.setPrenom("PrenomTest2");
            ens.setTel("TelTest2");
            ens.setChargesem("ChargeSemTest2");
            ens.setSalairemensu(2500.34);
            ens.setDateengag(LocalDate.now());
            ens = enseignantServiceImpl.update(ens);
            assertEquals("MatriculeTest2",ens.getMatricule(),"matricules différents "+"MatriculeTest2-"+ens.getMatricule());
            assertEquals("NomTest2",ens.getNom(),"noms différents "+"NomTest2-"+ens.getNom());
            assertEquals("PrenomTest2",ens.getPrenom(),"prenoms différents "+"PrenomTest2-"+ens.getPrenom());
            assertEquals("TelTest2",ens.getTel(),"telephones différents "+"TelTest2-"+ens.getTel());

            assertEquals("ChargeSemTest2",ens.getChargesem(),"chargesem différentes "+"ChargeSemTest2-"+ens.getChargesem());
            assertEquals("SalaireMensuTest2",ens.getSalairemensu(),"salairemensu différents "+"SalaireMensuTest2-"+ens.getSalairemensu());
            assertEquals("DateEngagTest2",ens.getDateengag(),"dates différentes "+"DateEngagTest2-"+ens.getDateengag());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            enseignantServiceImpl.delete(ens);
            Assertions.assertThrows(Exception.class, () -> {
                enseignantServiceImpl.read(ens);
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Enseignant> lens = enseignantServiceImpl.all();
            assertNotEquals(0,lens.size(),"la liste ne contient aucun élément");
        }catch (Exception e){ fail("erreur de recherche de tous les enseignants "+e);
        }
    }
}