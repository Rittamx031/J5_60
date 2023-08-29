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
public class GiaVeLichChieuKey {
    @Column(name = "id_lich_chieu")
    UUID id_lich_chieu;

    @Column(name = "id_loai_ghe")
    UUID id_loai_ghe;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GiaVeLichChieuKey)) return false;
        GiaVeLichChieuKey other = (GiaVeLichChieuKey) obj;
        return Objects.equals(id_lich_chieu, other.id_lich_chieu) && Objects.equals(id_loai_ghe, other.id_loai_ghe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_lich_chieu, id_loai_ghe);
    }
}
