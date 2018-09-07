package com.company.mybatis.factory;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSessionFactory {

    /** 
     * Location of mybatis-config.xml file.
     * getResourceAsStream style lookup for its configuration file. 
     * the location of the configuration file for the current session.   
     */
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static SqlSessionFactory sqlSessionFactory;
	
	static {
    	try {
    		String resource = "mybatis-config.xml";
    		InputStream inputStream = Resources.getResourceAsStream(resource);
    		 sqlSessionFactory = new SqlSessionFactoryBuilder()
    				.build(inputStream);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
    }
    private MyBatisSessionFactory() {
    }
	
	/**
     * Returns the ThreadLocal Session instance.  
     * the <code>SqlSessionFactory</code> if needed.
     *
     *  @return SqlSession
     */
    public static SqlSession getSession()  {
        SqlSession session = (SqlSession) threadLocal.get();

		if (session == null) {
			if (sqlSessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sqlSessionFactory != null) ? sqlSessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

        return session;
    }
    
    /**
     * Returns the ThreadLocal Session instance.  
     * the <code>SqlSessionFactory</code> if needed.
     *
     *  @param autoCommit 
     *  @return SqlSession
     */
    public static SqlSession getSession(boolean autoCommit)  {
    	SqlSession session = (SqlSession) threadLocal.get();
    	
    	if (session == null) {
    		if (sqlSessionFactory == null) {
    			rebuildSessionFactory();
    		}
    		session = (sqlSessionFactory != null) ? sqlSessionFactory.openSession(autoCommit)
    				: null;
    		threadLocal.set(session);
    	}
    	
    	return session;
    }

	/**
     *  Rebuild mybatis session factory
     *
     */
	public static void rebuildSessionFactory() {
		try {
			String resource = "mybatis-config.xml";
    		InputStream inputStream = Resources.getResourceAsStream(resource);
    		 sqlSessionFactory = new SqlSessionFactoryBuilder()
    				.build(inputStream);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
     *  Close the single  session instance.
     *
     */
    public static void closeSession()   {
        SqlSession session = (SqlSession) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

	/**
     *  return session factory
     */
	public static SqlSessionFactory getSessionFactory() {
		return sqlSessionFactory;
	}
}
