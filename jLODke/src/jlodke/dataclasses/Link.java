package jlodke.dataclasses;

public class Link /*implements GlobalHTMLAttributes, EventHTMLAttributes*/{
		
	private String charset;
	private String url;
	private String text;
	private String langCode;
	private String rel;
	private String rev;
	private String type;
	private String download;
	private String name;
	
	//MAKE THIS AS INTERFACES!!!
	private EventHTMLAttrs eventAttributes;
	
	public static final String XPATH_GET_HREF = "//a/@href";
	public static final String XPATH_GET_TEXT = "//a/text()";
	public static final String XPATH_GET_HREF_LANG_CODE = "//a/@hreflang";
	public static final String XPATH_GET_RELATION = "//a/@rel";
	public static final String XPATH_GET_REV = "//a/@rev";
	public static final String XPATH_GET_MIME_TYPE = "//a/@type";
	public static final String XPATH_GET_DOWNLOAD = "//a/@download";
	public static final String XPATH_GET_CHARSET = "//a/@charset";
	public static final String XPATH_GET_NAME = "//a/@name";
	public static final String XPATH_GET_TYPE = "//a/@type";

	public Link() {
		eventAttributes = new EventHTMLAttrs();
	}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getLangCode() {
			return langCode;
		}

		public void setLangCode(String langCode) {
			this.langCode = langCode;
		}

		public String getRel() {
			return rel;
		}

		public void setRel(String rel) {
			this.rel = rel;
		}

		public String getRev() {
			return rev;
		}

		public void setRev(String rev) {
			this.rev = rev;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public EventHTMLAttrs getEventAttributes() {
			return eventAttributes;
		}

		public void setEventAttributes(EventHTMLAttrs eventAttributes) {
			this.eventAttributes = eventAttributes;
		}

		public String getDownload() {
			return download;
		}

		public void setDownload(String download) {
			this.download = download;
		}

		public String getCharset() {
			return charset;
		}

		public void setCharset(String charset) {
			this.charset = charset;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
}