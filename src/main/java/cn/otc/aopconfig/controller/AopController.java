package cn.otc.aopconfig.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import cn.otc.aopconfig.model.Bean;
import cn.otc.aopconfig.model.PointType;
import cn.otc.aopconfig.service.AopDocumentService;
import cn.otc.aopconfig.service.AopService;

@Controller
public class AopController {
    
    	String baseDir = "E:/Workspaces/IdeaProjects/aop-config/src/main/java";
    	
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
	public String doAddAop(@RequestParam List<String> pointcut,
			@RequestParam Long beanId, @RequestParam String method,
			@RequestParam String pointType) {
	    	for(String p: pointcut){
	    	    aopService.createNotice(p, beanId, method,
    				PointType.of(pointType));
	    	}
		return "redirect:/";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteAop(Long id) {
		aopService.deleteNotice(id);
		return "redirect:/";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Long id, boolean state) {
		aopService.update(id, state);
		return "redirect:/";
	}

	@RequestMapping(value = "generate", method = RequestMethod.GET)
	@ResponseBody
	public String generate() {
		String result = adService.generateSpringAopDocument();
		return result;
	}
	
	@RequestMapping(value = "content", method = RequestMethod.GET)
	public String showXMLContent() {
		return "show-xml";
	}


	@RequestMapping(value = "signatures", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getSignatures() {
		List<String> result = aopService.getAllSignatures();
		return result;
	}
	
	@RequestMapping(value = "clear", method = RequestMethod.GET)
	public String cleardb(){
		aopService.clearAllPointcuts();
		return "redirect:/";
	}
	
	@RequestMapping(value = "tree", method = RequestMethod.GET)
	@ResponseBody
	public String getTree(){
	    File root = new File(baseDir);
	    return gson.toJson(toJsonArray(root));
	}
	
	private JsonObject toJsonArray(File root){
	    JsonArray array = new JsonArray();
	    for(File child: root.listFiles()){
		if(child.isDirectory()){
		    array.add(toJsonArray(child));
		}
		else{
		    array.add(new JsonPrimitive(child.getName()));
		}
	    }
	    JsonObject res = new JsonObject();
	    res.add(root.getName(), array);
	    return res;
	}
}
