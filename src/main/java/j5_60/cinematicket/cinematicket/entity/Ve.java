package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import j5_60.cinematicket.cinematicket.entity.key.VeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ve")
@AllArgsConstructor
@NoArgsConstructor
// @NamedQueries({
// @NamedQuery(name="Ve.fillter" ,query="SELECT ve FROM Ve ve WHERE
// LichChieu:id")
// })
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
    @NotNull
    @Column(name = "gia")
    private double gia;

    @Column(name = "trang_thai")
    private int trangThai;

    @NotNull
    @Column(name = "ngay_dat_ve")
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
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;
}
