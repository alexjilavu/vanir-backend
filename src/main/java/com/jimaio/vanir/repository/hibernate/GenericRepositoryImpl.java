package com.jimaio.vanir.repository.hibernate;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.jimaio.vanir.repository.AbstractHibernateDao;
import com.jimaio.vanir.repository.GenericRepository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericRepositoryImpl <T extends Serializable> extends AbstractHibernateDao<T>
	implements GenericRepository<T> {

}
