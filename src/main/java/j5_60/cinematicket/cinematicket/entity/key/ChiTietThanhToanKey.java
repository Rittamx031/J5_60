// package j5_60.cinematicket.cinematicket.entity.key;

// import java.util.Objects;
// import java.util.UUID;

// import jakarta.persistence.Column;
// import jakarta.persistence.Embeddable;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Embeddable
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class ChiTietThanhToanKey {
//     @Column(name = "id_hoa_don")
//     UUID id_hoa_don;

//     @Column(name = "id_phuong_thuc_thanh_toan")
//     UUID id_phuong_thuc_thanh_toan;

//     @Override
//     public boolean equals(Object o) {
//         if (this == o)
//             return true;
//         if (!(o instanceof ChiTietThanhToanKey))
//             return false;
//         ChiTietThanhToanKey that = (ChiTietThanhToanKey) o;
//         return Objects.equals(id_hoa_don, that.id_hoa_don) &&
//                 Objects.equals(id_phuong_thuc_thanh_toan, that.id_phuong_thuc_thanh_toan);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(id_hoa_don, id_phuong_thuc_thanh_toan);
//     }
// }
