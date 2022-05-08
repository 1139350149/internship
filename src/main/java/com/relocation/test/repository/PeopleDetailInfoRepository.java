package com.relocation.test.repository;

import com.relocation.test.entity.RelocationPeopleDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PeopleDetailInfoRepository extends JpaRepository<RelocationPeopleDetailInfo, Long> {
    RelocationPeopleDetailInfo findRelocationPeopleDetailInfosById(int id);
    RelocationPeopleDetailInfo findRelocationPeopleDetailInfoByIdCard(String id);
    boolean existsRelocationPeopleDetailInfoByIdCardAndIdNot(String idCard, int id);
    boolean existsRelocationPeopleDetailInfoById(int id);
    boolean existsRelocationPeopleDetailInfoByIdCard(String idCard);
    boolean existsRelocationPeopleDetailInfoByName(String name);
    List<RelocationPeopleDetailInfo> findRelocationPeopleDetailInfosByName(String name);
    List<RelocationPeopleDetailInfo> findAllByOrderByIdAsc();
    List<RelocationPeopleDetailInfo> findRelocationPeopleDetailInfosByRelocationPeopleInfo_Id(int id);
    List<RelocationPeopleDetailInfo> findRelocationPeopleDetailInfosByRelocationPeopleInfo_IdOrderById(int id);
    @Transactional
    @Modifying
    int deleteRelocationPeopleDetailInfoById(int id);
}
