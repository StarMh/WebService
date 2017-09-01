package cn.wangtao34.user;

/**
 * 
 * @author wangtao34
 * @time 2017年8月17日 上午11:17:07
 */
public class User {
	private String name;
	private Integer age;
	private String gender;
	
	public User(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
}
