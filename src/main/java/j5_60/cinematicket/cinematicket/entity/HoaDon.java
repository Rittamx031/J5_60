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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="HoaDon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ghi_chu")
    private String ghiChu;
    @Column(name = "tong_gia")
    private double tongGia;
    @Column(name = "tong_gia_sau_giam")
    private String tongGiaSauGiam;
    @Column(name = "thoi_gian_thanh_toan")
    private LocalDateTime thoiGianThanhToan;
    @Column(name = "trang_thai")
    private int trang_Thai;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @Column(name = "update_by")
    private UUID updateBy;
    @Column(name = "create_by")
    private UUID createBy;
    @Column(name= "deleted") 
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name="id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @Column(name="id_khach_hang") 
    private KhachHang khachHang;
}
