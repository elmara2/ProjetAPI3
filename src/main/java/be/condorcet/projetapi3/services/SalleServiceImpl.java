package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.Salle;
import be.condorcet.projetapi3.repositories.EnseignantRepository;
import be.condorcet.projetapi3.repositories.InfosRepository;
import be.condorcet.projetapi3.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class SalleServiceImpl implements InterfSalleService{
    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Override
    public List<Salle> read(int capacite) {
        return salleRepository.findSallesByCapacite(capacite);
    }
    @Override
    public Salle rechSalle(String sigle) {
        return salleRepository.findSalleBySigleLike(sigle);
    }

    @Override
    public List<Enseignant> listEnseignant(Salle salle) throws Exception {
        salle = read(salle);
        return salle.getListEnseignant();
    }

    @Override
    public void ajoutEnseignant(Enseignant enseignant, Salle salle) throws Exception {
        enseignant=enseignantRepository.findById(enseignant.getIdenseignant()).get();
        salle=read(salle);
        salle.getListEnseignant().add(enseignant);
        enseignant.setSalle(salle);
        salleRepository.save(salle);
        enseignantRepository.save(enseignant);
    }

    @Override
    public void suppEnseignant(Enseignant enseignant, Salle salle) throws Exception {
     enseignant=enseignantRepository.findById(enseignant.getIdenseignant()).get();
     salle=read(salle);
     salle.getListEnseignant().remove(enseignant);
     enseignant.setSalle(null);
     salleRepository.save(salle);
     enseignantRepository.save(enseignant);
    }


    @Override
    public Salle create(Salle salle) throws Exception {
        salleRepository.save(salle);
        return salle;
    }

    @Override
    public Salle read(Salle salle) throws Exception {
        return salleRepository.findById(salle.getIdsalle()).get();

    }

    @Override
    public Salle update(Salle salle) throws Exception {
        read(salle);
        salleRepository.save(salle);
        return salle;
    }

    @Override
    public void delete(Salle salle) throws Exception {
        salleRepository.deleteById(salle.getIdsalle());
    }

    @Override
    public List<Salle> all() throws Exception {
        return salleRepository.findAll();
    }

    @Override
    public Page<Salle> allp(Pageable pageable) throws Exception {
        return salleRepository.findAll(pageable);
    }

    @Override
    public List<Infos> readInfos(Salle salle) throws Exception {
        salle=read(salle);
        return salle.getListInfos();
    }


}
