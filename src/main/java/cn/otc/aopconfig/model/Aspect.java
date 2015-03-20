package cn.otc.aopconfig.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aspect")
public class Aspect extends IdEntity {

    private String name;

    private Set<Notice> notices;

    private Bean refBean;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "aspect")
    // orphanRemoval = true,
    public Set<Notice> getNotices() {
        return notices;
    }

    public void setNotices(Set<Notice> notices) {
        this.notices = notices;
    }

    @ManyToOne()
    @JoinColumn(name = "bean_id")
    public Bean getRefBean() {
        return refBean;
    }

    public void setRefBean(Bean refBean) {
        this.refBean = refBean;
    }

}
