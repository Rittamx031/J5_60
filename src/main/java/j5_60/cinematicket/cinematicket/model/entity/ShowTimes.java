
package j5_60.cinematicket.cinematicket.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
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
public class ShowTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "id_phong_chieu", referencedColumnName = "id")
    private CimenaRoom phongChieu;

    @ManyToOne()
    @JoinColumn(name = "id_phim", referencedColumnName = "id")
    private Movie thongTinPhim;

    @NotNull(message = "Giờ chiếu không được để trống")
    @Column(name = "gio_chieu")
    private LocalTime gioiChieu;

    @NotNull(message = "Giờ kết thúc không được để trống")
    @Column(name = "gio_ket_thuc")
    private LocalTime gioiKetThuc;

    @NotNull(message = "Ngày chiếu không được để trống")
    @FutureOrPresent(message = "Ngày chiếu phải là ngày trong tương lai hoặc hiện tại")
    @Column(name = "ngay_chieu")
    private LocalDate ngayChieu;

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

    @AssertTrue(message = "Giờ bắt đầu phải hơn giờ kết thúc tối thiểu 2 tiếng")
    public boolean isGioChieuValid() {
        if (gioiChieu == null || gioiKetThuc == null) {
            return true; // Bỏ qua kiểm tra nếu có trường giờ trống
        }

        Duration duration = Duration.between(gioiChieu, gioiKetThuc);
        return duration.compareTo(Duration.ofHours(2)) >= 0;
    }
}
