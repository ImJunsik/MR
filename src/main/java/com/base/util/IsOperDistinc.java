package com.base.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.mr.common.domain.ConfigurationSetting;

public class IsOperDistinc {
	
    public static boolean m_bOper = true;
    public static boolean m_bMail = true;
    public static boolean m_bOperDatabaseConfig = true;
    public static int init_count = -1;
    private final Logger logger = Logger.getLogger(this.getClass());
    
    
    private static IsOperDistinc ipd = new IsOperDistinc();

    private IsOperDistinc()
    {
    	isOperServer();
    	readDBconfig();
    }
    
    public static IsOperDistinc getInstance() {
        return ipd;
    }
    
    private String domainToIp(String dmain)
    {
    	String address = "";
    	InetAddress giriAddress;
		try {
			giriAddress = java.net.InetAddress.getByName(dmain);
		
        address = giriAddress.getHostAddress();
        System.out.println(address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			address = "172.17.140.159";		//yoo 운영 서버 IP 설정
		}
		
		return address;
    }
    
	public boolean isOperServer()
    {
		
		//yoo 240726 메일 전송 설정을 IP가 아닌 config 설정으로 한다
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");

		ConfigurationSetting configurationSetting = ctx.getBean("configurationSetting", ConfigurationSetting.class);
        
		m_bMail = configurationSetting.isMail();
		//configurationSetting.isOper();

        ctx.close();

		
    	// Yoo 운영 서버 일때만 돌게 한다
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("IP : [" + ip + "]");
			String host_id = ip.getHostAddress().trim();
			System.out.println("ID : [" + host_id + "]");
			//logger.debug(host_id + ":" + host_id, 0);
			logger.info("IP : [" + ip + "]");
			logger.info("ID : [" + host_id + "]");
			//String OperSrvIp = "172.18.61.159";			//new mr
			String OperSrvIp = domainToIp("mr.oilbank.co.kr");
			logger.info("OperSrvIp : [" + OperSrvIp + "]");
			//String OperSrvIp = "172.17.24.24";
			//String OperSrvIp = "172.17.59.20";
			
			if(host_id.equals(OperSrvIp))		
				m_bOper = true;
			else
				m_bOper = false;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("m_bOper : [" + m_bOper + "]" + m_bMail);
		System.out.println("m_bOper : [" + m_bOper + "]" + m_bMail);
		return m_bOper;
    }
    
    public void readDBconfig()
    {
    	//Yoo
    	InitialContext context;
		try {
			context = new InitialContext();
			Context xmlNode = (Context) context.lookup("java:comp/env");
			DataSource ds = (DataSource)xmlNode.lookup("jdbc/mrdb");

			String dbUrl = "";
	    	dbUrl = ds.toString();			//ds.getConnection().getMetaData().getURL();
			//int n = dbUrl.indexOf(".10.10");	//172.17.10.10
	    	int n = dbUrl.indexOf(".21.60");	//MR 운영 DB 172.17.21.60
			if(n > -1)
				m_bOperDatabaseConfig = true;
			else
				m_bOperDatabaseConfig = false;
			//System.out.println(dbUrl);
			System.out.println("m_bOperDatabaseConfig : [" + m_bOperDatabaseConfig + "]");
          

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
