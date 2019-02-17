package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/go")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userService.loginUser(user);
        if (user1 == null) {
            model.addAttribute("msg", "用户名或密码不正确");
            return "/login";
        } else {
            return "redirect:/go/findAll";
        }

    }

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("userList", list);
        return "list";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        int int_id = Integer.parseInt(id);
        userService.delete(int_id);
        return "redirect:/go/findAll";

    }
//回显数据
    @RequestMapping("/findById")
    public String findById(String id, HttpSession session) {
        int int_id = Integer.parseInt(id);
        User user = userService.findById(int_id);
        session.setAttribute("user", user);
        return "update";
    }

    @RequestMapping("/updateUser")
    public String update(String name,String sex,String age,String address,HttpSession session){
        int int_age = Integer.parseInt(age);
        User user = (User)session.getAttribute("user");
        user.setName(name);
        user.setSex(sex);
        user.setAge(int_age);
        user.setAddress(address);
        userService.update(user);
        return "redirect:/go/findAll";
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }


    @RequestMapping("/save")
    public String save(String name,String sex,String age,String address){
        int int_age = Integer.parseInt(age);
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setAge(int_age);
        user.setAddress(address);
        userService.save(user);
        return "redirect:/go/findAll";
}
}
