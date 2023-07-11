package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import j5_60.cinematicket.cinematicket.entity.key.HoaDonDoAnKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="HoaDonDoAn")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDoAn {
    @EmbeddedId
    HoaDonDoAnKey id;
    @ManyToOne
    @MapsId("id_hoa_don")
    @JoinColumn(name = "id_hoa_don")
    HoaDon hoaDon;
    @ManyToOne
    @MapsId("id_combo")
    @JoinColumn(name = "id_combo")
    Combo combo;
    @Column(name = "gia")
    private double gia;
    @Column(name = "so_luong")
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
