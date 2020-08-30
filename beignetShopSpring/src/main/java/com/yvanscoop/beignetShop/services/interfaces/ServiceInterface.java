package com.yvanscoop.beignetShop.services.interfaces;

import java.util.List;

public interface ServiceInterface<T> {
	
	T create(T entity);
	List<T> getAll();
	T getOne(Long id);
	T update(T entity);
	void delete(T entity);

}
