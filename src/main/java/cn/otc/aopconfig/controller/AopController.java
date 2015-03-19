package cn.otc.aopconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Hao Chen on 2015/3/19.
 */
@Controller
public class AopController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addAop(Model model){
        model.addAttribute("hehe", "hehehe");
        return "add-aop";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String doAddAop(@RequestParam String hehe){
        System.out.println(hehe);
        return "list";
    }

}
