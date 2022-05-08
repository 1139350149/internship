package com.relocation.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.relocation.test.entity.*;

/**
 * @Description: 返回json格式
 */
public class JsonUtil {

    public static JSONObject getJson() {
        JSONObject json = new JSONObject();
        json.put("data", null);
        json.put("meta", null);
        return json;
    }

    public static JSONObject getPeopleInfoJson() {
        JSONObject json = new JSONObject();
        JSONObject peoples = new JSONObject();
        peoples.put("peoples", null);
        json.put("data", peoples);
        json.put("meta", null);
        return json;
    }

    public static void loadPeopleInfo(JSONObject tar, RelocationPeopleInfo info) {
        tar.put("id", info.getId());
        tar.put("name", info.getOwner());
        tar.put("idcard", info.getIdCard());
        tar.put("address", info.getAddress());
        tar.put("regdate", info.getRegisterDate());
        tar.put("remark", info.getRemark());
        tar.put("principal", info.getPrincipal());
        tar.put("responsiblePeople", info.getResponsiblePeople());
        tar.put("filldate", info.getFillDate());
    }

    public static void loadPeopleDetailInfo(JSONObject tar, RelocationPeopleDetailInfo info) {
        tar.put("headid", info.getRelocationPeopleInfo().getId());
        tar.put("head", info.getRelocationPeopleInfo().getOwner());
        tar.put("name", info.getName());
        tar.put("relation", info.getRelation());
        tar.put("birth", info.getBirth());
        tar.put("remark", info.getRemark());
        tar.put("idcard", info.getIdCard());
        tar.put("id", info.getId());
    }

    public static void loadRelocationSettlement(JSONObject tar, RelocationPeopleInfo info) {
        tar.put("headid", info.getId());
        tar.put("name", info.getOwner());
        tar.put("idcard", info.getIdCard());

        tar.put("sid", info.getSettlement().getId());
        tar.put("address", info.getSettlement().getOriginalAddress());
        tar.put("oarea", info.getSettlement().getOriginalBuildingArea());
        tar.put("ovalue", info.getSettlement().getOriginalBuildingValue());
        tar.put("ofvalue", info.getSettlement().getOriginalBuildingFacilitiesValue());
        tar.put("darea", info.getSettlement().getDistributedBuildingAllocatedTotalArea());
        tar.put("dvalue", info.getSettlement().getDistributedBuildingAllocatedTotalValue());
        tar.put("samount", info.getSettlement().getDistributedBuildingAllocatedAndOriginalBuildingSettlementAmount());
        tar.put("sremark", info.getSettlement().getRemark());
        tar.put("officer", info.getSettlement().getOfficer());
        tar.put("settlement", info.getSettlement().getSettlement());
        tar.put("cview", info.getSettlement().isCreditReview()?"已结算":"未结算");
        tar.put("sdate", info.getSettlement().getSettlementDate());
        tar.put("sfdate", info.getSettlement().getFillDate());
        tar.put("sfname", info.getSettlement().getFillName());
        tar.put("ppercentage", info.getSettlement().getPledgePercentage());
        if (info.getCompensation() != null) {
            tar.put("cid", info.getCompensation().getId());
            tar.put("mdate", info.getCompensation().getMeasureDate());
            tar.put("oproject", info.getCompensation().getOccupationProject());
            tar.put("wcount", info.getCompensation().getWellCount());
            tar.put("wvalue", info.getCompensation().getWellValue());
            tar.put("waarea", info.getCompensation().getWallArea());
            tar.put("wavalue", info.getCompensation().getWallValue());
            tar.put("carea", info.getCompensation().getCementArea());
            tar.put("cvalue", info.getCompensation().getCementValue());
            tar.put("cearea", info.getCompensation().getCellarVolume());
            tar.put("cevalue", info.getCompensation().getCellarValue());
            tar.put("cfdate", info.getCompensation().getFillDate());
            tar.put("cfname", info.getCompensation().getFillName());
            tar.put("cremark", info.getCompensation().getRemark());
        } else {
            tar.put("cid", null);
            tar.put("mdate", null);
            tar.put("oproject", null);
            tar.put("ftvalue", null);
            tar.put("wcount", null);
            tar.put("wvalue", null);
            tar.put("waarea", null);
            tar.put("wavalue", null);
            tar.put("carea", null);
            tar.put("cvalue", null);
            tar.put("cearea", null);
            tar.put("cevalue", null);
            tar.put("cfdate", null);
            tar.put("cfname", null);
            tar.put("cremark", null);
        }
    }

    public static void loadEnterpriseInfo(JSONObject tar, EnterpriseTurnoverMeasurement measurement) {
        tar.put("id", measurement.getId());
        tar.put("mdate", measurement.getMeasureDate());
        tar.put("ename", measurement.getEnterpriseName());
        tar.put("address", measurement.getAddress());
        tar.put("oproject", measurement.getOccupiedProject());
        tar.put("recorder", measurement.getRecorder());
        tar.put("surveyor", measurement.getSurveyor());
        tar.put("well", measurement.getWellCount());
        tar.put("wall", measurement.getWallArea());
        tar.put("cement", measurement.getCementArea());
        tar.put("cellar", measurement.getCellarVolume());
        tar.put("rsign", measurement.getResettleSign());
        tar.put("oarea", measurement.getTotalOfficeArea());
        tar.put("farea", measurement.getTotalFactoryArea());
        tar.put("crarea", measurement.getTotalCementRoadArea());
        tar.put("fearea", measurement.getTotalFenceArea());
    }

    public static void loadPledgeInfo(JSONObject tar, DistributionOfBuildingExpensesSettlement settlement) {
        tar.put("id", settlement.getId());
        tar.put("headid", settlement.getRelocationPeopleInfo().getId());
        tar.put("head", settlement.getRelocationPeopleInfo().getOwner());
        tar.put("idcard", settlement.getRelocationPeopleInfo().getIdCard());
        tar.put("ispledge", settlement.isPledge());
        tar.put("ppercentage", settlement.getPledgePercentage());
        tar.put("pamount", (double) Math.round(settlement.getPledgePercentage() *
                Math.abs(settlement.getSettlement() * 100)) / 100);
        tar.put("samount", settlement.getSettlement());
        tar.put("cview", settlement.isCreditReview());
    }
}

