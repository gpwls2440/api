package com.databox.www.component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.databox.www.tms.TMSConnection;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TmsComponent implements ServletContextListener{
	
	@Setter @Getter
	public static TMSConnection pools = null;

	@Value("${global.hostName}")
	private String hostName;

	@Value("${global.siteIP}")
	private String siteIP;

	@Value("${global.socketPort}")
	private int socketPort;

	@Value("${global.poolSize}")
	private int poolSize;
	
	
	/** 컨텍스트 초기화 시 커넥션풀 시작한다 */
	public void contextInitialized(ServletContextEvent event) {
		log.info("==================================================== START ======================================");
		pools = new TMSConnection(hostName, siteIP, socketPort, poolSize);
		pools.start();
		
        System.out.println("TestThreadPool::main() start...");
	}

	/** 컨텍스트 종료 시 커넥션풀을 종료시킨다 */
	public void contextDestroyed(ServletContextEvent event) {
		pools.end();
		System.out.println("TestThreadPool::main() end...");
	}

}
