package org.app.service.entities;

import java.io.Serializable;
import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.NONE)
public class AtomLink implements Serializable{
	private URI href;
	private String rel;
	private String type;
	
	@XmlAttribute(name="href")
	public URI getHref() {
		return href;
	}
	@XmlAttribute(name="rel")
	public String getRel() {
		return rel;
	}
	@XmlAttribute(name="type")
	public String getType() {
		return type;
	}
	public AtomLink(String href, String rel) throws Exception{
		super();
		this.href = new URI(href);
		this.rel = rel;
		this.type = "text/html";
	}
	public AtomLink() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
