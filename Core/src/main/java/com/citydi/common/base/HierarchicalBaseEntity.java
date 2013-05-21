package com.citydi.common.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HierarchicalBaseEntity<T extends BaseEntity> extends BaseEntity {

    private T parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PARENT")
    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }
}
