package j5_60.cinematicket.cinematicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String pass;

    @Column(name = "so_dien_thoai")
    private String sdt;
    @Column(name = "ma_nhan_vien")

    private String maNhanVien;

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
