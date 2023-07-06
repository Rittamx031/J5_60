package j5_60.cinematicket.cinematicket.entity.key;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;

public class ChiTietThanhToanKey {
    @Column(name = "id_hoa_don")
    UUID id_hoa_don;
    
    @Column(name = "id_phuong_thuc_thanh_toan")
    UUID id_phuong_thuc_thanh_toan;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ChiTietThanhToanKey)) return false;
        ChiTietThanhToanKey other = (ChiTietThanhToanKey) obj;
        return Objects.equals(id_hoa_don, other.id_hoa_don) && Objects.equals(id_phuong_thuc_thanh_toan, other.id_phuong_thuc_thanh_toan);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id_hoa_don, id_phuong_thuc_thanh_toan);
    }
}
