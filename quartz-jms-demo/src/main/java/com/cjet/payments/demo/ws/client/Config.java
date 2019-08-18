package com.cjet.payments.demo.ws.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@PropertySource("classpath:wsdl/webservice.properties")
public class Config {
	
	@Value("${webservice.contextPath}")
	private String contextPath;
	
	@Value("${webservice.uri}")
	private String uri;
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this is the package name specified in the <generatePackage> specified in pom.xml
		marshaller.setContextPath(contextPath);
		
		return marshaller;
	}

	@Bean
	public SOAPConnector soapConnector(Jaxb2Marshaller marshaller) {
		SOAPConnector client = new SOAPConnector();
		client.setDefaultUri(uri);

		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
