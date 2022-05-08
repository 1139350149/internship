package com.relocation.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.relocation.test.entity.EnterpriseTurnoverMeasurement;
import com.relocation.test.repository.EnterpriseRepository;
import com.relocation.test.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class EnterpriseController {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    @RequestMapping(value = "/enterprise/totalNums")
    public JSONObject totalNums() {
        System.out.println("enterprise total numbers");
        List<EnterpriseTurnoverMeasurement> enterprises = enterpriseRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        data.put("totalNums", enterprises.size());
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/enterprise/list")
    public JSONObject list(@RequestParam(value = "offset") int offset, @RequestParam(value = "nums") int nums) {
        System.out.println("enterprise list");
        List<EnterpriseTurnoverMeasurement> enterpriseList = enterpriseRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        int dest = Math.min((offset + nums), enterpriseList.size());
        if (offset < enterpriseList.size()) {
            JSONObject enterprises = new JSONObject();
            for (int i = offset; i < dest; i++) {
                JSONObject enterprise = new JSONObject();
                JsonUtil.loadEnterpriseInfo(enterprise, enterpriseList.get(i));
                enterprises.put((i - offset) + "", enterprise);
            }
            data.put("enterprises", enterprises);
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

    @RequestMapping(value = "/enterprise/query/name", method = RequestMethod.GET)
    JSONObject queryEnterpriseByName(@RequestParam(value = "name") String name) {
        System.out.println("enterprise query by name");
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        List<EnterpriseTurnoverMeasurement> measurementList = enterpriseRepository.findEnterpriseTurnoverMeasurementsByEnterpriseNameContains(name);
        for (int i = 0; i < measurementList.size(); i++) {
            JSONObject temp = new JSONObject();
            JsonUtil.loadEnterpriseInfo(temp, measurementList.get(i));
            data.put(i + "", temp);
        }
        res.put("data", data);
        meta.put("msg", "查找成功");
        meta.put("status", 202);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/enterprise/query/project", method = RequestMethod.GET)
    JSONObject queryFamilyByName(@RequestParam(value = "project") String project) {
        System.out.println("enterprise query by project");
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        List<EnterpriseTurnoverMeasurement> measurementList = enterpriseRepository.findEnterpriseTurnoverMeasurementsByOccupiedProjectContains(project);
        for (int i = 0; i < measurementList.size(); i++) {
            JSONObject temp = new JSONObject();
            JsonUtil.loadEnterpriseInfo(temp, measurementList.get(i));
            data.put(i + "", temp);
        }
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/enterprise/add", method = RequestMethod.POST)
    JSONObject add(@RequestParam(value = "mdate") String measureDate,
                         @RequestParam(value = "ename") String enterpriseName,
                         @RequestParam(value = "address") String address,
                         @RequestParam(value = "oproject") String oProject,
                         @RequestParam(value = "recorder") String recorder,
                         @RequestParam(value = "surveyor") String surveyor,
                         @RequestParam(value = "well") int wellCount,
                         @RequestParam(value = "wall") double wallArea,
                         @RequestParam(value = "cement") double cementArea,
                         @RequestParam(value = "cellar") double cellarVolume,
                         @RequestParam(value = "rsign") String resettleSign,
                         @RequestParam(value = "oarea") double totalOfficeArea,
                         @RequestParam(value = "farea") double totalFactoryArea,
                         @RequestParam(value = "crarea") double totalCementRoadArea,
                         @RequestParam(value = "fearea") double totalFenceArea) {
        System.out.println("enterprise add");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date measureDateParse = null;
        try {
            measureDateParse = new Date(format.parse(measureDate).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            meta.put("msg", "日期格式错误");
            meta.put("status", "301");
        }
        if (enterpriseRepository.existsEnterpriseTurnoverMeasurementByOccupiedProject(oProject)) {
            meta.put("msg", "该项目已被登记");
            meta.put("status", 302);
        } else {
            EnterpriseTurnoverMeasurement measurement = new EnterpriseTurnoverMeasurement(measureDateParse
                    , enterpriseName, address, oProject, recorder, surveyor, wellCount, wallArea, cementArea
                    , cellarVolume, resettleSign, totalOfficeArea, totalFactoryArea, totalCementRoadArea, totalFenceArea);
            enterpriseRepository.save(measurement);
            meta.put("msg", "创建成功");
            meta.put("status", 202);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/enterprise/delete", method = RequestMethod.POST)
    public JSONObject deleteEnterprise(@RequestParam(value = "id") int id) {
        System.out.println("enterprise delete");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        if (enterpriseRepository.existsEnterpriseTurnoverMeasurementById(id)) {
            int consequence = enterpriseRepository.deleteEnterpriseTurnoverMeasurementById(id);
            meta.put("msg", consequence != 0 ? "删除成功" : "删除失败");
            meta.put("status", consequence != 0 ? 204 : 301);
        } else {
            meta.put("msg", "企业动迁信息编号不存在");
            meta.put("status", 302);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/enterprise/update", method = RequestMethod.POST)
    public JSONObject updateEnterprise(@RequestParam(value = "id") int id,
                                       @RequestParam(value = "mdate") String measureDate,
                                       @RequestParam(value = "ename") String enterpriseName,
                                       @RequestParam(value = "address") String address,
                                       @RequestParam(value = "oproject") String oProject,
                                       @RequestParam(value = "recorder") String recorder,
                                       @RequestParam(value = "surveyor") String surveyor,
                                       @RequestParam(value = "well") int wellCount,
                                       @RequestParam(value = "wall") double wallArea,
                                       @RequestParam(value = "cement") double cementArea,
                                       @RequestParam(value = "cellar") double cellarVolume,
                                       @RequestParam(value = "rsign") String resettleSign,
                                       @RequestParam(value = "oarea") double totalOfficeArea,
                                       @RequestParam(value = "farea") double totalFactoryArea,
                                       @RequestParam(value = "crarea") double totalCementRoadArea,
                                       @RequestParam(value = "fearea") double totalFenceArea) {
        System.out.println("enterprise update");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date measureDateParse = null;
        try {
            measureDateParse = new Date(format.parse(measureDate).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            meta.put("msg", "修改失败，日期格式错误");
            meta.put("status", 303);
        }
        if (enterpriseRepository.existsEnterpriseTurnoverMeasurementByOccupiedProjectAndIdNot(oProject, id)) {
            meta.put("msg", "修改失败，征占项目已被登记");
            meta.put("status", 301);
        } else {
            EnterpriseTurnoverMeasurement measurement = enterpriseRepository.findEnterpriseTurnoverMeasurementById(id);
            measurement.update(measureDateParse, enterpriseName, address, oProject, recorder, surveyor, wellCount,
                    wallArea, cementArea, cellarVolume, resettleSign, totalOfficeArea, totalFactoryArea, totalCementRoadArea, totalFenceArea);
            enterpriseRepository.save(measurement);
            meta.put("msg", "修改成功");
            meta.put("status", 202);
        }

        res.put("meta", meta);
        return res;
    }
}
