package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.entity.ComboDoAnDetail;
import j5_60.cinematicket.cinematicket.entity.key.ComBoDoAnDetailKey;

/**
 * ComboDoAnDetailRepository
 */
public interface ComboDoAnDetailRepository extends JpaRepository<ComboDoAnDetail,ComBoDoAnDetailKey> {

    
}