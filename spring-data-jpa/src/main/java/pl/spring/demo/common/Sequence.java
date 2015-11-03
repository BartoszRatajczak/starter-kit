package pl.spring.demo.common;

import java.util.Collection;

import org.springframework.stereotype.Component;

import pl.spring.demo.to.IdAware;

@Component
public class Sequence {

	public long nextValue(Collection<?> existingIds) {
		long result = 1;
        for (Object nextExistingId : existingIds) {
            if (Long.compare(((IdAware) nextExistingId).getId(), result) > 0) {
                result = ((IdAware) nextExistingId).getId();
            }
        }
        return result;
	}
}
