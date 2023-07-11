package j5_60.cinematicket.cinematicket.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "ten")
    private String ten;

    @ManyToOne
    @JoinColumn(name = "id_loai_ghe", columnDefinition = "uniqueidentifier")
    private LoaiGhe loaiGhe;

    @ManyToOne
    @JoinColumn(name = "id_phong_chieu", columnDefinition = "uniqueidentifier")
    private PhongChieu phongChieu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private LocalDateTime  createAt;

    @Column(name = "deleted", columnDefinition = "bit default 0")
    private boolean deleted;
}
