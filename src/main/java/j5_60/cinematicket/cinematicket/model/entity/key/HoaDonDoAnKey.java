package j5_60.cinematicket.cinematicket.model.entity.key;

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
public class HoaDonDoAnKey {
    @Column(name = "id_combo")
    UUID id_combo;

    @Column(name = "id_hoa_don")
    UUID id_hoa_don;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof HoaDonDoAnKey))
            return false;
        HoaDonDoAnKey other = (HoaDonDoAnKey) obj;
        return Objects.equals(id_combo, other.id_combo) && Objects.equals(id_hoa_don, other.id_hoa_don);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_combo, id_hoa_don);
    }
}
