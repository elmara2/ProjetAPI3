package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CoursServiceImpl implements InterfCoursService{
    @Autowired
    private CoursRepository coursRepository;
    @Override
    public Cours create(Cours cours) throws Exception {
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public Cours read(Cours cours) throws Exception {
        Optional<Cours> ocrs = coursRepository.findById(cours.getIdcours());
        return ocrs.get();
    }

    @Override
    public Cours update(Cours cours) throws Exception {
        read(cours);
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public void delete(Cours cours) throws Exception {
        coursRepository.deleteById(cours.getIdcours());
    }

    @Override
    public List<Cours> all() throws Exception {
        return coursRepository.findAll();
    }
}
