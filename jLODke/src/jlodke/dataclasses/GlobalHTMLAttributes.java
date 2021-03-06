package jlodke.dataclasses;

public interface GlobalHTMLAttributes {
	
	public static final String XPATH_GET_ACCESS_KEY = "//a/@accesskey";
	public static final String XPATH_GET_HTML_CLASS = "//a/@class";
	public static final String XPATH_GET_CONTENT_EDITABLE = "//a/@contenteditable";
	public static final String XPATH_GET_CONTEXT_MENU = "//a/@contextmenu";
	public static final String XPATH_GET_DATA = "//a/@data-*";
	public static final String XPATH_GET_DIR = "//a/@dir";
	public static final String XPATH_GET_DRAGGABLE = "//a/@draggable";
	public static final String XPATH_GET_DROP_ZONE = "//a/@dropzone";
	public static final String XPATH_GET_HIDDEN = "//a/@hidden";
	public static final String XPATH_GET_ELEMENT_ID = "//a/@id";
	public static final String XPATH_GET_LANGUAGE = "//a/@lang";
	public static final String XPATH_GET_IS_SPELLCHECKED = "//a/@spellcheck";
	public static final String XPATH_GET_STYLE = "//a/@style";
	public static final String XPATH_GET_TAB_INDEX = "//a/@tabindex";
	public static final String XPATH_GET_TITLE = "//a/@title";
	public static final String XPATH_GET_TRANSLATE = "//a/@translate";
	
	String accessKey = "";
	String htmlClass = "";
	boolean contentEditable = false;
	String contextMenu  = "";
	String data  = "";
	String dir  = "";
	boolean draggable  = false;
	String dropZone  = "";
	String hidden  = "";
	String elementId  = "";
	String language = "";
	boolean isSpellcheked = false;
	String style = "";
	int tabIndex = -1;
	String title = "";
	boolean translate = false;

	public String getAccessKey();
	public void setAccessKey(String accessKey);
	public String getHtmlClass();
	public void setHtmlClass(String htmlClass);
	public boolean isContentEditable();
	public void setContentEditable(boolean contentEditable);
	public String getContextMenu();
	public void setContextMenu(String contextMenu);
	public String getData();
	public void setData(String data);
	public String getDir();
	public void setDir(String dir);
	public boolean isDraggable();
	public void setDraggable(boolean draggable);
	public String getDropZone();
	public void setDropZone(String dropZone);
	public String getHidden();
	public void setHidden(String hidden);
	public String getElementId();
	public void setElementId(String elementId);
	public String getLanguage();
	public void setLanguage(String language);
	public boolean isSpellcheked();
	public void setSpellcheked(boolean isSpellcheked);
	public String getStyle();
	public void setStyle(String style);
	public int getTabIndex();
	public void setTabIndex(int tabIndex);
	public String getTitle();
	public void setTitle(String title);
	public boolean isTranslate();
	public void setTranslate(boolean translate);
}
