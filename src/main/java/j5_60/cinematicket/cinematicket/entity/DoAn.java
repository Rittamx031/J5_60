package j5_60.cinematicket.cinematicket.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DoAn")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "DoAn.search", query = "SELECT doan FROM DoAn doan WHERE ten LIKE CONCAT('%',:txtSearch,'%')")
})
public class DoAn {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;
    @NotNull(message = "Ten can not null")
    @NotBlank
    @NotEmpty
    @Column(name = "ten")
    private String ten;
    @NotNull(message = "gia can not null")
    @Column(name = "gia")
    private double gia;
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
