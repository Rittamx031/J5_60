package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HoaDon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "HoaDon.search", query = "SELECT hoaDon FROM HoaDon hoaDon WHERE" +
                " hoaDon.id LIKE CONCAT('%', :txtSearch, '%') OR " +
                "hoaDon.ghiChu LIKE CONCAT('%', :txtSearch, '%') OR " +
                "hoaDon.tongGia LIKE CONCAT('%',:txtSearch,'%') OR " +
                "hoaDon.tongGiaSauGiam LIKE CONCAT('%',:txtSearch,'%') OR " +
                "hoaDon.thoiGianThanhToan LIKE CONCAT('%',:txtSearch,'%') OR " +
                "hoaDon.trangThai LIKE CONCAT('%',:txtSearch,'%') ")
})
public class HoaDon {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;
    @NotNull
    @Positive
    @Column(name = "tong_gia")
    private double tongGia;
    @NotNull
    @Positive
    @Column(name = "tong_gia_sau_giam")
    private String tongGiaSauGiam;
    @Column(name = "thoi_gian_thanh_toan")
    private LocalDateTime thoiGianThanhToan;
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
    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_phuong_thuc_thanh_toan")
    private PhuongThucThanhToan phuongThucThanhToan;
}
