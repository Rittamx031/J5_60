package j5_60.cinematicket.cinematicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ChucVu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name="ten")
    private String tenCV;

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
