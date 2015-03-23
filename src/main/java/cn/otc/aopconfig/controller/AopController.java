package cn.otc.aopconfig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.otc.aopconfig.model.Bean;
import cn.otc.aopconfig.model.PointType;
import cn.otc.aopconfig.service.AopDocumentService;
import cn.otc.aopconfig.service.AopService;

/**
 * Created by Hao Chen on 2015/3/19.
 */
@Controller
public class AopController {

    @Autowired
    AopService aopService;
    
    @Autowired
    AopDocumentService adService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
	model.addAttribute("notices", aopService.getNotices());
	return "list";
    }
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addAop(Model model) {
	List<Bean> beans = aopService.getBeans();
	model.addAttribute("beans", beans);
	return "add-aop";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String doAddAop(@RequestParam String pointcut,
	    @RequestParam Long beanId, @RequestParam String method,
	    @RequestParam String pointType) {
	aopService.createNotice(pointcut, beanId, method,
		PointType.of(pointType));
	return "redirect:/";
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteAop(Long id){
	aopService.deleteNotice(id);
	return "redirect:/";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(Long id, boolean state){
	aopService.update(id, state);
	return "redirect:/";
    }
    
    @RequestMapping(value = "generate", method = RequestMethod.GET)
    @ResponseBody
    public String generate(){
	String result = adService.generateSpringAopDocument();
	return result;
    }
}
