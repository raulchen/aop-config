package cn.otc.aopconfig.service;

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
	for (int i = 1; i <= 3; i++) {
	    Bean bean = new Bean();
	    bean.setShortName("c" + i);
	    bean.setName("cn.otc.aopconfig.Class" + i);
	    bean = beanDao.save(bean);

	    Aspect aspect = new Aspect();
	    aspect.setRefBean(bean);
	    aspect.setName("Aspect" + i);
	    aspectDao.save(aspect);
	}
    }

    public List<Bean> getBeans() {
	return (List<Bean>) beanDao.findAll();
    }

    public void createNotice(String pointcut, Long beanId, String method,
	    PointType pointType) {
	Notice notice = new Notice();
	notice.setCreateTime(new Date());

	Bean bean = beanDao.findOne(beanId);
	Aspect aspect = aspectDao.findByRefBean(bean);
	notice.setAspect(aspect);

	notice.setMethod(method);
	notice.setPointcut(findOrCreatePointcut(pointcut));
	notice.setPointType(pointType);
	notice.setState(true);

	noticeDao.save(notice);
    }

    public List<Notice> getNotices() {
	return (List<Notice>) noticeDao.findAll();
    }

    public Pointcut findOrCreatePointcut(String expression) {
	Pointcut pointcut = pointcutDao.findByExpression(expression);
	if (pointcut == null) {
	    pointcut = new Pointcut();
	    pointcut.setExpression(expression);
	    pointcut.setShortName(expression.substring(expression.length() - 6));
	    pointcutDao.save(pointcut);
	}
	return pointcut;
    }
    
    public void deleteNotice(Long id){
	noticeDao.delete(id);
    }
}