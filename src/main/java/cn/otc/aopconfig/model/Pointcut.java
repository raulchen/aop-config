package cn.otc.aopconfig.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pointcut")
public class Pointcut extends IdEntity {

    private String shortName;
    private String expression;
    private String signature;


    public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
