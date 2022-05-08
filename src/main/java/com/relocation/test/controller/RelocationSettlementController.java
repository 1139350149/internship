package com.relocation.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.relocation.test.entity.DistributionOfBuildingExpensesSettlement;
import com.relocation.test.entity.RelocationPeopleDetailInfo;
import com.relocation.test.entity.RelocationPeopleDwellingFacilityCompensation;
import com.relocation.test.entity.RelocationPeopleInfo;
import com.relocation.test.repository.DistributionOfBuildingExpensesSettlementRepository;
import com.relocation.test.repository.PeopleInfoRepository;
import com.relocation.test.repository.RelocationPeopleDwellingFacilityCompensationRepository;
import com.relocation.test.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

@RestController
public class RelocationSettlementController {
    @Autowired
    PeopleInfoRepository peopleInfoRepository;

    @Autowired
    DistributionOfBuildingExpensesSettlementRepository settlementRepository;

    @Autowired
    RelocationPeopleDwellingFacilityCompensationRepository compensationRepository;

    @RequestMapping("/relocation/totalNums")
    JSONObject totalNums() {
        System.out.println("relocation total numbers");
        List<DistributionOfBuildingExpensesSettlement> settlements = settlementRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        data.put("totalNums", settlements.size());
        JSONObject meta = new JSONObject();
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping("/relocation/list")
    JSONObject list(@RequestParam(value = "offset") int offset, @RequestParam(value = "nums") int nums) {
        System.out.println("list relocation ");
        List<DistributionOfBuildingExpensesSettlement> settlements = settlementRepository.findAllByOrderByIdAsc();
        int dest = Math.min((offset + nums), settlements.size());
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        int count = 0;
        if (offset < settlements.size()) {
            JSONObject relocations = new JSONObject();
            for (int i = offset; i < dest; i++) {
                JSONObject relocation = new JSONObject();
                JsonUtil.loadRelocationSettlement(relocation, settlements.get(i).getRelocationPeopleInfo());
                relocations.put(count++ + "", relocation);
            }
            data.put("relocations", relocations);
            meta.put("msg", "获取成功");
            meta.put("status", 200);
            res.put("data", data);
        } else {
            meta.put("msg", "获取失败");
            meta.put("status", 301);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping("/relocation/query/head")
    JSONObject queryRelocationByName(@RequestParam(value = "head") String name) {
        System.out.println("query relocation by name ");
        List<RelocationPeopleInfo> infos = peopleInfoRepository.findRelocationPeopleInfosByOwner(name);
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getSettlement() != null) {
                JSONObject relocation = new JSONObject();
                JsonUtil.loadRelocationSettlement(relocation, infos.get(i));
                data.put(i + "", relocation);
            }
        }
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping("/relocation/query/idcard")
    JSONObject queryRelocationByIdCard(@RequestParam(value = "idcard") String idCard) {
        System.out.println("query relocation by idcard");
        RelocationPeopleInfo info = peopleInfoRepository.findRelocationPeopleInfoByIdCard(idCard);
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (info != null) {
            JSONObject relocation = new JSONObject();
            if (info.getSettlement() != null) {
                JsonUtil.loadRelocationSettlement(relocation, info);
                data.put("0", relocation);
                meta.put("msg", "获取成功");
                meta.put("status", 200);
            } else {
                meta.put("msg", "获取失败，该户主没有动迁信息");
                meta.put("status", 302);
            }
        } else {
            meta.put("msg", "获取失败，身份证号不存在");
            meta.put("status", 303);
        }
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/relocation/add", method = RequestMethod.POST)
    JSONObject add(@RequestParam(value = "headid") int headid,
                   @RequestParam(value = "address") String address,
                   @RequestParam(value = "oarea") double originalArea,
                   @RequestParam(value = "ovalue") double originalValue,
                   @RequestParam(value = "darea") double distributedArea,
                   @RequestParam(value = "dvalue") double distributedValue,
                   @RequestParam(value = "sremark") String settlementRemark,
                   @RequestParam(value = "officer") String officer,
                   @RequestParam(value = "sfdate") String settlementFillDate,
                   @RequestParam(value = "sfname") String settlementFillName,
                   @RequestParam(value = "mdate") String measurementDate,
                   @RequestParam(value = "oproject") String oProject,
                   @RequestParam(value = "wcount") int wellCount,
                   @RequestParam(value = "wvalue") double wellValue,
                   @RequestParam(value = "waarea") double wallArea,
                   @RequestParam(value = "wavalue") double wallValue,
                   @RequestParam(value = "carea") double cementArea,
                   @RequestParam(value = "cvalue") double cementValue,
                   @RequestParam(value = "cearea") double cellarVolume,
                   @RequestParam(value = "cevalue") double cellarValue,
                   @RequestParam(value = "cfdate") String compensationFillDate,
                   @RequestParam(value = "cfname") String compensationFillName,
                   @RequestParam(value = "cremark") String compensationRemark) {
        System.out.println("relocation add");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date settlementFillDateParse = null;
        Date measurementDateParse = null;
        Date compensationFillDateParse = null;
        if (peopleInfoRepository.existsRelocationPeopleInfoById(headid)) {
            RelocationPeopleInfo head = peopleInfoRepository.findRelocationPeopleInfoById(headid);
            if (head.getSettlement() != null || head.getCompensation() != null) {
                meta.put("msg", "创建失败, 该户主已有动迁信息");
                meta.put("status", 302);
            } else {
                double originalFacilityValue = (double) Math.round((wellValue + wallValue + cellarValue + cementValue) * 100) / 100;
                double settleAmount = (double) Math.round((originalValue - distributedValue) * 100) / 100;
                double settle = (double) Math.round((originalValue + originalFacilityValue - distributedValue) * 100) / 100;
                try {
                    settlementFillDateParse = new Date(format.parse(settlementFillDate).getTime());
                    measurementDateParse = new Date(format.parse(measurementDate).getTime());
                    compensationFillDateParse = new Date(format.parse(compensationFillDate).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    meta.put("msg", "创建失败，日期格式错误");
                    meta.put("status", 303);
                }
                DistributionOfBuildingExpensesSettlement settlement = new DistributionOfBuildingExpensesSettlement(head
                        , address, originalArea, originalValue, originalFacilityValue, distributedArea, distributedValue
                        , settleAmount, settlementRemark, officer
                        , settle, false, null
                        , settlementFillDateParse, settlementFillName);
                settlementRepository.save(settlement);
                RelocationPeopleDwellingFacilityCompensation compensation = new RelocationPeopleDwellingFacilityCompensation(
                        head, measurementDateParse, oProject, originalFacilityValue, wellCount, wellValue, wallArea, wallValue
                        , cementArea, cementValue, cellarVolume, cellarValue, compensationFillDateParse, compensationFillName
                        , compensationRemark);
                compensationRepository.save(compensation);
                meta.put("msg", "创建成功");
                meta.put("status", 202);
            }
        } else {
            meta.put("msg", "户主id不存在,添加失败");
            meta.put("status", 302);
        }

        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/relocation/update", method = RequestMethod.POST)
    JSONObject updateRelocation(@RequestParam(value = "sid") int sid,
                                @RequestParam(value = "cid") int cid,
                                @RequestParam(value = "address") String address,
                                @RequestParam(value = "oarea") double originalArea,
                                @RequestParam(value = "ovalue") double originalValue,
                                @RequestParam(value = "darea") double distributedArea,
                                @RequestParam(value = "dvalue") double distributedValue,
                                @RequestParam(value = "sremark") String settlementRemark,
                                @RequestParam(value = "officer") String officer,
                                @RequestParam(value = "sfdate") String settlementFillDate,
                                @RequestParam(value = "sfname") String settlementFillName,
                                @RequestParam(value = "mdate") String measurementDate,
                                @RequestParam(value = "oproject") String oProject,
                                @RequestParam(value = "wcount") int wellCount,
                                @RequestParam(value = "wvalue") double wellValue,
                                @RequestParam(value = "waarea") double wallArea,
                                @RequestParam(value = "wavalue") double wallValue,
                                @RequestParam(value = "carea") double cementArea,
                                @RequestParam(value = "cvalue") double cementValue,
                                @RequestParam(value = "cearea") double cellarVolume,
                                @RequestParam(value = "cevalue") double cellarValue,
                                @RequestParam(value = "cfdate") String compensationFillDate,
                                @RequestParam(value = "cfname") String compensationFillName,
                                @RequestParam(value = "cremark") String compensationRemark,
                                @RequestParam(value = "ppercentage") double pledgePercentage) {
        System.out.println("relocation update");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date settlementFillDateParse = null;
        Date measurementDateParse = null;
        Date compensationFillDateParse = null;
        try {
            settlementFillDateParse = new Date(format.parse(settlementFillDate).getTime());
            measurementDateParse = new Date(format.parse(measurementDate).getTime());
            compensationFillDateParse = new Date(format.parse(compensationFillDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            meta.put("msg", "修改失败，日期格式错误");
            meta.put("status", 303);
        }
        double originalFacilityValue = (double) Math.round((wellValue + wallValue + cellarValue + cementValue) * 100) / 100;
        double settleAmount = (double) Math.round((originalValue - distributedValue) * 100) / 100;
        double settle = (double) Math.round((originalValue + originalFacilityValue - distributedValue) * 100) / 100;
        if (settlementRepository.existsDistributionOfBuildingExpensesSettlementById(sid)) {
            DistributionOfBuildingExpensesSettlement settlement = settlementRepository.findDistributionOfBuildingExpensesSettlementById(sid);
            settlement.update(address, originalArea, originalValue, originalFacilityValue, distributedArea, distributedValue
                    , settleAmount, settlementRemark, officer, settle, settlementFillDateParse, settlementFillName
                    , pledgePercentage);
            settlementRepository.save(settlement);
            meta.put("msg", "修改成功");
            meta.put("status", 202);
        } else {
            meta.put("msg", "修改失败，结算信息编号不存在");
            meta.put("status", 302);
        }
        if (compensationRepository.existsRelocationPeopleDwellingFacilityCompensationById(cid)) {
            RelocationPeopleDwellingFacilityCompensation compensation = compensationRepository.findRelocationPeopleDwellingFacilityCompensationById(cid);
            compensation.update(measurementDateParse, oProject, originalFacilityValue, wellCount, wellValue, wallArea, wallValue
                    , cementArea, cementValue, cellarVolume, cellarValue, compensationFillDateParse, compensationFillName
                    , compensationRemark);
            compensationRepository.save(compensation);
            meta.put("msg", "修改成功");
            meta.put("status", 202);
        } else {
            meta.put("msg", "修改失败，补偿信息编号不存在");
            meta.put("status", 302);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/relocation/delete", method = RequestMethod.POST)
    JSONObject deleteRelocation(@RequestParam(value = "sid") int sid, @RequestParam(value = "cid") int cid) {
        System.out.println("relocation delete");
        StringBuilder msg = new StringBuilder();
        int status;
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        if (settlementRepository.existsDistributionOfBuildingExpensesSettlementById(sid)) {
            int consequence = settlementRepository.deleteDistributionOfBuildingExpensesSettlementById(sid);
            msg.append(consequence != 0 ? "删除结算信息成功," : "删除结算信息失败,");
            status = consequence != 0 ? 204 : 301;
        } else {
            msg.append("结算信息编号不存在,");
            status = 302;
        }
        if (compensationRepository.existsRelocationPeopleDwellingFacilityCompensationById(cid)) {
            int consequence = compensationRepository.deleteRelocationPeopleDwellingFacilityCompensationById(cid);
            msg.append(consequence != 0 ? "删除补偿信息成功" : "删除补偿信息失败");
            status = consequence != 0 ? 204 : 301;
        } else {
            msg.append("补偿信息编号不存在");
            status = status > 300 ? 302 : status;
        }
        meta.put("msg", msg);
        meta.put("status", status);
        res.put("meta", meta);
        return res;
    }
}
