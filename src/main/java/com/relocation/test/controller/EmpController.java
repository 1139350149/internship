package com.relocation.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.relocation.test.entity.Employee;
import com.relocation.test.repository.EmployeeRepository;
import com.relocation.test.util.JsonUtil;
import com.relocation.test.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmpController {
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    JSONObject login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        System.out.println("login");
        JSONObject res = JsonUtil.getJson();
        JSONObject meta = null;
        JSONObject data = null;
        Employee emp = null;
        emp = employeeRepository.findEmployeeByName(username);
        if (emp != null){
            if (emp.getPwd().equals(password)) {
                String token = TokenUtil.sign(username, password);
                data = new JSONObject();
                meta = new JSONObject();
                data.put("id", emp.getId());
                data.put("name", emp.getName());
                data.put("num", emp.getPwd());
                data.put("email", emp.getEmail());
                data.put("gender", emp.getGender());
                data.put("phone", emp.getPhone());
                data.put("remark", emp.getRemark());
                data.put("token", token);

                meta.put("msg", "登录成功");
                meta.put("code", "200");
            }
            else{
                meta = new JSONObject();
                meta.put("msg", "密码错误");
                meta.put("code", "301");
            }
        }
        else{
            meta = new JSONObject();
            meta.put("msg", "用户名不存在");
            meta.put("code", "302");
        }
        res.put("data", data);
        res.put("meta", meta);
        return res;
    }
}
