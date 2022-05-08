package com.relocation.test.repository;

import com.relocation.test.entity.RelocationPeopleDwellingFacilityCompensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface RelocationPeopleDwellingFacilityCompensationRepository extends JpaRepository<RelocationPeopleDwellingFacilityCompensation, Long> {
    RelocationPeopleDwellingFacilityCompensation findRelocationPeopleDwellingFacilityCompensationById(int id);
    boolean existsRelocationPeopleDwellingFacilityCompensationById(int id);
    @Transactional
    @Modifying
    int deleteRelocationPeopleDwellingFacilityCompensationById(int id);
}
