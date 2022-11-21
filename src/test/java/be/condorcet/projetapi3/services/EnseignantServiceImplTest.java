package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
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


    @Autowired
    private EnseignantServiceImpl enseignantServiceImpl;

    Enseignant ens;

    @BeforeEach
    void setUp() {
        try{
            ens = new Enseignant(null,"MatriculeTest","NomTest","PrenomTest","TelTest","200",2500.23, LocalDate.of(2022,11,11),null,new ArrayList<>());
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
        assertEquals("200",ens.getChargesem(),"chargesem de l'enseignant non enregistré : "+ens.getChargesem()+ " au lieu de ChargeSemTest");
        assertEquals(2500.23,ens.getSalairemensu(),"salairemensu de l'enseignant non enregistré : "+ens.getSalairemensu()+" au lieu de SalaireMensuTest");
        assertEquals(LocalDate.of(2022,11,11),ens.getDateengag(),"date de l'enseignant non enregistré : "+ens.getDateengag()+" au lieu de DateEngagTest");

    }

    @Test
    void read() {
        try{
            Enseignant ens2=enseignantServiceImpl.read(ens);
            assertEquals("MatriculeTest",ens2.getMatricule(),"matricules différents "+"MatriculeTest"+"-"+ens2.getMatricule());
            assertEquals("NomTest",ens2.getNom(),"noms différents "+"NomTest"+"-"+ens2.getNom());
            assertEquals("PrenomTest",ens2.getPrenom(),"prenoms différents "+"PrenomTest"+"-"+ens2.getPrenom());
            assertEquals("TelTest",ens2.getTel(),"telephones différents "+"TelTest"+"-"+ens2.getTel());
            assertEquals("200",ens2.getChargesem(),"chargesem différentes "+"ChargeSemTest"+"-"+ens2.getChargesem());
            assertEquals(2500.23,ens2.getSalairemensu(),"salairemensu différents "+"SalaireMensuTest"+"-"+ens2.getSalairemensu());
            assertEquals(LocalDate.of(2022,11,11),ens2.getDateengag(),"dateengag différentes "+"DateEngag"+"-"+ens2.getDateengag());

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
            ens.setChargesem("300");
            ens.setSalairemensu(20.23);
            ens.setDateengag(LocalDate.of(2021,11,11));
            ens = enseignantServiceImpl.update(ens);
            assertEquals("MatriculeTest2",ens.getMatricule(),"matricules différents "+"MatriculeTest2-"+ens.getMatricule());
            assertEquals("NomTest2",ens.getNom(),"noms différents "+"NomTest2-"+ens.getNom());
            assertEquals("PrenomTest2",ens.getPrenom(),"prenoms différents "+"PrenomTest2-"+ens.getPrenom());
            assertEquals("TelTest2",ens.getTel(),"telephones différents "+"TelTest2-"+ens.getTel());
            assertEquals("300",ens.getChargesem(),"chargesem différentes "+"ChargeSemTest2-"+ens.getChargesem());
            assertEquals(20.23,ens.getSalairemensu(),"salairemensu différents "+"SalaireMensuTest2-"+ens.getSalairemensu());
            assertEquals(LocalDate.of(2021,11,11),ens.getDateengag(),"dates différentes "+"DateEngagTest2-"+ens.getDateengag());
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
        }catch (Exception e){
            fail("erreur de recherche de tous les enseignants "+e);
        }
    }

    @Test
    void rechEnseignant(){
        List<Enseignant> lens = enseignantServiceImpl.read("NomTest");
        boolean trouve=false;
        for(Enseignant e : lens){
            if(e.getNom().startsWith("NomTest")) trouve=true;
            else fail("un record ne correspond pas , intitule = "+e.getNom());
        }
        assertTrue(trouve,"record nom trouvé dans la liste");
    }
}