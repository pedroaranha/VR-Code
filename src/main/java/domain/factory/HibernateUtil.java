package domain.factory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	static{
		try {
			sessionFactory = HibernateUtil.configureSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Falha na criação inicial da SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException{
		return sessionFactory.openSession();
	}
	
	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    //SchemaExport se = new SchemaExport(configuration);
	    //se.execute(true, true, false, true);
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}

}
