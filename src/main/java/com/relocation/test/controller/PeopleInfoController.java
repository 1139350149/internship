package com.relocation.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.relocation.test.annotation.Token;
import com.relocation.test.entity.RelocationPeopleInfo;
import com.relocation.test.repository.PeopleInfoRepository;
import com.relocation.test.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class PeopleInfoController {

    @Autowired
    PeopleInfoRepository peopleInfoRepository;

    @Token
    @RequestMapping(value = "/people/totalNums", method = RequestMethod.GET)
    JSONObject totalNums() {
        System.out.println("people total numbers");
        List<RelocationPeopleInfo> relocationPeopleInfos = peopleInfoRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        data.put("totalNums", relocationPeopleInfos.size());
        JSONObject meta = new JSONObject();
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @Token
    @RequestMapping(value = "/people/list", method = RequestMethod.GET)
    JSONObject list(@RequestParam(value = "offset") int offset, @RequestParam(value = "nums") int nums) {
        System.out.println("list people");
        List<RelocationPeopleInfo> relocationPeopleInfos = peopleInfoRepository.findAllByOrderByIdAsc();
        int dest = Math.min((offset + nums), relocationPeopleInfos.size());
        JSONObject res = JsonUtil.getPeopleInfoJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (offset < relocationPeopleInfos.size()) {
            JSONObject peoples = new JSONObject();
            for (int i = offset; i < dest; i++) {
                JSONObject people = new JSONObject();
                JsonUtil.loadPeopleInfo(people, relocationPeopleInfos.get(i));
                peoples.put((i - offset) + "", people);
            }
            data.put("peoples", peoples);
            meta.put("msg", "获取成功");
            meta.put("status", 200);
            res.put("data", data);
            res.put("meta", meta);

        } else {
            meta.put("msg", "获取失败");
            meta.put("status", 301);
            res.put("meta", meta);
        }
        return res;
    }

    @Token
    @RequestMapping(value = "/people/query/name", method = RequestMethod.GET)
    JSONObject queryPeopleByName(@RequestParam(value = "name") String name) {
        System.out.println("people query by name");
        JSONObject res = JsonUtil.getPeopleInfoJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (peopleInfoRepository.existsRelocationPeopleInfoByOwner(name)) {
            List<RelocationPeopleInfo> peopleInfos = peopleInfoRepository.findRelocationPeopleInfosByOwner(name);
            JSONObject peoples = new JSONObject();
            for (int i = 0; i < peopleInfos.size(); i++) {
                JSONObject people = new JSONObject();
                JsonUtil.loadPeopleInfo(people, peopleInfos.get(i));
                peoples.put(i + "", people);
            }
            data.put("peoples", peoples);
            meta.put("msg", "查询成功");
            meta.put("status", 200);
        } else {
            meta.put("msg", "此人不存在");
            meta.put("status", 302);
        }
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @Token
    @RequestMapping(value = "/people/query/idcard", method = RequestMethod.GET)
    JSONObject queryPeopleByIdCard(@RequestParam(value = "idcard") String idCard) {
        System.out.println("people query by id card");
        JSONObject res = JsonUtil.getPeopleInfoJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (peopleInfoRepository.existsRelocationPeopleInfoByIdCard(idCard)) {
            RelocationPeopleInfo peopleInfo = peopleInfoRepository.findRelocationPeopleInfoByIdCard(idCard);
            JSONObject peoples = new JSONObject();
            JSONObject people = new JSONObject();
            JsonUtil.loadPeopleInfo(people, peopleInfo);
            peoples.put(0 + "", people);
            data.put("peoples", peoples);
            meta.put("msg", "查询成功");
            meta.put("status", 200);
        } else {
            meta.put("msg", "此人不存在");
            meta.put("status", 302);
        }
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @Token
    @RequestMapping(value = "/people/add", method = RequestMethod.POST)
    JSONObject addPeople(@RequestParam(value = "name") String owner,
                         @RequestParam(value = "idcard") String idCard,
                         @RequestParam(value = "address") String address,
                         @RequestParam(value = "regdate") String regDate,
                         @RequestParam(value = "remark") String remark,
                         @RequestParam(value = "prin") String principal,
                         @RequestParam(value = "resp") String responsiblePeople,
                         @RequestParam(value = "filldate") String fillDate) {
        System.out.println("people add");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        if (peopleInfoRepository.existsRelocationPeopleInfoByIdCard(idCard)){
            meta.put("msg", "创建失败，身份证号码已存在");
            meta.put("status", 301);
        }
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date regDateParse = null;
            Date fillDateParse = null;
            try {
                regDateParse = new Date(simpleDateFormat.parse(regDate).getTime());
                fillDateParse = new Date(simpleDateFormat.parse(fillDate).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            RelocationPeopleInfo peopleInfo = new RelocationPeopleInfo(idCard, owner, address, regDateParse, remark, owner,
                    principal, responsiblePeople, fillDateParse);
            peopleInfoRepository.save(peopleInfo);

            meta.put("msg", "创建成功");
            meta.put("status", "201");
        }
        res.put("meta", meta);
        return res;
    }

    @Token
    @RequestMapping(value = "/people/delete", method = RequestMethod.POST)
    JSONObject deletePeopleInfo(@RequestParam(value = "id") int id) {
        System.out.println("people delete");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        if (peopleInfoRepository.existsRelocationPeopleInfoById(id)) {
            int consequence = peopleInfoRepository.deleteRelocationPeopleInfoById(id);
            meta.put("msg", consequence != 0 ? "删除成功" : "删除失败");
            meta.put("status", consequence != 0 ? 204 : 301);
        } else {
            meta.put("msg", "目标身份证号不存在");
            meta.put("status", 302);
        }
        res.put("meta", meta);
        return res;
    }

    @Token
    @RequestMapping(value = "/people/update", method = RequestMethod.POST)
    JSONObject updatePeopleInfo(@RequestParam(value = "id") int id,
                                @RequestParam(value = "name") String owner,
                                @RequestParam(value = "idcard") String idCard,
                                @RequestParam(value = "address") String address,
                                @RequestParam(value = "regdate") String regDate,
                                @RequestParam(value = "remark") String remark,
                                @RequestParam(value = "prin") String principal,
                                @RequestParam(value = "resp") String responsiblePeople,
                                @RequestParam(value = "filldate") String fillDate) {
        System.out.println("people update");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        RelocationPeopleInfo info = peopleInfoRepository.findRelocationPeopleInfoByIdCardAndIdNot(idCard, id);
        if (info != null){
            System.out.println(info.getId());
        }
        if (peopleInfoRepository.existsRelocationPeopleInfoByIdCardAndIdNot(idCard, id)){
            meta.put("msg", "修改失败，身份证号码已存在");
            meta.put("status", 301);
        }
        else {
            RelocationPeopleInfo peopleInfo = peopleInfoRepository.findRelocationPeopleInfoById(id);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date regDateParse = null;
            Date fillDateParse = null;
            try {
                regDateParse = new Date(simpleDateFormat.parse(regDate).getTime());
                fillDateParse = new Date(simpleDateFormat.parse(fillDate).getTime());
                if (regDateParse == null || fillDateParse == null){
                    meta.put("msg", "修改失败，错误格式的填写日期或注册日期");
                    meta.put("status", 302);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            peopleInfo.setOwner(owner);
            peopleInfo.setIdCard(idCard);
            peopleInfo.setAddress(address);
            peopleInfo.setRegisterDate(regDateParse);
            peopleInfo.setRemark(remark);
            peopleInfo.setPrincipal(principal);
            peopleInfo.setResponsiblePeople(responsiblePeople);
            peopleInfo.setFillDate(fillDateParse);
            peopleInfo.setSignature(owner);
            peopleInfoRepository.save(peopleInfo);
            meta.put("msg", "修改成功");
            meta.put("status", 202);
        }
        res.put("meta", meta);
        return res;
    }
}
