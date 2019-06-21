package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.UserInfoService;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    //return the userInfos displayed on current page
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public JSONObject findAllUser() {
        List<User> users = userInfoService.findAll();
        JSONObject result = new JSONObject();
        result.put("users", users);
        return result;
    }

    //return number of all userInfos
    @RequestMapping(value = "/totalNum", method = RequestMethod.GET)
    public JSONObject getTotalNum() {
        long total = userInfoService.count();
        JSONObject result = new JSONObject();
        result.put("totalNum", total);
        return result;
    }

    //return the user detail infos
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public JSONObject showDetail(@RequestParam(name = "id") String u_id) {
        User user = userInfoService.queryById(u_id);
        JSONObject result = new JSONObject();
        result.put("user", user);
        return result;
    }

    //set or lift the ban of user
    @RequestMapping(value = "/toggle", method = RequestMethod.PUT)
    public JSONObject toggleStatus(@RequestBody JSONObject param) {
        String phone = param.getString("phone");
        User user = userInfoService.queryByPhone(phone);
        System.out.println(user.getU_id());
        int i = ((user.getStatus() == 0) ? -1 : 0);   // toggle status
        user.setStatus(i);
        userInfoService.update(user);
        JSONObject result = new JSONObject();
        result.put("status", i);
        return result;
    }

    //update userInfo
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public JSONObject update(@RequestBody JSONObject param) {
        String username = param.getString("username");
        String password = param.getString("password");
        Integer state = param.getInteger("status");
        String phone = param.getString("phone");
        String email = param.getString("email");
        User user = userInfoService.queryByPhone(phone);
        user.setStatus(state);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        userInfoService.update(user);
        JSONObject result = new JSONObject();
        result.put("user", user);
        return result;
    }

}