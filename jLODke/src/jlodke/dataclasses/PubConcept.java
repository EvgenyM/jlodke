package jlodke.dataclasses;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import eu.dm2e.grafeo.annotations.Namespaces;
import eu.dm2e.grafeo.annotations.RDFClass;
import eu.dm2e.grafeo.annotations.RDFProperty;
import eu.dm2e.grafeo.gom.SerializablePojo;

@Namespaces({"skos", "http://www.w3.org/2004/02/skos/core#",
    "dc", "http://purl.org/dc/terms/",
    "dbpedia", "http://dbpedia.org/ontology/",
    "jl", "http://data.judaicalink.org/data/"
    })
@RDFClass("skos:Concept")
public class PubConcept extends SerializablePojo<PubConcept> {
	
	@RDFProperty("jl:fromPage")
	private URI page;
	@RDFProperty("skos:related")
	private Set<PubConcept> links = new HashSet<PubConcept>();
	@RDFProperty("skos:prefLabel")
	private String prefLabel;
	@RDFProperty("skos:altLabel")
	private Set<String> altLabels;
	@RDFProperty("jl:abstract")
	private String articleStart;	
	
	public URI getPage() {
		return page;
	}
	public void setPage(URI page) {
		this.page = page;
	}
	public Set<PubConcept> getLinks() {
		return links;
	}
	public void setLinks(Set<PubConcept> links) {
		this.links = links;
	}
	public String getPrefLabel() {
		return prefLabel;
	}
	public void setPrefLabel(String prefLabel) {
		this.prefLabel = prefLabel;
	}
	public Set<String> getAltLabels() {
		return altLabels;
	}
	public void setAltLabels(Set<String> altLabels) {
		this.altLabels = altLabels;
	}
	public String getArticleStart() {
		return articleStart;
	}
	public void setArticleStart(String articleStart) {
		this.articleStart = articleStart;
	}
	
	
	
	

}
