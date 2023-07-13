package j5_60.cinematicket.cinematicket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ThongTinPhim")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ThongTinPhim {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "dao_dien")
    private String daoDien;

    @Column(name = "nha_san_xuat")
    private String nhaSanXuat;

    @Column(name = "dien_vien")
    private String dienVien;

    @Column(name = "nam_phat_hanh")
    private int namPhatHanh;

    @Column(name = "thoi_luong")
    private int thoiLuong;

    @Column(name = "tuoi_gioi_han")
    private int tuoiGioiHan;

    // @Column(name = "ngay_khoi_chieu")
    // private Date ngayKhoiChieu;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "poster")
    private String poster;

    @ManyToOne()
    @JoinColumn(name = "id_quoc_gia", referencedColumnName = "id")
    private QuocGia quocGia;

    @ManyToOne()
    @JoinColumn(name = "id_ngon_ngu", referencedColumnName = "id")
    private NgonNgu ngonNgu;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    // @Column(name = "create_by")
    // private UUID createById;
    //
    // @Column(name = "update_by")
    // private UUID updateById;

    @Column(name = "deleted")
    private boolean deleted;

}
