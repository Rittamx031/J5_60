package j5_60.cinematicket.cinematicket.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "TheLoai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TheLoai {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @NotBlank
    @Range(min=1,max= 100, message = "ten length 1 -> 100")
    @Column(name = "ten")
    private String ten;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

   @Column(name = "create_by")
   private UUID createBy;

   @Column(name = "update_by")
   private UUID updateBy;

    @Column(name = "deleted")
    private Integer deleted;
}
