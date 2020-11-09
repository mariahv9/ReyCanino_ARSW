package edu.eci.arsw.rey.reycanino.reyCanino.db;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangesInitializer implements InitializingBean{
	
	@Autowired
	private ReservaChangesListener listener;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		listener.pushChangestoWebSocket();
	}
	
}
