package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EnseignantServiceImpl implements InterfEnseignantService{
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Override
    public Enseignant create(Enseignant enseignant) throws Exception {
        enseignantRepository.save(enseignant);
        return enseignant;
    }

    @Override
    public Enseignant read(Enseignant enseignant) throws Exception {
        Optional<Enseignant> oens = enseignantRepository.findById(enseignant.getIdenseignant());
        return oens.get();
    }

    @Override
    public Enseignant update(Enseignant enseignant) throws Exception {
        read(enseignant);
        enseignantRepository.save(enseignant);
        return enseignant;
    }

    @Override
    public void delete(Enseignant enseignant) throws Exception {
        enseignantRepository.deleteById(enseignant.getIdenseignant());
    }

    @Override
    public List<Enseignant> all() throws Exception {
        return enseignantRepository.findAll();
    }
}
