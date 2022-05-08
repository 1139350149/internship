package com.relocation.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.relocation.test.entity.DistributionOfBuildingExpensesSettlement;
import com.relocation.test.entity.EnterpriseTurnoverMeasurement;
import com.relocation.test.entity.RelocationPeopleInfo;
import com.relocation.test.repository.DistributionOfBuildingExpensesSettlementRepository;
import com.relocation.test.repository.PeopleInfoRepository;
import com.relocation.test.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class PledgeController {
    @Autowired
    PeopleInfoRepository peopleInfoRepository;

    @Autowired
    DistributionOfBuildingExpensesSettlementRepository settlementRepository;

    @RequestMapping("/pledge/totalNums")
    public JSONObject totalNums() {
        System.out.println("pledge total numbers");
        List<DistributionOfBuildingExpensesSettlement> pledges = settlementRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        data.put("totalNums", pledges.size());
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/pledge/list")
    public JSONObject list(@RequestParam(value = "offset") int offset, @RequestParam(value = "nums") int nums) {
        System.out.println("pledge list");
        List<DistributionOfBuildingExpensesSettlement> pledgeList = settlementRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        int dest = Math.min((offset + nums), pledgeList.size());
        if (offset < pledgeList.size()) {
            JSONObject pledges = new JSONObject();
            for (int i = offset; i < dest; i++) {
                JSONObject enterprise = new JSONObject();
                JsonUtil.loadPledgeInfo(enterprise, pledgeList.get(i));
                pledges.put((i - offset) + "", enterprise);
            }
            data.put("pledges", pledges);
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

    @RequestMapping("/pledge/query/head")
    JSONObject queryRelocationByName(@RequestParam(value = "head") String name) {
        System.out.println("query relocation by name ");
        List<RelocationPeopleInfo> infos = peopleInfoRepository.findRelocationPeopleInfosByOwner(name);
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getSettlement() != null) {
                JSONObject relocation = new JSONObject();
                JsonUtil.loadPledgeInfo(relocation, infos.get(i).getSettlement());
                data.put(i + "", relocation);
            }
        }
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping("/pledge/query/idcard")
    JSONObject queryRelocationByIdCard(@RequestParam(value = "idcard") String idCard) {
        System.out.println("query relocation by idcard");
        RelocationPeopleInfo info = peopleInfoRepository.findRelocationPeopleInfoByIdCard(idCard);
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (info != null) {
            JSONObject relocation = new JSONObject();
            if (info.getSettlement() != null) {
                JsonUtil.loadPledgeInfo(relocation, info.getSettlement());
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

    @RequestMapping(value = "/pledge/pay")
    public JSONObject payPledge(@RequestParam(value = "id") int id) {
        System.out.println("pledge pay");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        DistributionOfBuildingExpensesSettlement settlement = settlementRepository.findDistributionOfBuildingExpensesSettlementById(id);
        if (settlement.getSettlementDate() == null){
            settlement.setPledge(true);
            settlementRepository.save(settlement);
            meta.put("msg", "缴纳押金成功");
            meta.put("status", 202);
        }
        else {
            meta.put("msg", "已经返还押金了");
            meta.put("status", 303);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/pledge/refund")
    public JSONObject refundPledge(@RequestParam(value = "id") int id) {
        System.out.println("pledge pay");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        DistributionOfBuildingExpensesSettlement settlement = settlementRepository.findDistributionOfBuildingExpensesSettlementById(id);
        if (settlement.isPledge()){
            settlement.setCreditReview(true);
            settlement.setSettlementDate(new Date(new java.util.Date().getTime()));
            settlementRepository.save(settlement);
            meta.put("msg", "返还押金成功");
            meta.put("status", 202);
        }
        else{
            meta.put("msg", "尚未缴纳押金");
            meta.put("status", 303);
        }
        res.put("meta", meta);
        return res;
    }
}
