
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.HibernateUtils.HibernateSessionFactory;
import com.User.User;


public class HibernateSimpleExampleTest {

	private Session session;
	@Before
	public void ber(){
		
		 session = HibernateSessionFactory.getSession();
		 //开启事务
		 session.beginTransaction();
	}
	
	/**
	 * 将picture以Blob类型存到数据库中
	 * @throws Exception
	 */
	@Test
	public void saveBlob() throws Exception {
	
		User user = new User();
		//先获得照片文件
		File file = new File("d:"+File.separator+"timg.jpg");
		//或者File file = new File("E:\\tomcat.gif");
		//获得照片文件的输入流
		FileInputStream input = new FileInputStream(file);
		//创建一个blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
		user.setBlob(image);
		session.save(user);

	}
	/**
	 * 熊数据库中获得Blob类型的picture
	 * @throws Exception 
	 */
	@Test
	public void getBlob() throws Exception {
		//获得user对象
		User user= session.get(User.class, 0);
		Blob image = user.getPicture();
		//获得照片的输入流
		InputStream input  = image.getBinaryStream();
		//创建输出流
		File file = new File("d:\\dest.jpg");
		//获得输出流
		OutputStream output = new FileOutputStream(file);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		//input.read(buff); 
		output.write(input.read(buff));
		input.close();
		output.close();
		
		
	} 


	@After
	public void aft() {
		//提交事务
		session.getTransaction().commit();
		HibernateSessionFactory.closeAll();
		
	}
}