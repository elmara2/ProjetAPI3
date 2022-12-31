package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.*;
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
public class InfosServiceImpl implements InterfInfosService{
    @Autowired
    private InfosRepository infosRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Override
    public Infos create(Infos infos) throws Exception {
        infosRepository.save(infos);
        return infos;
    }

    @Override
    public Infos read(Infos infos) throws Exception {

        Infos inf = infosRepository.findById(infos.getId_infos()).get();
        return inf;
    }

    @Override
    public Infos update(Infos infos) throws Exception {
        read(infos);
        infosRepository.save(infos);
        return infos;
    }

    @Override
    public void delete(Infos infos) throws Exception {
        infosRepository.deleteById(infos.getId_infos());
    }

    @Override
    public List<Infos> all() throws Exception {
        return infosRepository.findAll();
    }

    @Override
    public Page<Infos> allp(Pageable pageable) throws Exception {
        return infosRepository.findAll(pageable);
    }

    @Override
    public List<Infos> read(Integer nbreheures) {
        return infosRepository.findInfosByNbreheuresLike(nbreheures);
    }

    @Override
    public Infos modifEnseignant(Infos infos, Enseignant enseignant) throws Exception {
        infos=read(infos);
        enseignant=enseignantRepository.findById(enseignant.getIdenseignant()).get();
        infos.setEnseignant(enseignant);
        infosRepository.save(infos);
        return infos;
    }

    @Override
    public Infos modifSalle(Infos infos, Salle salle) throws Exception {
        infos=read(infos);
        salle=salleRepository.findById(salle.getIdsalle()).get();
        infos.setSalle(salle);
        infosRepository.save(infos);
        return infos;
    }
}
