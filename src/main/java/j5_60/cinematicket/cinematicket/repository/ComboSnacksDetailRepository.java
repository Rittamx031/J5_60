package j5_60.cinematicket.cinematicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import j5_60.cinematicket.cinematicket.model.entity.ComboSnacksDetail;
import j5_60.cinematicket.cinematicket.model.entity.key.ComBoDoAnDetailKey;

/**
 * ComboDoAnDetailRepository
 */
public interface ComboSnacksDetailRepository extends JpaRepository<ComboSnacksDetail,ComBoDoAnDetailKey> {

    
}