package j5_60.cinematicket.cinematicket.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "id_chuc_vu", nullable = false)
    private ChucVu idCV;


    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @NotNull
    @NotBlank(message="Please enter your name")
    @Column(name = "ho_ten")
    private String hoTen;

    @Email
    @NotNull
    @NotBlank(message="Please enter your name")

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String pass;

    @NotNull
    @NotBlank(message="Please enter so_dien_thoai")
    @Pattern(regexp = "(0|\\+84)(3[2-9]|5[2689]|7[06-9]|8[1-689]|9[0-46-9])[-.\\s]?(\\d[-.\\s]?){7}", message = "Sdt khong dung dinh dang")
    @Column(name = "so_dien_thoai")
    private String sdt;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @NotNull
    @NotBlank(message="Please enter CCCD")
    @Column(name = "cccd_id")
    private String cccd_id;


    @NotNull
    @NotBlank(message="Please enter dia chi")
    @Column(name = "dia_chi")
    private String diaChi;

    @NotNull
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "trang_thai")
    private int trangThai;


    @Column(name = "image_nv")
    private String image;

    // @Column(name="gioi_tinh")
    // private boolean gioiTinh;

    // @Column(name="ngay_sinh")
    // private Date ngaySinh;
    // @Column(name="trang_thai")
    // private int trangThai;

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
