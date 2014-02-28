package jlodke.dataclasses;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Page {
	
	private String cleanedContent;
	private String header;
	private String rawHtml;
	private Link ownLink;
	private List<Link> outgoingLinkCollecion;
	
	public Page() {
		outgoingLinkCollecion = new ArrayList<Link>();
	}

	public Link getOwnLink() {
		return ownLink;
	}

	public void setOwnLink(Link ownLink) {
		this.ownLink = ownLink;
	}

	public List<Link> getLinkCollecion() {
		return outgoingLinkCollecion;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getCleanedContent() {
		return cleanedContent;
	}

	public void setCleanedContent(String cleanedContent) {
		this.cleanedContent = cleanedContent;
	}

	public String getRawHtml() {
		return rawHtml;
	}

	public void setRawHtml(String rawHtml) {
		this.rawHtml = rawHtml;
	}
	
	public String getOwnUriPart(String startsWith)
	{
		int pos = ownLink.getUrl().indexOf(startsWith) + startsWith.length();		
		return ownLink.getUrl().substring(pos);
	}
	
	public static String getUrlDecoded(String url) throws UnsupportedEncodingException
	{
		return URLDecoder.decode(url, "UTF-8");
	}
	
	public void addOutgoingLink(Link lnk)
	{
		this.outgoingLinkCollecion.add(lnk);
	}
	
	public void setOutgoingLinks(List<Link> links)
	{
		this.outgoingLinkCollecion = links;
	}
}
