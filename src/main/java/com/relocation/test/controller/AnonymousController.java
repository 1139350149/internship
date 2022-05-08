package com.relocation.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.relocation.test.entity.EnterpriseTurnoverMeasurement;
import com.relocation.test.entity.RelocationPeopleInfo;
import com.relocation.test.repository.*;
import com.relocation.test.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnonymousController {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Autowired
    PeopleInfoRepository peopleInfoRepository;

    @Autowired
    PeopleDetailInfoRepository detailInfoRepository;

    @Autowired
    DistributionOfBuildingExpensesSettlementRepository settlementRepository;

    @Autowired
    RelocationPeopleDwellingFacilityCompensationRepository compensationRepository;


    @RequestMapping(value = "/anonymous/enterprise/query", method = RequestMethod.GET)
    public JSONObject anonymousEnterpriseQuery(@RequestParam(value = "name") String name
            , @RequestParam(value = "project") String project) {
        System.out.println("anonymous enterprise query");
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (!enterpriseRepository.existsEnterpriseTurnoverMeasurementByEnterpriseNameAndOccupiedProject(name, project)) {
            meta.put("msg", "该企业信息不存在");
            meta.put("status", 302);
        } else {
            EnterpriseTurnoverMeasurement info =
                    enterpriseRepository.findEnterpriseTurnoverMeasurementByEnterpriseNameAndOccupiedProject(name, project);
            JSONObject temp = new JSONObject();
            JsonUtil.loadEnterpriseInfo(temp, info);
            data.put("0", temp);
            res.put("data", data);
            meta.put("msg", "查找成功");
            meta.put("status", 202);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/anonymous/people/query")
    public JSONObject anonymousPeopleQuery(@RequestParam(value = "name") String name, @RequestParam(value = "idcard") String idCard) {
        System.out.println("anonymous people query");
        StringBuilder builder = new StringBuilder();
        int status = 202;
        JSONObject res = JsonUtil.getPeopleInfoJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (peopleInfoRepository.existsRelocationPeopleInfoByOwnerAndIdCard(name, idCard)) {
            RelocationPeopleInfo peopleInfo = peopleInfoRepository.findRelocationPeopleInfoByOwnerAndIdCard(name, idCard);
            JSONObject people = new JSONObject();
            JsonUtil.loadPeopleInfo(people, peopleInfo);
            data.put("people", people);
            builder.append("户主信息查询成功; ");

            JSONObject families = new JSONObject();
            for (int i = 0; i < peopleInfo.getRelocationPeopleDetailInfos().size(); i++) {
                JSONObject family = new JSONObject();
                JsonUtil.loadPeopleDetailInfo(family, peopleInfo.getRelocationPeopleDetailInfos().get(i));
                families.put(i + "", family);
            }
            data.put("families", families);
            builder.append("户主家属信息查询成功, 共有")
                    .append(peopleInfo.getRelocationPeopleDetailInfos().size())
                    .append("人; ");

            JSONObject relocation = new JSONObject();
            if (peopleInfo.getSettlement() != null) {
                JsonUtil.loadRelocationSettlement(relocation, peopleInfo);
                data.put("relocation", relocation);
                builder.append("户主动迁信息查询成功");
            } else {
                builder.append("该户主没有登记任何动迁信息");
                status = 302;
            }

            meta.put("msg", builder.toString());
            meta.put("status", status);
        } else {
            status = 302;
            meta.put("msg", "此人不存在");
            meta.put("status", status);
        }
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }
}
