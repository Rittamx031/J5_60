package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import j5_60.cinematicket.cinematicket.entity.key.ChiTietThanhToanKey;
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
@Table(name = "ChiTietThanhToan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietThanhToan {
    @EmbeddedId
    ChiTietThanhToanKey id;

    @ManyToOne
    @MapsId("id_hoa_don")
    @JoinColumn(name = "id_hoa_don")
    HoaDon hoaDon;

    @ManyToOne
    @MapsId("id_phuong_thuc_thanh_toan")
    @JoinColumn(name = "id_phuong_thuc_thanh_toan")
    PhuongThucThanhToan phuongThucThanhToan;
    @Column(name="tong_tien")
    private double tongTien;
    @Column(name="trang_thai")
    private int trangThai;
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
