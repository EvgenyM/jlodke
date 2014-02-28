package jlodke.dataclasses;

public class GlobalHTMLAttrs {
	
		private String accessKey;
		private String htmlClass;
		private boolean contentEditable;
		private String contextMenu;
		private String data;
		private String dir;
		private boolean draggable;
		private String dropZone;
		private String hidden;
		private String elementId;
		private String language;
		private boolean isSpellcheked;
		private String style;
		private int tabIndex;
		private String title;
		private boolean translate;

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
		
		public GlobalHTMLAttrs() {
		}

		public String getAccessKey() {
			return accessKey;
		}

		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		public String getHtmlClass() {
			return htmlClass;
		}

		public void setHtmlClass(String htmlClass) {
			this.htmlClass = htmlClass;
		}

		public boolean isContentEditable() {
			return contentEditable;
		}

		public void setContentEditable(boolean contentEditable) {
			this.contentEditable = contentEditable;
		}

		public String getContextMenu() {
			return contextMenu;
		}

		public void setContextMenu(String contextMenu) {
			this.contextMenu = contextMenu;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}

		public boolean isDraggable() {
			return draggable;
		}

		public void setDraggable(boolean draggable) {
			this.draggable = draggable;
		}

		public String getDropZone() {
			return dropZone;
		}

		public void setDropZone(String dropZone) {
			this.dropZone = dropZone;
		}

		public String getHidden() {
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}

		public String getElementId() {
			return elementId;
		}

		public void setElementId(String elementId) {
			this.elementId = elementId;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public boolean isSpellcheked() {
			return isSpellcheked;
		}

		public void setSpellcheked(boolean isSpellcheked) {
			this.isSpellcheked = isSpellcheked;
		}

		public String getStyle() {
			return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}

		public int getTabIndex() {
			return tabIndex;
		}

		public void setTabIndex(int tabIndex) {
			this.tabIndex = tabIndex;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public boolean isTranslate() {
			return translate;
		}

		public void setTranslate(boolean translate) {
			this.translate = translate;
		}
}