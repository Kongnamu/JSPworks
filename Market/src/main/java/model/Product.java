package model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter //@ : 에너테이션(라이브러리가 주입됨)
@Setter
public class Product implements Serializable{
	private static final long serialVersionUID = 20L;
	
	//필드
	private int pno;
	private String pid;
	private String pname;
	private int price;
	private String description;
	private String category;
	private long pstock;
	private String condition;
	private String pimage;
	private Timestamp regDate;
	private Timestamp updateDate;
	private int quantity; // 장바구니에 담은 수량
}
