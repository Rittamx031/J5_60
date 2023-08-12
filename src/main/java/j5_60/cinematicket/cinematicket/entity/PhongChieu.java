package j5_60.cinematicket.cinematicket.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PhongChieu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhongChieu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @NotNull(message = "Không được để trống")
    @NotBlank(message = "Không được để trống")
    @Size(min = 2, max = 100, message = "Tên phòng phải có độ dài từ 2 đến 100 ký tự")
    @Column(name = "ten", nullable = false)
    private String ten;

    @PositiveOrZero(message = "Số lượng ghế phải là số không âm")
    @Column(name = "so_luong_ghe")
    private int soLuongGhe;

    @PositiveOrZero(message = "Trạng thái phải là số không âm")
    @Column(name = "trang_thai")
    private int trangThai;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "create_by")
    private UUID createBy;

    @Column(name = "update_by")
    private UUID updateBy;

    @Column(name = "deleted")
    private boolean deleted;

    // Other attributes and relationships specific to the "PhongChieu" entity

    // Getters and setters, as well as any additional methods
}
