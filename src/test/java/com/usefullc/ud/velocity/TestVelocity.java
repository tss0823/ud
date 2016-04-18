/**
 * 
 */
package com.usefullc.ud.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;

/**
 * @author tangss
 *
 * @2013年8月31日 @下午3:08:26
 */
public class TestVelocity {

	public static void main(String[] args) {
		
		
		String LOGGER_NAME = "velexample";
		
		Logger log = org.slf4j.LoggerFactory.getLogger(LOGGER_NAME);

		Velocity.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
      "org.apache.velocity.runtime.log.Log4JLogChute" );
		Velocity.setProperty("runtime.log.logsystem.log4j.logger",
                    LOGGER_NAME);
		Velocity.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, false);
		Velocity.setProperty(RuntimeConstants.INPUT_ENCODING, "utf-8");
		Velocity.setProperty(RuntimeConstants.OUTPUT_ENCODING, "utf-8");
		Velocity.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "d:/");
		//Velocity.setProperty(RuntimeConstants.re, "vm");
		
		
		Velocity.init();

		VelocityContext context = new VelocityContext();

		context.put( "name", new String("这是") );

		Template template = null;

		try
		{
		   template = Velocity.getTemplate("mytemplate.txt");
		}
		catch( ResourceNotFoundException rnfe )
		{
		   // couldn't find the template
		}
		catch( ParseErrorException pee )
		{
		  // syntax error: problem parsing the template
		}
		catch( MethodInvocationException mie )
		{
		  // something invoked in the template
		  // threw an exception
		}
		catch( Exception e )
		{}

		StringWriter sw = new StringWriter();

		template.merge( context, sw );
		
		System.out.println(sw.toString());
	    
	}
}
