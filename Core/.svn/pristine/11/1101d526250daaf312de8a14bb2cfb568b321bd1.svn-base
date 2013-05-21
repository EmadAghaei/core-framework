package com.citydi.common.base;

/**
 * Created by IntelliJ IDEA.
 * User: Aghayi
 * Date: 10/23/12
 * Time: 3:58 PM
 */
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.persistence.Entity;

public class SmartAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean
{
	private String basePackage;

	public void setBasePackage( String basePackage ) {
		this.basePackage = basePackage;
	}

	@Override
	protected void postProcessAnnotationConfiguration( AnnotationConfiguration config ) throws HibernateException {
		assert ( basePackage!=null );

		ResolverUtil<Entity> resolverUtil = new ResolverUtil<Entity>();

		resolverUtil.findAnnotated( Entity.class, basePackage.split(",") );
		for( Class<? extends Entity> entityClass : resolverUtil.getClasses() )
			config.addAnnotatedClass( entityClass );
	}
}
