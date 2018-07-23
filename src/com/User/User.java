package com.User;

import java.sql.Blob;

/**
 * 
 * @author 凡帅
 *2018年3月14日
 *上午9:43:41
 *用户类
 */
public class User {

	private int id;
	private String name;
	private String password;
	
	private Blob picture;//Blob对应大的二进制文件，如视频音乐等，CBlob对应大的文本类型，

	public Blob getPicture() {
		return picture;
	}

	public User(int id, String name, String password, Blob picture) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.picture = picture;
	}

	public void setBlob(Blob picture) {
		this.picture = picture;
	}

	public User() {

	}

	

	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", picture=" + picture + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
