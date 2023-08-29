package j5_60.cinematicket.cinematicket.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "KhachHang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma_khach_hang")
    private String maKhachHang;

    @NotNull
    @NotBlank(message="Please enter your name")
    @Column(name = "ho_ten")
    private String hoTen;

    @Email
    @NotNull
    @NotBlank(message="Please enter your email")
    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String pass;

    @NotNull
    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @NotNull
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @NotNull
    @NotBlank(message="Please enter your phone number")
    @Pattern(regexp = "(0|\\+84)(3[2-9]|5[2689]|7[06-9]|8[1-689]|9[0-46-9])[-.\\s]?(\\d[-.\\s]?){7}")
    @Column(name = "so_dien_thoai")
    private String sdt;

    @NotNull
    @NotBlank(message="Please enter your dia chi")
    @Column(name = "dia_chi")
    private String diaChi;

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
