package be.condorcet.projetapi3.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class InfosPK implements Serializable {
    @Column(name = "idclasse")
    private Integer idclasse;
    @Column(name = "idcours")
    private Integer idcours;

    public Integer getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(Integer idclasse) {
        this.idclasse = idclasse;
    }

    public Integer getIdcours() {
        return idcours;
    }

    public void setIdcours(Integer idcours) {
        this.idcours = idcours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfosPK)) return false;
        InfosPK infosPK = (InfosPK) o;
        return getIdclasse().equals(infosPK.getIdclasse()) && getIdcours().equals(infosPK.getIdcours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdclasse(), getIdcours());
    }
}
