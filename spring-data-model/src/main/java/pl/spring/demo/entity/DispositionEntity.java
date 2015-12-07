package pl.spring.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISPOSITION")
public class DispositionEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
