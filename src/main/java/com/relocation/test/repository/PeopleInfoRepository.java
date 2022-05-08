package com.relocation.test.repository;

import com.relocation.test.entity.RelocationPeopleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PeopleInfoRepository extends JpaRepository<RelocationPeopleInfo, Long> {
    RelocationPeopleInfo findRelocationPeopleInfoByOwnerAndIdCard(String name, String idCard);
    boolean existsRelocationPeopleInfoByOwnerAndIdCard(String name, String idCard);
    List<RelocationPeopleInfo> findAllByOrderByIdAsc();
    List<RelocationPeopleInfo> findRelocationPeopleInfosByOwner(String name);
    RelocationPeopleInfo findRelocationPeopleInfoByIdCard(String id);
    boolean existsRelocationPeopleInfoByIdCard(String idCard);
    boolean existsRelocationPeopleInfoById(int id);
    boolean existsRelocationPeopleInfoByIdCardAndIdNot(String idCard, int id);
    RelocationPeopleInfo findRelocationPeopleInfoByIdCardAndIdNot(String idCard, int id);
    boolean existsRelocationPeopleInfoByOwner(String name);
    RelocationPeopleInfo findRelocationPeopleInfoById(int id);
    @Transactional
    @Modifying
    int deleteRelocationPeopleInfoByIdCard(String id);
    @Transactional
    @Modifying
    int deleteRelocationPeopleInfoById(int id);

}
