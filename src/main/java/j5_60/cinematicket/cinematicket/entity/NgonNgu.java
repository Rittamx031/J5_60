package j5_60.cinematicket.cinematicket.entity;



import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "NgonNgu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NgonNgu {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

//    @Column(name = "create_by")
//    private UUID createById;
//
//    @Column(name = "update_by")
//    private UUID updateById;

    @Column(name = "deleted")
    private Integer deleted;
}