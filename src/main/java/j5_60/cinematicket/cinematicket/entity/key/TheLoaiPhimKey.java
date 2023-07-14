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
public class TheLoaiPhimKey {
    @Column(name = "id_film")
    UUID id_film;

    @Column(name = "id_the_loai")
    UUID id_the_loai;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TheLoaiPhimKey))
            return false;
        TheLoaiPhimKey other = (TheLoaiPhimKey) obj;
        return Objects.equals(id_film, other.id_film) && Objects.equals(id_the_loai, other.id_the_loai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_film, id_the_loai);
    }
}
