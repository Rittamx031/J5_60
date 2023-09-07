package j5_60.cinematicket.cinematicket.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Ghe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ten")
    private String ten;

    @NotNull(message = "Không được để trống")
    @Column(name = "row")
    private int hang;

    @NotNull(message = "Không được để trống")
    @Column(name = "cot")
    private int cot;

    @ManyToOne
    @JoinColumn(name = "id_loai_ghe", columnDefinition = "uniqueidentifier")
    private SeatType loaiGhe;

    @ManyToOne
    @JoinColumn(name = "id_phong_chieu", columnDefinition = "uniqueidentifier")
    private CimenaRoom phongChieu;

    @Min(value = 0, message = "Trạng thái phải là số không âm")
    @Max(value = 1, message = "Trạng thái phải là số 0 hoặc 1")
    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private UUID updateBy;

    @Column(name = "create_by")
    private UUID createBy;

    @Column(name = "deleted")
    private boolean deleted;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Seat))
            return false;
        Seat other = (Seat) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
