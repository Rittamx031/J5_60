package j5_60.cinematicket.cinematicket.entity;

import j5_60.cinematicket.cinematicket.entity.key.GiaVeLichChieuKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "GiaVeLichChieu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiaVeLichChieu {
    @EmbeddedId
    GiaVeLichChieuKey id;

    @ManyToOne
    @MapsId("id_loai_ghe")
    @JoinColumn(name = "id_loai_ghe")
    LoaiGhe loaiGhe;

    @ManyToOne
    @MapsId("id_lich_chieu")
    @JoinColumn(name = "id_lich_chieu")
    LichChieu lichChieu;

    @Column(name = "gia")
    private double gia;
    // @Column(name = "so_luong_ghe")
    // private int slGhe;
    @Column(name = "trang_thai")
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
