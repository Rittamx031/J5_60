package j5_60.cinematicket.cinematicket.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.model.entity.PhuongThucThanhToan;

/**
 * PhuongThucThanhToanRepository
 */
public interface PayMothodRepository extends JpaRepository<PhuongThucThanhToan, UUID> {
    public List<PhuongThucThanhToan> search(@Param("txtSearch") String txtSearch);
}