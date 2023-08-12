package j5_60.cinematicket.cinematicket.entity;

import j5_60.cinematicket.cinematicket.entity.key.GiaVeLichChieuKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private LoaiGhe loaiGhe;

    @ManyToOne
    @MapsId("id_lich_chieu")
    @JoinColumn(name = "id_lich_chieu")
    private LichChieu lichChieu;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "1000", message = "Giá phải lớn hơn hoặc bằng 1000")
    @DecimalMax(value = "1000000", message = "Giá phải nhỏ hơn hoặc bằng 1000000")
    @Column(name = "gia")
    private double gia;

    @PositiveOrZero(message = "Trạng thái phải là số không âm")
    @Column(name = "trang_thai")
    private int trangThai;

    @PastOrPresent(message = "Thời gian tạo phải là thời gian trong quá khứ hoặc hiện tại")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @PastOrPresent(message = "Thời gian cập nhật phải là thời gian trong quá khứ hoặc hiện tại")
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private UUID updateBy;

    @Column(name = "create_by")
    private UUID createBy;

    @Column(name = "deleted")
    private boolean deleted;

    // Other attributes and relationships specific to the "GiaVeLichChieu" entity

    // Getters and setters, as well as any additional methods
}
