package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;

import j5_60.cinematicket.cinematicket.entity.key.ComBoDoAnDetailKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ComboDoAnDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComboDoAnDetail {
    @EmbeddedId
    ComBoDoAnDetailKey id;
    @ManyToOne
    @MapsId("id_do_an")
    @JoinColumn(name = "id_do_an")
    DoAn doAn;
    @ManyToOne
    @MapsId("id_combo")
    @JoinColumn(name = "id_combo")
    Combo combo;
    @NotNull
    @Range(min = 1, max = 1000, message = "soluong range 1 and 1OOO ")
    @Column(name = "so_luong")
    private int soLuong;
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
