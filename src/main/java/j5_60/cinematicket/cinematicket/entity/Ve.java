package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class Ve {
    @EmbeddedId
    private VeKey id;
    @ManyToOne
    @MapsId("id_lich_chieu")
    @JoinColumn(name = "id_lich_chieu")
    private LichChieu lichChieu;
    @ManyToOne
    @MapsId("id_ghe")
    @JoinColumn(name = "id_ghe")
    private Ghe ghe;
    @Column(name = "gia")
    private double gia;
    @Column(name = "trang_thai")
    private int trangThai;
    @Column(name="ngay_dat_ve")
    private LocalDate ngayDatVe;
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
    @ManyToOne
    @JoinColumn(name="id_hoa_don")
    private HoaDon hoaDon;

}
