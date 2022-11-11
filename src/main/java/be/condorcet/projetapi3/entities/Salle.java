package be.condorcet.projetapi3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "SALLE", schema = "ORA10" , catalog = "orcl")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "classe_generator")
    @SequenceGenerator(name = "classe_generator", sequenceName = "MASEQUENCE",allocationSize = 1)
    private Integer idsalle;
    @NonNull
    private String sigle;
    private Integer capacite;
    @JsonIgnore
    @OneToMany(mappedBy = "salle")
    @ToString.Exclude
    private List<Infos> listInfos;

    @JsonIgnore
    @OneToMany(mappedBy = "salle")
    @ToString.Exclude
    private List<Enseignant> listEnseignant;
}
