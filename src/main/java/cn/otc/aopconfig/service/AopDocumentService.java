package cn.otc.aopconfig.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.otc.aopconfig.model.Aspect;
import cn.otc.aopconfig.model.Bean;
import cn.otc.aopconfig.model.Notice;
import cn.otc.aopconfig.model.Pointcut;
import cn.otc.aopconfig.repository.AspectDao;
import cn.otc.aopconfig.repository.BeanDao;
import cn.otc.aopconfig.repository.PointcutDao;

@Service
public class AopDocumentService {

    @Autowired
    private AspectDao aspectDao;

    @Autowired
    private BeanDao beanDao;

    @Autowired
    private PointcutDao pointcutDao;

    
     private static final String AOP_FILENAME =
     "D:\\文件\\中科院\\项目\\毕设\\new\\cccis\\resources\\spring-aop.xml";
     
    public String generateSpringAopDocument() {

	//Document document = XMLHelper.readXML(getAopFilePath("spring-aop.xml"));
    Document document = XMLHelper.readXML(AOP_FILENAME);
	addBean(document);
	Element aspectConfig = findElement(document.getRootElement(),
		"//aop:config", "aop:config");
    aspectConfig.addAttribute("proxy-target-class", "true");
	removeAllElements(aspectConfig);
	addPointcut(document, aspectConfig);
	List<Aspect> aspects = (List<Aspect>) aspectDao.findAll();
	for (int i = 0; i < aspects.size(); i++) {
	    Element aspectElement = findElement(aspectConfig,
		    "//aop:aspect[@id='" + aspects.get(i).getName() + "']",
		    "aop:aspect");
	    addAspect(aspects.get(i), aspectElement);
	    if (aspectElement.elements().size() == 0) {
		aspectConfig.remove(aspectElement);
	    }
	}
	String path = AOP_FILENAME;
	XMLHelper.writeXML(document, path);
	try {
	    return FileUtils.readFileToString(new File(path));
	} catch (IOException e) {
	    return "";
	}
    }

    private void addBean(Document doc) {
	List<Bean> beans = (List<Bean>) beanDao.findAll();
	String namespace = doc.getRootElement().getNamespaceURI();
	for (Bean bean : beans) {
	    XPath xpath = doc.createXPath("/beans/ns:bean[@id='"
		    + bean.getShortName() + "']");
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("ns", namespace);// 给命名空间设置一个前缀
	    xpath.setNamespaceURIs(map);// 把命名空间加入XPath中
	    Element element = (Element) xpath.selectSingleNode(doc);
	    if (element == null) {
		element = doc.getRootElement().addElement("bean");
	    }
	    element.addAttribute("id", bean.getShortName());
	    element.addAttribute("class", bean.getName());
	}
    }

    private void addPointcut(Document document, Element aspectConfig) {
	List<Pointcut> pointcuts = (List<Pointcut>) pointcutDao.findAll();
	for (Pointcut pointcut : pointcuts) {
	    Element element = findElement(aspectConfig, "//aop:pointcut[@id='"
		    + pointcut.getShortName() + "']", "aop:pointcut");
	    element.addAttribute("id", pointcut.getShortName());
	    element.addAttribute("expression", pointcut.getExpression());
	}

    }

    private void addAspect(Aspect aspect, Element aspectElement) {
	aspectElement.addAttribute("id", aspect.getName());
	aspectElement.addAttribute("ref", aspect.getRefBean().getShortName());
	for (Notice notice : aspect.getNotices()) {
	    if (notice.isState()) {
		Element noticeElement = aspectElement.addElement("aop:"
			+ notice.getPointType().getName());
		addNotice(notice, noticeElement);
	    }
	}
    }

    private void addNotice(Notice notice, Element noticeElement) {
	String noticeType = notice.getPointType().getName();
	noticeElement.addAttribute("pointcut-ref", notice.getPointcut()
		.getShortName());
	noticeElement.addAttribute("method", notice.getMethod());
	if (notice.getInputParameter() != ""
		&& notice.getInputParameter() != null) {
	    noticeElement.addAttribute("arg-names", notice.getInputParameter());
	}
	if (notice.getOutputParameter() != ""
		&& notice.getOutputParameter() != null) {
	    if (noticeType.equals("after-returning")) {
		noticeElement.addAttribute("returning",
			notice.getOutputParameter());
	    } else if (noticeType.equals("after-throwing")) {
		noticeElement.addAttribute("throwing",
			notice.getOutputParameter());
	    }
	}
    }

    private Element findElement(Element father, String path, String elementName) {
	Element element = (Element) father.selectSingleNode(path);
	if (element == null) {
	    element = father.addElement(elementName);
	}
	return element;
    }

    @SuppressWarnings("unchecked")
    private void removeAllElements(Element father) {
	List<Element> list = father.elements();
	for (Element element : list) {
	    father.remove(element);
	}
    }

    String getAopFilePath(String fileName) {
	URL classPathUrl = this.getClass().getResource("/");
	String classPathDirectory = classPathUrl.getFile();
	String[] classPath = classPathDirectory.split("/");
	StringBuilder filePath = new StringBuilder();
	for (String s : classPath) {
	    if (!s.equals("") && !s.endsWith("target")
		    && !s.endsWith("classes")) {
		filePath.append(s).append("/");
	    }
	}
	return filePath.append("src/main/resources/").append(fileName)
		.toString();
    }

}

class XMLHelper {
    public static Document readXML(String filename) {
	Document doc = null;
	File f = new File(filename);// 读取文件
	SAXReader reader = new SAXReader();
	try {
	    doc = reader.read(f);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
	return doc;
    }

    public static void writeXML(Document doc, String path) {
	XMLWriter output = null;
	/** 格式化输出,类型IE浏览一样 */
	OutputFormat format = OutputFormat.createPrettyPrint();
	/** 指定XML字符集编码 */
	format.setEncoding("UTF-8");
	try {
	    output = new XMLWriter(new FileWriter(new File(path)), format);
	    output.write(doc);
	    output.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}