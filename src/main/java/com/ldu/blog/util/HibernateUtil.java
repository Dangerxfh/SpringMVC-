package com.ldu.blog.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionfactory;
	private static Session session;
	
	static{
		Configuration config=new Configuration().configure();
		StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		StandardServiceRegistry ssr=ssrb.build();
		config.buildSessionFactory(ssr);
		
	}
	//��ȡ�Ự����sessionfactory
	public static SessionFactory getSessionfactory(){
		return sessionfactory;
	}
	//��ȡ�Ựsession
	public static Session getSession(){
		session=sessionfactory.openSession();
		return session;
	}
	
	//�ر�session
	public static void  closeSession(Session session){
		if(session!=null)
			session.close();
	}
	
}
