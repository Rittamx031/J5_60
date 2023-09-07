package j5_60.cinematicket.cinematicket.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import j5_60.cinematicket.cinematicket.model.entity.PayMethod;

/**
 * PhuongThucThanhToanRepository
 */
public interface PayMothodRepository extends JpaRepository<PayMethod, UUID> {
    public List<PayMethod> search(@Param("txtSearch") String txtSearch);
}