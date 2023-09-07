package j5_60.cinematicket.cinematicket.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;

import j5_60.cinematicket.cinematicket.model.entity.key.HoaDonDoAnKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HoaDonDoAn")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillSnack {
    @EmbeddedId
    HoaDonDoAnKey id;
    @ManyToOne
    @MapsId("id_hoa_don")
    @JoinColumn(name = "id_hoa_don")
    Bill hoaDon;
    @ManyToOne
    @MapsId("id_combo")
    @JoinColumn(name = "id_combo")
    Combo combo;
    @NotNull
    @Positive
    @Column(name = "gia")
    private double gia;
    @NotNull
    @Range(min = 1, max = 100, message = "range in 1 to 100")
    @Column(name = "soluong")
    private int soLuong;
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
}
