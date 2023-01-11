package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.*;
import be.condorcet.projetapi3.repositories.EnseignantRepository;
import be.condorcet.projetapi3.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EnseignantServiceImpl implements InterfEnseignantService{
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private SalleRepository salleRepository;

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
        return enseignantRepository.findAll(pageable);
    }

    @Override
    public List<Enseignant> read(String nom) {
        return enseignantRepository.findEnseignantsByNomLike(nom+"%");
    }

    @Override
    public Enseignant rechEnseignant(String matricule) {
        return enseignantRepository.findEnseignantByMatriculeLike(matricule);
    }

    @Override
    public Salle rechSalleDeEnseignant(Enseignant enseignant) throws Exception {
        enseignant=read(enseignant);
        return enseignant.getSalle();
    }

    @Override
    public Enseignant modifSalleDeEnseignant(Enseignant enseignant, Salle salle) throws Exception {
        enseignant=read(enseignant);
        salle=salleRepository.findById(salle.getIdsalle()).get();
        enseignant.setSalle(salle);
        enseignantRepository.save(enseignant);
        return enseignant;
    }

    @Override
    public List<Infos> readInfos(Enseignant enseignant) throws Exception {
        enseignant=read(enseignant);
        return enseignant.getListInfos();
    }

    @Override
    public List<Salle> rechSalleDeEnseignantWithName(List<Enseignant> listenseignant) throws Exception {
        Salle sal;
        List<Salle> lsal=new ArrayList<Salle>();
        for (Enseignant ens : listenseignant) {
            sal=rechSalleDeEnseignant(ens);
            if(!lsal.contains(sal)){
                lsal.add(sal);
            }

        }
        return lsal;
    }
}
