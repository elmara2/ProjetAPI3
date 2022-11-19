package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return enseignantRepository.findById(enseignant.getIdenseignant()).get();

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

    @Override
    public Page<Enseignant> allp(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public List<Enseignant> read(String nom) {
        return enseignantRepository.findEnseignantsByNomLike(nom);
    }

    @Override
    public Enseignant rechEnseignant(String matricule) {
        return enseignantRepository.findEnseignantByMatriculeLike(matricule);
    }
}
