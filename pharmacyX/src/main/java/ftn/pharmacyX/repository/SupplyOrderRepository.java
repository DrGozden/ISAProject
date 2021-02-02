package ftn.pharmacyX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.pharmacyX.model.SupplyOrder;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

	List<SupplyOrder> findAllByDeleted(boolean deleted);
}
