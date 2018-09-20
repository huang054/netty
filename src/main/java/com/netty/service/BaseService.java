package com.netty.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


public class BaseService<T> {
	
	@Autowired
	private JpaRepository<T, Long> repository;
	
	private static final Sort sort = new Sort(Sort.Direction.DESC, "id");
	
	public T findById(Long id) {
		Optional<T> t  = repository.findById(id);
		if(t.isPresent()) {
			return t.get();
		}else {
			return null;
		}
	}
	
	public void deleteInBatch(Iterable<T> entities) {
		repository.deleteInBatch(entities);
	}
	
	public T save(T t) {
		try {
			return repository.save(t);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<T> findAll(T t){
		Example<T> example = Example.of(t);
		return repository.findAll(example, sort);
	}
	
	public Iterable<T> findAllById(Iterable<Long>ids){
		return repository.findAllById(ids);
	}
	
	public Iterable<T> findAll() {
		return repository.findAll(sort);
	}
	
	public Iterable<T> findAll(Example<T> example ) {
		return repository.findAll(example, sort);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	public Page<T> findAll(Example<T> example,Pageable pageable){
		return repository.findAll(example,pageable);
	}
	
	public Page<T> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
}
