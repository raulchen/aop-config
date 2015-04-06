package cn.otc.aopconfig.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.otc.aopconfig.model.Aspect;
import cn.otc.aopconfig.model.Bean;
import cn.otc.aopconfig.model.Notice;
import cn.otc.aopconfig.model.PointType;
import cn.otc.aopconfig.model.Pointcut;
import cn.otc.aopconfig.repository.AspectDao;
import cn.otc.aopconfig.repository.BeanDao;
import cn.otc.aopconfig.repository.NoticeDao;
import cn.otc.aopconfig.repository.PointcutDao;

@Service
public class AopService {

	@Autowired
	BeanDao beanDao;

	@Autowired
	AspectDao aspectDao;

	@Autowired
	NoticeDao noticeDao;

	@Autowired
	PointcutDao pointcutDao;

	@PostConstruct
	private void init() {
		
		 /*Bean bean = new Bean();
		 bean.setShortName("logService"); bean.setName("com.cccis.struts2.service.util.LogService"); 
		 bean = beanDao.save(bean);		 
		 Aspect aspect = new Aspect(); aspect.setRefBean(bean);
		 aspect.setName("log"); aspectDao.save(aspect);
		 
		 Bean dataBean = new Bean();
		 dataBean.setShortName("dataAccess"); dataBean.setName("com.cccis.aop.dataAccessSecurityController"); 
		 dataBean = beanDao.save(dataBean);		 
		 Aspect dataAspect = new Aspect(); dataAspect.setRefBean(dataBean);
		 dataAspect.setName("data"); aspectDao.save(dataAspect);*/
		 
//		 for (int i = 1; i <= 3; i++) { Bean bean = new Bean();
//		  bean.setShortName("c" + i); bean.setName("cn.otc.aopconfig.Class" +
//		  i); bean = beanDao.save(bean);
//		 
//		  Aspect aspect = new Aspect(); aspect.setRefBean(bean);
//		  aspect.setName("Aspect" + i); aspectDao.save(aspect); }
		 
	}

	public List<Bean> getBeans() {
		return (List<Bean>) beanDao.findAll();
	}

	public void createNotice(String pointcut, Long beanId, String method,
			PointType pointType) {

		String inputParameter = "";
		String signature = pointcut;
		if (pointcut.endsWith(")")) {
			int p = pointcut.lastIndexOf('(');
			if (p >= 0) {
				String[] params = pointcut.substring(p + 1,
						pointcut.length() - 1).split(",");
				for (int i = 0; i < params.length; i++) {
					if (i > 0) {
						inputParameter += ",";
					}
					String name = params[i].substring(params[i]
							.lastIndexOf(' ') + 1);
					inputParameter += name;
				}
				pointcut = "execution(* " + pointcut.substring(0, p) + "(..))";
				if (inputParameter.isEmpty() == false) {
					//pointcut += " and args(" + inputParameter + ")";
					pointcut += " and args(..)";
				}
			}
		} else {
			pointcut = "execution(* " + pointcut + "(..))";
		}

		Notice notice = new Notice();
		notice.setCreateTime(new Date());

		Bean bean = beanDao.findOne(beanId);
		Aspect aspect = aspectDao.findByRefBean(bean);
		notice.setAspect(aspect);

		notice.setMethod(method);
		notice.setPointcut(findOrCreatePointcut(pointcut, signature));
		notice.setPointType(pointType);
		notice.setInputParameter(inputParameter);
		notice.setState(true);

		noticeDao.save(notice);
	}

	public List<Notice> getNotices() {
		return (List<Notice>) noticeDao.findAll();
	}
	
	public Pointcut findOrCreatePointcut(String expression, String signature) {
		Pointcut pointcut = pointcutDao.findByExpression(expression);
		if (pointcut == null) {
			pointcut = new Pointcut();
			pointcut.setExpression(expression);
			pointcut.setSignature(signature);
			String subString = expression.substring(0, expression.indexOf("(..)"));
			pointcut.setShortName(subString.substring(subString.lastIndexOf('.')+1, subString.length()));
			pointcutDao.save(pointcut);
		}
		return pointcut;
	}

	public void deleteNotice(Long id) {
		noticeDao.delete(id);
	}

	public Notice update(Long id, boolean state) {
		Notice notice = noticeDao.findOne(id);
		notice.setState(state);
		return noticeDao.save(notice);
	}
	
	public List<String> getAllSignatures(){
		List<Pointcut> pointcuts = (List<Pointcut>) pointcutDao.findAll();
		List<String> signatures = new ArrayList<>();
		for(Pointcut pointcut:pointcuts){
			signatures.add(pointcut.getSignature());
		}
		return signatures;
	}
	
	public void clearAllPointcuts(){
		pointcutDao.deleteAll();
	}
}
