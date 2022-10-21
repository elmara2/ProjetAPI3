package be.condorcet.projetapi3;

import be.condorcet.projetapi3.repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetApi3Application {
    @Autowired
    private ClasseRepository classeRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProjetApi3Application.class, args);
    }

}
