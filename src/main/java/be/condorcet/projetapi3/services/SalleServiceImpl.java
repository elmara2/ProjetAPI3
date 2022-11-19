package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;
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

    @Override
    public List<Salle> read(int capacite) {
        return salleRepository.findSallesByCapacite(capacite);
    }
    @Override
    public Salle rechSalle(String sigle) {
        return salleRepository.findSalleBySigleLike(sigle);
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


}
