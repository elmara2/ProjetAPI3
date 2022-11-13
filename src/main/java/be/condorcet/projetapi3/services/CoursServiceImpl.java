package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CoursServiceImpl implements InterfCoursService{
    @Autowired
    private CoursRepository coursRepository;

    @Override
    public List<Cours> read(String intitule) {
        return coursRepository.findCoursByIntituleLike(intitule);
    }

    @Override
    public Cours rechCours(String code) {
        return coursRepository.findCoursByCodeLike(code);
    }

    @Override
    public Cours create(Cours cours) throws Exception {
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public Cours read(Cours cours) throws Exception {
        return coursRepository.findCoursByIdcours(cours.getIdcours());

    }

    @Override
    public Cours update(Cours cours) throws Exception {
        read(cours);
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public void delete(Cours cours) throws Exception {
        coursRepository.deleteCoursByIdcours(cours.getIdcours());
    }

    @Override
    public List<Cours> all() throws Exception {
        return coursRepository.findAll();
    }
}
