package com.movilizer.connector.example;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.movilizer.connector.example.md.MasterDataSendingConfiguration;

/**
 * Main class and Main Spring Configuration of the Application.
 *
 * @author Pavel Kotlov
 */
@SpringBootApplication
@Import(MasterDataSendingConfiguration.class)
public class Application {

	private static Log logger = LogFactory.getLog(Application.class);

	public static void main(String[] args) throws Exception {
		logger.debug("Starting Movilizer DSD data generation backend...");

		/*
		 * The problem that is solved by that: ClassCastException ..cannot be
		 * cast to com.sun.xml.bind.v2.runtime.reflect.Accessor An alternative
		 * way of fixing it was: System.setProperty(
		 * "com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");
		 */
		System.setProperty("javax.xml.bind.JAXBContext", "com.sun.xml.internal.bind.v2.ContextFactory");

		/*
		 * A way to set the profile to dsd scenario and epcis connection. Files
		 * like application-dsd-epcis.properties will be automatically loaded.
		 * An alternative way to manage profiles:
		 * System.setProperty("spring.config.name",
		 * "config/application-example-project");
		 */
		System.setProperty("spring.profiles.active", "example-project");

		/*
		 * If you want to debug the startup e.g. for yaml functionality:
		 * System.setProperty("logging.level.org.springframework", "DEBUG");
		 */

		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}
}