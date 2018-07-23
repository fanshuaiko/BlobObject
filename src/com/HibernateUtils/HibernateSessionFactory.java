package com.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 
 * @author 凡帅
 *2018年3月14日
 *下午2:38:21
 *创建session对象和SessionFacatory对象的类
 */
public class HibernateSessionFactory {

	private static Session session;
	private static SessionFactory sessionFactory;
	
	static {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
			System.out.println("创建SessionFactory失败!");
		}
	}
	/*
	 * 获得session对象的方法
	 */
	public static Session getSession() {
		//获得session
		session = sessionFactory.openSession();
		if(session==null||!session.isOpen()) {
			if(sessionFactory==null) {
			 session = rebuildSessionFactory().openSession();
			 return session;
			}else {
				session = sessionFactory.openSession();
				return session;
			}
		}
		return session;
	}
	/*
	 *获得SessionFactory的方法
	 */
	public static SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}
	/*
	 * 关闭
	 */
	public static void closeAll() {
		if(session.isOpen()||session!=null) {
			session.close();
		}
		if(sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}
	/*
	 * 当不能正常创建SessionFactory对象的时候，从新创建sessionFactory的方法
	 */
	public static SessionFactory rebuildSessionFactory() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
			System.out.println("重新SessionFactory失败！");
		}
		return sessionFactory;
	}
}


