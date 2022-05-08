package com.relocation.test.repository;

import com.relocation.test.entity.DistributionOfBuildingExpensesSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DistributionOfBuildingExpensesSettlementRepository extends JpaRepository<DistributionOfBuildingExpensesSettlement, Long> {
    List<DistributionOfBuildingExpensesSettlement> findAllByOrderByIdAsc();
    DistributionOfBuildingExpensesSettlement findDistributionOfBuildingExpensesSettlementById(int id);
    boolean existsDistributionOfBuildingExpensesSettlementById(int id);
    @Transactional
    @Modifying
    int deleteDistributionOfBuildingExpensesSettlementById(int id);
}
