package ftn.pharmacyX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.pharmacyX.model.SupplyOrder;

public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

	List<SupplyOrder> findAllByDeleted(boolean deleted);
}
