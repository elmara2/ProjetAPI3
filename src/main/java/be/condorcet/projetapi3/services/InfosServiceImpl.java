package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.InfosPK;
import be.condorcet.projetapi3.repositories.InfosRepository;
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
}
