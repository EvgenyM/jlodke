package jlodke.scraper;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jlodke.dataclasses.Link;
import jlodke.dataclasses.Page;
import jlodke.dataclasses.PubConcept;
import jlodke.dataclasses.UTF8TransliterationTable;
import jlodke.utils.ScraperConfigGenerator;

import org.apache.jena.atlas.logging.Log;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperContext;
import org.webharvest.runtime.variables.Variable;
import org.xml.sax.InputSource;

import eu.dm2e.grafeo.Grafeo;
import eu.dm2e.grafeo.jena.GrafeoImpl;

public class GraphBuilder {
	
	public static void main(String args[])
	{
		GraphBuilder bulider = new GraphBuilder();
		
		try {
			bulider.doWork();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static final String PARAMETER_BASE_URL = "Base URL";
	
	public static final String PARAMETER_DOMAINS_LIST_ALLOWED = "Use the following domains";
	
	public static final String PARAMETER_DOMAINS_LIST_BLOCKED= "Exclude the following domains";
	
	public static final String PARAMETER_KEYWORDS_USED = "Use concepts...";
	
	public static final String PARAMETER_KEYWORDS_BLOCKED = "Exclude concepts...";

	public static final String PARAMETER_MAX_GRAPH_DEPTH = "Max graph depth";
			
	public static final String PARAMETER_MAX_NODE_DEGREE = "Max node degree";
	
	public static final String SCRAPER_KEY = "scraper_key";
	
	private String baseURL = "http://www.rujen.ru";
	
	private Map<String, String> filters = new HashMap<String, String>();	
	
	public GraphBuilder() {		
		fillFilterSet();
	}
	

	public void doWork() throws UnsupportedEncodingException {
		
		List<Page> pages = null;
		try {
			pages = getCollectionOfPages(baseURL);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Data crawling completed");
		System.out.println("Initializing Grafeo...");
		Grafeo g = new GrafeoImpl();
		System.out.println("Creating set of PubConcepts...");
		System.out.println("Total number of objects to create: "+pages.size());
		HashMap<String, PubConcept> concepts = new HashMap<String, PubConcept>();
		for (Page page: pages)
		{
			PubConcept c = new PubConcept();
			c.setPrefLabel(page.getHeader());
			c.setPage(URI.create(baseURL+page.getOwnLink().getUrl()));
			c.setId("jl:" + "rujen/" + UTF8TransliterationTable.getLatinString(Page.getUrlDecoded(page.getOwnUriPart("/index.php/"))));
			c.setArticleStart(page.getCleanedContent());
			concepts.put(c.getPage().toString(), c);
		}
		System.out.println("Adding labels...");
		for (Page page: pages)
		{
			PubConcept origin = concepts.get(baseURL+page.getOwnLink().getUrl());
			for (Link link:page.getLinkCollecion()) {
				PubConcept target = concepts.get(baseURL+link.getUrl());
				if (target==null) continue;
				origin.getLinks().add(target);
				target.getAltLabels().add(link.getText());
			}			
		}		
		System.out.println("Adding objects to Grafeo...");
		for (String key:concepts.keySet())
		{
			g.getObjectMapper().addObject(concepts.get(key));
		}
		
		System.out.println("Saving Triples...");
		writeToFile("D:/RDF_JUDAICALINK/triples_A.txt", g.getNTriples());
		
		System.out.println("Saving TTL...");
		writeToFile("D:/RDF_JUDAICALINK/turtle_A.rdf", g.getTurtle());		
		
		System.out.println("Saving Terse TTL...");
		writeToFile("D:/RDF_JUDAICALINK/terse_turtle_A.rdf", g.getTerseTurtle());		
		
		System.out.println("Putting triples to endpoint...");
		g.putToEndpoint("http://lelystad.informatik.uni-mannheim.de:3031/judaicalink/update", "http://Rujen"); 
				
		System.out.println("Process finished execution.");
	}
	
	private void writeToFile(String path, String data) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
			out.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private List<Page> getCollectionOfPages(String url) throws Exception
	{		
		List<Page> pages = new ArrayList<Page>();
		
		if (!url.equals(null))
		{
			if (!url.equals(""))
			{
				List<Link> resultLinks = getListOfPagesToDownload(url);	
				System.out.println("Links retrieval finished");
				pages = downloadPagesSelection(resultLinks, 0, resultLinks.size());
			}
		}
		else
		{
			System.out.println("Please specify the correct base URL");
		}
		return pages;
	}
	
	private List<Page> downloadPagesSelection(List<Link> links, int startIndex, int offset) throws Exception
	{
		List<Page> result = new ArrayList<Page>();
		for (int i=startIndex;i<offset;i++)
		{
			Link lnk = links.get(i);
			Page page = new Page();
			page.setOwnLink(lnk);
			page.setHeader(lnk.getText());
			String pageContent = executeWebHarvest(ScraperConfigGenerator.prepareGetStarterSet(baseURL + lnk.getUrl(), filters.get("MainBlockContent"))).toString("UTF-8");
			page.setRawHtml(pageContent);
			String cleanedContent = executeWebHarvest(ScraperConfigGenerator.prepareGetStarterSet(baseURL + lnk.getUrl(), filters.get("MainBlockContentCleaned"))).toString("UTF-8");
			page.setCleanedContent(cleanedContent);
			page.setOutgoingLinks(getLinks(pageContent));
			result.add(page);
			System.out.println("	Page (content): "+new String(page.getHeader().getBytes("UTF-8"), "UTF-8")+" number "+i+" downloaded");
		}
		System.out.println("Page (content) set from "+startIndex+ " to "+offset+ " retrieved (Total: " + String.valueOf(offset-startIndex)+")");
		return result;
	}
	
	private List<Link> getListOfPagesToDownload(String url) throws UnsupportedEncodingException
	{
		List<Link> resultLinks = new ArrayList<Link>();						
		List<Link> coreLinks = getLinks(url, filters.get("StarterSet"));	
				
		int repeats = 0;
				
		for(Link lnk : coreLinks)
		{
			List<Link> derivedLinks = getLinks(url+lnk.getUrl(), filters.get("AlphabetSeletcionContent"));	
			if (repeats == 0)
			{
				for (Link dlnk : derivedLinks)
				{
					resultLinks.add(dlnk);
				}
			}
			else
			{
				for (Link dlnk : derivedLinks)
				{
					boolean lnkExist = false;
					for (Link rlnk : resultLinks)
					{
						if (dlnk.getUrl().equals(rlnk.getUrl())) {//Comparison by URL
							lnkExist = true;
							break;									
						}
					}
					if (!lnkExist)
						resultLinks.add(dlnk);
				}
			}					
			repeats++;
			System.out.println("Links retrieval: core page "+repeats+" processed");
			//TODO FOR TEST ONLY!!!
			if (repeats>0)
				break;
		}			
		return resultLinks;
	}
	
	private void fillFilterSet()
	{
		//TODO populate these filters here	
		filters.put("MainBlockContent", "\"//*[@id='bodyContent']/p\"");//Content of the main block
		filters.put("MainBlockContentCleaned", "\"//*[@id='bodyContent']/p/text()\"");//Clean text
		filters.put("MainBlock", "\"//*[@id='content']/h1\"");//Header before main text block
		filters.put("StarterSet", "\"//*[@id='bodyContent']/p[12]\"");//Set of URLs to start from
		filters.put("AlphabetSeletcionContent", "\"//*[@id='bodyContent']/table[2]/tbody\"");
		filters.put("MainBlockAsXml", "");
	}
	
	/****
	 * Parsing the links *
	 */
	private Link createLink(String data)
	{
		Link link = null;
		try {
			link = new Link();				
			link.setUrl(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_HREF, data)).toString("UTF-8"));
			link.setText(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_TEXT, data)).toString("UTF-8"));				
			link.setCharset(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_CHARSET, data)).toString("UTF-8"));
			link.setDownload(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_DOWNLOAD, data)).toString("UTF-8"));
			link.setLangCode(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_HREF_LANG_CODE, data)).toString("UTF-8"));
			link.setName(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_NAME, data)).toString("UTF-8"));
			link.setRel(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_RELATION, data)).toString("UTF-8"));
			link.setRev(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_REV, data)).toString("UTF-8"));
			link.setType(executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_TYPE, data)).toString("UTF-8"));											
		} catch (Exception e) {	}
		return link;
	}
	
	private List<Link> getLinks(String url, String xPathTag) throws UnsupportedEncodingException
	{		
		String starterBlock = executeWebHarvest(ScraperConfigGenerator.prepareGetStarterSet(url, xPathTag)).toString("UTF-8");
		String[] arr = starterBlock.split("<a ");
		
		List<Link> listOfLinks = new ArrayList<Link>();		
		
		for (int i=1;i<arr.length;i++)//skip first
		{
			arr[i]="<a "+arr[i];
			arr[i] = arr[i].substring(0, arr[i].indexOf("</a>")+4);
			listOfLinks.add(createLink(arr[i]));							
			System.out.println("	Links processing: page "+(i)+" out of "+(arr.length-1)+" processed");
		}		
		return listOfLinks;
	}
	
	private List<Link> getLinks(String data) throws Exception
	{		
		String[] hRefsAsString = executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_HREF, data)).toString("UTF-8").split("\n");
		String[] textsAsString = executeWebHarvest(ScraperConfigGenerator.extract(Link.XPATH_GET_TEXT, data)).toString("UTF-8").split("\n");
		
		List<Link> listOfLinks = new ArrayList<Link>();
		
		//Assuming the correct extraction: number of elements in both array is equal.
		//TODO Warning! Mismatching possible!
		
		if (hRefsAsString.length == textsAsString.length) {
			for (int i=0; i<hRefsAsString.length; i++)
			{
				Link lnk = new Link();
				lnk.setUrl(hRefsAsString[i]);
				lnk.setText(textsAsString[i]);
				listOfLinks.add(lnk);
			}
		} else {
			throw new Exception("Links can not be extracted for: "+data);
		}			
		return listOfLinks;
	}
	
	private Variable executeWebHarvest(String configText)
	{ 
		Variable res = null;		
		try {
			ScraperConfiguration config = new ScraperConfiguration(new InputSource(new ByteArrayInputStream(configText.getBytes("UTF-8"))));
			Scraper scrap = new Scraper(config, "");
			scrap.execute();		
			ScraperContext scraperContext = scrap.getContext();
			res = (Variable) scraperContext.get(SCRAPER_KEY);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
		return res;
	}
}
