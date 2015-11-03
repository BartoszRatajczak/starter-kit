package pl.spring.demo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractDao<T> {

	private Set<T> set = new HashSet<T>();
	
	public T save(T o) {
		set.add(o);
		return o;
	}
	
	public List<T> findAll() {
		return new ArrayList<>(set);
	}
}
