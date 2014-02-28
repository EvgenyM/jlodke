package jlodke.utils;

import jlodke.scraper.GraphBuilder;

public class ScraperConfigGenerator {
	
	public static String prepareGetStarterSet(String url, String starterXPathExpession)
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<config>"
				+ "		<var-def name=\"data\">"
				+ "			<html-to-xml outputtype=\"pretty\" >"
				+ "				<http url=\"" + url + "\"></http>"
				+ "			</html-to-xml>"
				+ "		</var-def>"				
				+ "		<var-def name=\"" + GraphBuilder.SCRAPER_KEY + "\">"
				+ "			<xpath expression="+starterXPathExpession+">"
				+ "				<var name=\"data\"/>"	
				+ "			</xpath>"
				+ "		</var-def>"
				+ "</config>";		
	}
	
	public static String extract(String xPathExpression, String data)
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<config>"			
				+ "		<var-def name=\"" + GraphBuilder.SCRAPER_KEY + "\">"
				+ "			<xpath expression=\""+xPathExpression+"\">"
				+ "				<![CDATA[ "
				+ 					data
				+ "					  ]]>"
				+ "			</xpath>"
				+ "		</var-def>"
				+ "</config>"; 
	}

	public static String prepareGetLinks(String url)
	{
		//pageAsXml = pageAsXml.replaceAll("[?][>]", "/>");
		//pageAsXml = pageAsXml.replaceAll("[<][?]", "<");
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<config>"
				+ "		<var-def name=\"data\">"
				+ "			<html-to-xml outputtype=\"pretty\" >"
				+ "				<http url=\"" + url + "\"></http>"
				+ "			</html-to-xml>"
				+ "		</var-def>"				
				+ "		<var-def name=\"" + GraphBuilder.SCRAPER_KEY + "\">"
				+ "			<xpath expression=\"//a\">"
				+ "				<var name=\"data\"/>"	
				+ "			</xpath>"
				+ "		</var-def>"
				+ "</config>";								
	}
}