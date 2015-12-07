package pl.spring.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK_EXCHANGE")
public class StockExchangeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
