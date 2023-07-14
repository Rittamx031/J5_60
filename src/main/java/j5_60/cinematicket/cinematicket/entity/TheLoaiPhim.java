package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import j5_60.cinematicket.cinematicket.entity.key.TheLoaiPhimKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TheLoaiPhim")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheLoaiPhim {
    @EmbeddedId
    TheLoaiPhimKey id;
    @ManyToOne
    @MapsId("id_film")
    @JoinColumn(name = "id_film")
    ThongTinPhim thongTinPhim;
    @ManyToOne
    @MapsId("id_the_loai")
    @JoinColumn(name = "id_the_loai")
    TheLoai theLoai;
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
