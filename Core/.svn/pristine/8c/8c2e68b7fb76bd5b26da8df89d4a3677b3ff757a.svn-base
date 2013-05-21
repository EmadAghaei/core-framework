package com.citydi.common.base;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 *
 * @author Emad Aghayi created Jul 30, 2012 - 9:31:03 PM
 */
@MappedSuperclass
public abstract class BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;
//    @Version
//    @Column(name="VERSION", nullable=false, updatable=false, unique=false)
//    protected long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator="idGenerator"*/)
    @Column(name = "ID", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        BaseEntity entity = (BaseEntity) obj;

        if( id==null && entity.getId()==null ) {
            return true;
        } else if( id==null || entity.getId()==null ) {
            return false;
        }

        return id.equals(entity.getId());
    }
}