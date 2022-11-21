package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ClasseServiceImpl implements InterfClasseService {
    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public List<Classe> read(String specialite) {
        return classeRepository.findClassesBySpecialiteLike(specialite+"%");
    }

    @Override
    public Classe rechClasse(String sigle) {
        return classeRepository.findClasseBySigleLike(sigle);
    }


    @Override
    public Classe create(Classe classe) throws Exception {
        classeRepository.save(classe);
        return classe;
    }

    @Override
    public Classe read(Classe classe) throws Exception {
        return classeRepository.findById(classe.getIdclasse()).get();
    }

    @Override
    public Classe update(Classe classe) throws Exception {
        read(classe);
        classeRepository.save(classe);
        return classe;
    }

    @Override
    public void delete(Classe classe) throws Exception {
        classeRepository.deleteById(classe.getIdclasse());

    }

    @Override
    public List<Classe> all() throws Exception {
        return classeRepository.findAll();
    }

    @Override
    public Page<Classe> allp(Pageable pageable) throws Exception {
        return classeRepository.findAll(pageable);
    }
}
