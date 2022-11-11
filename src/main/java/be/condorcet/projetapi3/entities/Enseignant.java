package be.condorcet.projetapi3.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "ENSEIGNANT", schema = "ORA10" , catalog = "orcl")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "classe_generator")
    @SequenceGenerator(name = "classe_generator", sequenceName = "MASEQUENCE",allocationSize = 1)
    private Integer idenseignant;
    @NonNull
    private String matricule;
    private String nom;
    private String prenom;
    private String tel;
    private String chargesem;
    private double salairemensu;
    private LocalDate dateengag;

    @ManyToOne
    @JoinColumn(name = "idsalle")
    private Salle salle;

    @JsonIgnore
    @OneToMany(mappedBy = "enseignant")
    @ToString.Exclude
    private List<Infos> listInfos;

}
