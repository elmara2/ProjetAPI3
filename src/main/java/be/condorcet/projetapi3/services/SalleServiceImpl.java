package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Salle;
import be.condorcet.projetapi3.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleServiceImpl implements InterfSalleService{
    @Autowired
    private SalleRepository salleRepository;
    @Override
    public Salle create(Salle salle) throws Exception {
        salleRepository.save(salle);
        return salle;
    }

    @Override
    public Salle read(Salle salle) throws Exception {
        Optional<Salle> osal = salleRepository.findById(salle.getIdsalle());
        return osal.get();
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
}
