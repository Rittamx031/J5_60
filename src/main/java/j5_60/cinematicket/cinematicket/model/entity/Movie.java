package j5_60.cinematicket.cinematicket.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "ThongTinPhim")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@NamedQueries({
        @NamedQuery(name = "ThongTinPhim.search", query = "SELECT ttp FROM ThongTinPhim ttp WHERE ten LIKE CONCAT('%',:txtSearch,'%')"
                + " OR daoDien LIKE CONCAT('%',:txtSearch,'%')"
                + "OR nhaSanXuat LIKE CONCAT('%',:txtSearch,'%')"
                + "OR dienVien LIKE CONCAT('%',:txtSearch,'%')"
                + "OR noiDung LIKE CONCAT('%',:txtSearch,'%')"
                + "OR quocGia.ten LIKE CONCAT('%',:txtSearch,'%')"
                + "OR ngonNgu.ten LIKE CONCAT('%',:txtSearch,'%')"
        )
})
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @NotBlank
    @Min(value = 1, message = "ten phim cannot length < 1")
    @Column(name = "ten")
    private String ten;
    @NotNull
    @NotBlank
    @Column(name = "dao_dien")
    private String daoDien;
    @NotNull
    @NotBlank
    @Column(name = "nha_san_xuat")
    private String nhaSanXuat;
    @NotNull
    @NotBlank
    @Column(name = "dien_vien")
    private String dienVien;
    @NotNull
    @Column(name = "nam_phat_hanh")
    private int namPhatHanh;
    @NotNull
    @Positive
    @Column(name = "thoi_luong")
    private int thoiLuong;
    @NotNull
    @Range(min = 1, max = 150, message = "tuoi gioi han range 1 -> 150")
    @Column(name = "tuoi_gioi_han")
    private int tuoiGioiHan;

    // @Column(name = "ngay_khoi_chieu")
    // private Date ngayKhoiChieu;
    @NotNull
    @NotBlank
    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "poster")
    private String poster;

    @ManyToOne
    @JoinColumn(name = "id_quoc_gia", referencedColumnName = "id")
    private Coutry quocGia;

    @ManyToOne
    @JoinColumn(name = "id_ngon_ngu", referencedColumnName = "id")
    private Language ngonNgu;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "create_by")
    private UUID createBy;

    @Column(name = "update_by")
    private UUID updateBy;

    @Column(name = "deleted")
    private boolean deleted;

}
