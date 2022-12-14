package be.condorcet.projetapi3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "CLASSE", schema = "ORA10" , catalog = "orcl")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "classe_generator")
    @SequenceGenerator(name = "classe_generator", sequenceName = "MASEQUENCE",allocationSize = 1)
    private Integer idclasse;
    @NonNull
    private String sigle;
    private Integer annee;
    private String specialite;
    private Integer nbreeleves;
    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    @ToString.Exclude
    private List<Infos> listInfos;
}
