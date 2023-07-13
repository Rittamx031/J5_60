package j5_60.cinematicket.cinematicket.entity.key;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComBoDoAnDetailKey {
      @Column(name = "id_combo")
    UUID id_combo;
    
    @Column(name = "id_do_an")
    UUID id_do_an;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ComBoDoAnDetailKey)) return false;
        ComBoDoAnDetailKey other = (ComBoDoAnDetailKey) obj;
        return Objects.equals(id_combo, other.id_combo) && Objects.equals(id_do_an, other.id_do_an);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id_combo, id_do_an);
    }
}
