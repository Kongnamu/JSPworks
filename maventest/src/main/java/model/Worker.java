package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//롬복, allargs를 활용해서 getter, setter와 생성자를 대신 표현
//@AllArgsConstructor
@Getter
@Setter
public class Worker {
	private String name;
	private int age;
	private String email;
	
	/*
	 * //생성자 public Worker(String name, int age, String email) { super(); this.name
	 * = name; this.age = age; this.email = email; }
	 */
	
	//getter, setter
	/*
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; } public int getAge() { return age; } public void
	 * setAge(int age) { this.age = age; } public String getEmail() { return email;
	 * } public void setEmail(String email) { this.email = email; }
	 */
	
	
}
