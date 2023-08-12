package j5_60.cinematicket.cinematicket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Ghe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ghe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @NotNull(message = "Không được để trống")
    @NotBlank(message = "Không được để trống")
    @Length(min = 1, max = 255, message = "Tên phải có độ dài từ 1 đến 255 ký tự")
    @Column(name = "ten")
    private String ten;

    @NotNull(message = "Không được để trống")
    @NotBlank(message = "Không được để trống")
    @Column(name = "hang")
    private String hang;

    @NotNull(message = "Không được để trống")
    @NotBlank(message = "Không được để trống")
    @Column(name = "cot")
    private String cot;

    @ManyToOne
    @JoinColumn(name = "id_loai_ghe", columnDefinition = "uniqueidentifier")
    private LoaiGhe loaiGhe;

    @ManyToOne
    @JoinColumn(name = "id_phong_chieu", columnDefinition = "uniqueidentifier")
    private PhongChieu phongChieu;

    @Min(value = 0, message = "Trạng thái phải là số không âm")
    @Max(value = 1, message = "Trạng thái phải là số 0 hoặc 1")
    @Column(name = "trang_thai")
    private Integer trangThai;

    @PastOrPresent(message = "Thời gian cập nhật phải là thời gian trong quá khứ hoặc hiện tại")
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PastOrPresent(message = "Thời gian tạo phải là thời gian trong quá khứ hoặc hiện tại")
    @Column(name = "create_at")
    private LocalDateTime  createAt;

    @Column(name = "deleted", columnDefinition = "bit default 0")
    private boolean deleted;

    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
        updateAt = createAt;
    }

    @PreUpdate
    public void preUpdate() {
        updateAt = LocalDateTime.now();
    }
}
