package cn.otc.aopconfig.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notice")
public class Notice extends IdEntity {

    private Pointcut pointcut;

    private PointType pointType;

    private Aspect aspect;

    private String inputParameter;

    private String outputParameter;

    private String method;

    private long userId;

    private Date createTime;

    private boolean state;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pointcut_id")
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pointtype_id")
    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public String getInputParameter() {
        return inputParameter;
    }

    public void setInputParameter(String inputParameter) {
        this.inputParameter = inputParameter;
    }

    public String getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(String outputParameter) {
        this.outputParameter = outputParameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @ManyToOne
    @JoinColumn(name = "aspect_id", nullable = false)
    public Aspect getAspect() {
        return aspect;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
