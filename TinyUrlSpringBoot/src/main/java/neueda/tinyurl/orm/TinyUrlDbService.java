package neueda.tinyurl.orm;

import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import neueda.tinyurl.controller.Response;

@SuppressWarnings("deprecation")
@Service
@Component
public class TinyUrlDbService {
	/**
	 * Saves long and short url to DB
	 * 
	 * @param response
	 * @param id
	 * @param timeStampDate
	 */
	public void saveUrl(Response response, final String id, Timestamp timeStampDate) {
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		UrlDao urlEntity = new UrlDao();
		urlEntity.setShortUrl(id);
		urlEntity.setLongUrl(response.getUrl());
		urlEntity.setIpAddress(response.getIpAddr());
		// Timestamp rand = randomTSGenerator();//this is the random TS generator when
		// required to generate mock data
		urlEntity.setTimeStamp(timeStampDate);// replace here with random timestamp when needed
		session.beginTransaction();
		session.save(urlEntity);
		session.getTransaction().commit();
	}

	/**
	 * get long url from db
	 * 
	 * @param name
	 * @return
	 */
	public UrlDao getLongUrl(String name) {
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query query2 = session.createQuery("from UrlDao where shorturl = (:name)");
		query2.setParameter("name", name);
		return (UrlDao) query2.list().get(0);

	}

	/**
	 * random timestamp generator
	 * 
	 * @return
	 */
	/*
	 * private Timestamp randomTSGenerator() { long offset =
	 * Timestamp.valueOf("2012-01-01 00:00:00").getTime(); long end =
	 * Timestamp.valueOf("2019-01-01 00:00:00").getTime(); long diff = end - offset
	 * + 1; return new Timestamp(offset + (long)(Math.random() * diff));
	 * 
	 * }
	 */
}
