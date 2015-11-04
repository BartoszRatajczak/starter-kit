package pl.spring.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.AbstractDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.exception.IllegalJoinPointException;
import pl.spring.demo.to.IdAware;

@Aspect
@Component
public class BookDaoAdvisor {
	
	@Autowired
    private Sequence sequence;
	
	@Before(value = "@annotation(pl.spring.demo.annotation.NullableId)")
    public void before(JoinPoint joinPoint) throws Throwable {
    	checkNotNullId(joinPoint.getArgs()[0]);
    	setId(joinPoint, joinPoint.getTarget());
    		
    }

	private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }
	
	private void setId(JoinPoint joinPoint, Object target) {
		if (target instanceof AbstractDao<?>) {
			((IdAware) joinPoint.getArgs()[0]).setId(sequence.nextValue(((AbstractDao<?>) target).findAll()));
		} else {
			throw new IllegalJoinPointException();
		}
	}
}
