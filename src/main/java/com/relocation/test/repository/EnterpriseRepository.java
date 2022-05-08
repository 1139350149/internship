package com.relocation.test.repository;

import com.relocation.test.entity.EnterpriseTurnoverMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<EnterpriseTurnoverMeasurement, Long> {
    List<EnterpriseTurnoverMeasurement> findAllByOrderByIdAsc();
    boolean existsEnterpriseTurnoverMeasurementByOccupiedProject(String project);
    boolean existsEnterpriseTurnoverMeasurementById(int id);
    boolean existsEnterpriseTurnoverMeasurementByOccupiedProjectAndIdNot(String project, int id);
    boolean existsEnterpriseTurnoverMeasurementByEnterpriseNameAndOccupiedProject(String name, String project);
    List<EnterpriseTurnoverMeasurement> findEnterpriseTurnoverMeasurementsByEnterpriseNameContains(String name);
    List<EnterpriseTurnoverMeasurement> findEnterpriseTurnoverMeasurementsByOccupiedProjectContains(String project);
    EnterpriseTurnoverMeasurement findEnterpriseTurnoverMeasurementById(int id);
    EnterpriseTurnoverMeasurement findEnterpriseTurnoverMeasurementByEnterpriseNameAndOccupiedProject(String name, String project);
    @Transactional
    @Modifying
    int deleteEnterpriseTurnoverMeasurementById(int id);
}
