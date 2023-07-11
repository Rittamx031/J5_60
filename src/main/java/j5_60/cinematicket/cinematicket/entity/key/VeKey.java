package j5_60.cinematicket.cinematicket.entity.key;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VeKey
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeKey {
    @Column(name = "id_lich_chieu")
    UUID id_lich_chieu;

    @Column(name = "id_ghe")
    UUID id_ghe;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof VeKey))
            return false;
        VeKey other = (VeKey) obj;
        return Objects.equals(id_lich_chieu, other.id_lich_chieu) && Objects.equals(id_ghe, other.id_ghe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_lich_chieu, id_ghe);
    }

}