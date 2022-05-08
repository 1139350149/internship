package com.relocation.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.relocation.test.annotation.Token;
import com.relocation.test.entity.RelocationPeopleDetailInfo;
import com.relocation.test.entity.RelocationPeopleInfo;
import com.relocation.test.repository.PeopleDetailInfoRepository;
import com.relocation.test.repository.PeopleInfoRepository;
import com.relocation.test.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PeopleDetailInfoController {
    @Autowired
    PeopleInfoRepository peopleInfoRepository;

    @Autowired
    PeopleDetailInfoRepository peopleDetailInfoRepository;

    @Token
    @RequestMapping(value = "/family/totalNums", method = RequestMethod.GET)
    JSONObject totalNums() {
        System.out.println("family total numbers");
        List<RelocationPeopleDetailInfo> detailInfos = peopleDetailInfoRepository.findAllByOrderByIdAsc();
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        data.put("totalNums", detailInfos.size());
        JSONObject meta = new JSONObject();
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }

    @Token
    @RequestMapping(value = "family/list", method = RequestMethod.GET)
    JSONObject list(@RequestParam(value = "offset") int offset, @RequestParam(value = "nums") int nums) {
        System.out.println("list family ");
        List<RelocationPeopleDetailInfo> detailInfos = peopleDetailInfoRepository.findAllByOrderByIdAsc();
        int dest = Math.min((offset + nums), detailInfos.size());
        JSONObject res = JsonUtil.getPeopleInfoJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (offset < detailInfos.size()) {
            JSONObject families = new JSONObject();
            for (int i = offset; i < dest; i++) {
                JSONObject family = new JSONObject();
                JsonUtil.loadPeopleDetailInfo(family, detailInfos.get(i));
                families.put((i - offset) + "", family);
            }
            data.put("families", families);
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

    @Token
    @RequestMapping(value = "/family/query", method = RequestMethod.GET)
    JSONObject queryFamily(@RequestParam(value = "id") int id) {
        System.out.println("family query by id");
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        List<RelocationPeopleDetailInfo> peopleDetailInfos =
                peopleDetailInfoRepository.findRelocationPeopleDetailInfosByRelocationPeopleInfo_Id(id);
        for (int i = 0; i < peopleDetailInfos.size(); i++) {
            JSONObject temp = new JSONObject();
            JsonUtil.loadPeopleDetailInfo(temp, peopleDetailInfos.get(i));
            data.put(i + "", temp);
        }
        res.put("data", data);
        meta.put("msg", "获取成功");
        meta.put("status", 200);
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/family/query/idcard", method = RequestMethod.GET)
    JSONObject queryFamilyByIdCard(@RequestParam(value = "idcard") String idCard) {
        System.out.println("family query by id");
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (!peopleDetailInfoRepository.existsRelocationPeopleDetailInfoByIdCard(idCard)) {
            meta.put("msg", "身份证号码不存在");
            meta.put("status", 302);
        } else {
            RelocationPeopleDetailInfo info = peopleDetailInfoRepository.findRelocationPeopleDetailInfoByIdCard(idCard);
            JSONObject temp = new JSONObject();
            JsonUtil.loadPeopleDetailInfo(temp, info);
            data.put("0", temp);
            res.put("data", data);
            meta.put("msg", "查找成功");
            meta.put("status", 202);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/family/query/name", method = RequestMethod.GET)
    JSONObject queryFamilyByName(@RequestParam(value = "name") String name) {
        System.out.println("family query by name");
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        if (!peopleDetailInfoRepository.existsRelocationPeopleDetailInfoByName(name)) {
            meta.put("msg", "家属名不存在");
            meta.put("status", 302);
        } else {
            List<RelocationPeopleDetailInfo> infos = peopleDetailInfoRepository.findRelocationPeopleDetailInfosByName(name);
            for (int i = 0; i < infos.size(); i++) {
                JSONObject temp = new JSONObject();
                JsonUtil.loadPeopleDetailInfo(temp, infos.get(i));
                data.put(i + "", temp);
            }
            res.put("data", data);
            meta.put("msg", "查找成功");
            meta.put("status", 202);
        }
        res.put("meta", meta);
        return res;
    }


    @RequestMapping(value = "/family/query/head", method = RequestMethod.GET)
    JSONObject queryFamilyByHead(@RequestParam(value = "head") String headName) {
        System.out.println("family query by head");
        int sizes = 0;
        JSONObject res = JsonUtil.getJson();
        JSONObject data = new JSONObject();
        JSONObject meta = new JSONObject();
        List<RelocationPeopleInfo> heads = peopleInfoRepository.findRelocationPeopleInfosByOwner(headName);
        if (heads.size() > 0) {
            for (RelocationPeopleInfo head : heads) {
                List<RelocationPeopleDetailInfo> families =
                        peopleDetailInfoRepository.findRelocationPeopleDetailInfosByRelocationPeopleInfo_IdOrderById(head.getId());
                for (int i = 0; i < families.size(); i++) {
                    JSONObject temp = new JSONObject();
                    JsonUtil.loadPeopleDetailInfo(temp, families.get(i));
                    data.put(i + sizes + "", temp);
                }
                sizes = families.size();
            }
            res.put("data", data);
            meta.put("msg", "获取成功");
            meta.put("status", 200);
        } else {
            meta.put("msg", "户主不存在");
            meta.put("status", 302);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/family/add", method = RequestMethod.POST)
    JSONObject addFamily(@RequestParam(value = "name") String name,
                         @RequestParam(value = "idcard") String idCard,
                         @RequestParam(value = "birth") String birth,
                         @RequestParam(value = "relation") String relation,
                         @RequestParam(value = "remark") String remark,
                         @RequestParam(value = "headid") int headId) {
        System.out.println("family add");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthParse = null;
        try {
            birthParse = new Date(format.parse(birth).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!peopleInfoRepository.existsRelocationPeopleInfoById(headId)) {
            meta.put("msg", "户主档案不存在");
            meta.put("status", 302);
        } else if (peopleDetailInfoRepository.existsRelocationPeopleDetailInfoByIdCard(idCard)) {
            meta.put("msg", "添加失败，身份证号码已存在");
            meta.put("status", 301);
        } else {
            RelocationPeopleInfo peopleInfo = peopleInfoRepository.findRelocationPeopleInfoById(headId);
            RelocationPeopleDetailInfo detailInfo = new RelocationPeopleDetailInfo(name, idCard, birthParse,
                    relation, remark, peopleInfo);
            peopleDetailInfoRepository.save(detailInfo);
            meta.put("msg", "创建成功");
            meta.put("status", 202);
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/family/update", method = RequestMethod.POST)
    JSONObject updateFamily(@RequestParam(value = "id") int id,
                            @RequestParam(value = "name") String name,
                            @RequestParam(value = "idcard") String idCard,
                            @RequestParam(value = "birth") String birth,
                            @RequestParam(value = "relation") String relation,
                            @RequestParam(value = "remark") String remark,
                            @RequestParam(value = "headid") int headId) {
        System.out.println("family update");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        if (!peopleInfoRepository.existsRelocationPeopleInfoById(headId)) {
            meta.put("msg", "户主档案不存在");
            meta.put("status", 302);
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthParse = null;
            try {
                birthParse = new Date(format.parse(birth).getTime());

            } catch (ParseException e) {
                e.printStackTrace();
                meta.put("msg", "修改失败，日期格式错误");
                meta.put("status", 303);
            }
            if (peopleDetailInfoRepository.existsRelocationPeopleDetailInfoByIdCardAndIdNot(idCard, id)) {
                meta.put("msg", "修改失败，身份证号码已存在");
                meta.put("status", 301);
            } else if (!peopleInfoRepository.existsRelocationPeopleInfoById(headId)) {
                meta.put("msg", "户主档案不存在");
                meta.put("status", 302);
            } else {
                RelocationPeopleInfo peopleInfo = peopleInfoRepository.findRelocationPeopleInfoById(headId);
                RelocationPeopleDetailInfo detailInfo = peopleDetailInfoRepository.findRelocationPeopleDetailInfosById(id);
                detailInfo.setName(name);
                detailInfo.setIdCard(idCard);
                detailInfo.setBirth(birthParse);
                detailInfo.setRelation(relation);
                detailInfo.setRemark(remark);
                detailInfo.setRelocationPeopleInfo(peopleInfo);
                peopleDetailInfoRepository.save(detailInfo);
                meta.put("msg", "修改成功");
                meta.put("status", 202);
            }
        }
        res.put("meta", meta);
        return res;
    }

    @RequestMapping(value = "/family/delete", method = RequestMethod.POST)
    JSONObject deleteFamily(@RequestParam(value = "id") int id) {
        System.out.println("family delete");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = new JSONObject();
        if (peopleDetailInfoRepository.existsRelocationPeopleDetailInfoById(id)) {
            int consequence = peopleDetailInfoRepository.deleteRelocationPeopleDetailInfoById(id);
            meta.put("msg", consequence != 0 ? "删除成功" : "删除失败");
            meta.put("status", consequence != 0 ? 204 : 301);
        } else {
            meta.put("msg", "家属编号不存在");
            meta.put("status", 302);
        }
        res.put("meta", meta);
        return res;
    }
}
