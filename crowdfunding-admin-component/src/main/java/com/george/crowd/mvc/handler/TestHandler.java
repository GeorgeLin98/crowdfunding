package com.george.crowd.mvc.handler;

import com.george.crowd.entity.Admin;
import com.george.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author georgeLin
 * @date 2021-04-13-1:36
 */
@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("test/ssm.html")
    public String testSsm(Model model){
        List<Admin> list = adminService.getAll();
        model.addAttribute("adminList", list);
        return "target";
    }
}
