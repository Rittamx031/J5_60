package j5_60.cinematicket.cinematicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "LichChieu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class LichChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "id_phong_chieu",referencedColumnName = "id")
    private PhongChieu phongChieu;

    @ManyToOne()
    @JoinColumn(name = "id_phim",referencedColumnName = "id")
    private ThongTinPhim thongTinPhim;

    @Column(name = "gio_chieu")
    private LocalTime gioiChieu;
    @Column(name = "gio_ket_thuc")
    private LocalTime gioiKetThuc;
    @Column(name = "ngay_chieu")
    private LocalDateTime ngayChieu;
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
