 /**
 * Copyright (c) 2013 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Aziz Nanthaamornphong
 */
package edu.ua.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.helpers.XMLFilterImpl;


public class XMLWriter extends XMLFilterImpl {

  private final Attributes EMPTY_ATTS = new AttributesImpl();

  private Hashtable<String, String> prefixTable;
  private Hashtable<String, Boolean> forcedDeclTable;
  private Hashtable<String, String> doneDeclTable;
  private int elementLevel = 0;
  private Writer output;
  private NamespaceSupport nsSupport;
  private int prefixCounter = 0;
  private boolean outputCharsAsIs = false;
  private String doctypeName = "";
  private String doctypeSystem = "";
  
  protected static final String LINE_SEPARATOR = System.getProperty("line.separator");

  
  public XMLWriter() {
    init(null);
  }

  
  public XMLWriter(Writer writer) {
    init(writer);
  }

  public XMLWriter(XMLReader xmlreader) {
    super(xmlreader);
    init(null);
  }

  public XMLWriter(XMLReader xmlreader, Writer writer) {
    super(xmlreader);
    init(writer);
  }
  
  private void init(Writer writer) {
    setOutput(writer);
    nsSupport = new NamespaceSupport();
    prefixTable = new Hashtable<String, String>();
    forcedDeclTable = new Hashtable<String, Boolean>();
    doneDeclTable = new Hashtable<String, String>();
  }

  public void reset() {
    elementLevel = 0;
    prefixCounter = 0;
    nsSupport.reset();
  }

  
  public void flush() throws IOException {
    output.flush();
  }

  public void setOutput(Writer writer) {
    if (writer == null) {
      output = new OutputStreamWriter(System.out);
    } else {
      output = writer;
    }
  }

  public void setDoctype(String doctypeName, String doctypeSystem) {
    this.doctypeName = doctypeName;
    this.doctypeSystem = doctypeSystem;
  }

  public void setPrefix(String uri, String prefix) {
    prefixTable.put(uri, prefix);
  }


  public String getPrefix(String uri) {
    return (String) prefixTable.get(uri);
  }

 
  public void forceNSDecl(String uri) {
    forcedDeclTable.put(uri, Boolean.TRUE);
  }

  
  public void forceNSDecl(String uri, String prefix) {
    setPrefix(uri, prefix);
    forceNSDecl(uri);
  }

  public void startDocument() throws SAXException {
    reset();

    boolean standalone = ((doctypeSystem.length() == 0) || (doctypeName
            .length() == 0));

    write("<?xml version=\"1.0\" standalone=\""
            + (standalone ? "yes" : "no") + "\"?>" + LINE_SEPARATOR);

    if (!standalone) {
      write("<!DOCTYPE " + doctypeName + " SYSTEM \""
              + doctypeSystem + "\">" + LINE_SEPARATOR);
    }

    super.startDocument();
  }

 
  public void endDocument() throws SAXException {
    write(LINE_SEPARATOR);
    super.endDocument();
    try {
      flush();
    } catch (IOException e) {
      throw new SAXException(e);
    }
  }

 
  public void startElement(String uri, String localName,
          String qName, Attributes atts) throws SAXException {
    elementLevel++;
    nsSupport.pushContext();
    write('<');
    writeName(uri, localName, qName, true);
    writeAttributes(atts);
    if (elementLevel == 1) {
      forceNSDecls();
    }
    writeNSDecls();
    write('>');
    super.startElement(uri, localName, qName, atts);
  }

  
  public void endElement(String uri, String localName, String qName)
          throws SAXException {
    write("</");
    writeName(uri, localName, qName, true);
    write('>');
    if (elementLevel == 1) {
      write(LINE_SEPARATOR);
    }
    super.endElement(uri, localName, qName);
    nsSupport.popContext();
    elementLevel--;
  }

  
  public void characters(char ch[], int start, int len)
          throws SAXException {
    writeEsc(ch, start, len, false);
    super.characters(ch, start, len);
  }

 
  public void ignorableWhitespace(char ch[], int start, int length)
          throws SAXException {
    writeEsc(ch, start, length, false);
    super.ignorableWhitespace(ch, start, length);
  }

  public void processingInstruction(String target, String data)
          throws SAXException {
    write("<?");
    write(target);
    write(' ');
    write(data);
    write("?>");
    if (elementLevel < 1) {
      write(LINE_SEPARATOR);
    }
    super.processingInstruction(target, data);
  }

  
  public void emptyElement(String uri, String localName,
          String qName, Attributes atts) throws SAXException {
    nsSupport.pushContext();
    write('<');
    writeName(uri, localName, qName, true);
    writeAttributes(atts);
    if (elementLevel == 1) {
      forceNSDecls();
    }
    writeNSDecls();
    write("/>");
    super.startElement(uri, localName, qName, atts);
    super.endElement(uri, localName, qName);
  }
  
  public void startElement(String uri, String localName)
          throws SAXException {
    startElement(uri, localName, "", EMPTY_ATTS);
  }

  
  public void startElement(String localName) throws SAXException {
    startElement("", localName, "", EMPTY_ATTS);
  }

  
  public void endElement(String uri, String localName)
          throws SAXException {
    endElement(uri, localName, "");
  }

  
  public void endElement(String localName) throws SAXException {
    endElement("", localName, "");
  }

  
  public void emptyElement(String uri, String localName)
          throws SAXException {
    emptyElement(uri, localName, "", EMPTY_ATTS);
  }

  
  public void emptyElement(String localName) throws SAXException {
    emptyElement("", localName, "", EMPTY_ATTS);
  }

  
  public void dataElement(String uri, String localName, String qName,
          Attributes atts, String content) throws SAXException {
    startElement(uri, localName, qName, atts);
    characters(content);
    endElement(uri, localName, qName);
  }

 
  public void dataElement(String uri, String localName, String content)
          throws SAXException {
    dataElement(uri, localName, "", EMPTY_ATTS, content);
  }

 
  public void dataElement(String localName, String content)
          throws SAXException {
    dataElement("", localName, "", EMPTY_ATTS, content);
  }

 
  public void characters(String data) throws SAXException {
    char ch[] = data.toCharArray();
    characters(ch, 0, ch.length);
  }

  
  public void setOutputCharsAsIs(boolean outputCharsAsIs) {
    this.outputCharsAsIs = outputCharsAsIs;
  }

  
  private void forceNSDecls() {
    Enumeration prefixes = forcedDeclTable.keys();
    while (prefixes.hasMoreElements()) {
      String prefix = (String) prefixes.nextElement();
      doPrefix(prefix, null, true);
    }
  }

  private String doPrefix(String uri, String qName, boolean isElement) {
    String defaultNS = nsSupport.getURI("");
    if ("".equals(uri)) {
      if (isElement && defaultNS != null) {
        nsSupport.declarePrefix("", "");
      }
      return null;
    }
    String prefix;
    if (isElement && defaultNS != null && uri.equals(defaultNS)) {
      prefix = "";
    } else {
      prefix = nsSupport.getPrefix(uri);
    }
    if (prefix != null) {
      return prefix;
    }
    prefix = (String) doneDeclTable.get(uri);
    if (prefix != null
            && ((!isElement || defaultNS != null)
            && "".equals(prefix) || nsSupport
            .getURI(prefix) != null)) {
      prefix = null;
    }
    if (prefix == null) {
      prefix = (String) prefixTable.get(uri);
      if (prefix != null
              && ((!isElement || defaultNS != null)
              && "".equals(prefix) || nsSupport
              .getURI(prefix) != null)) {
        prefix = null;
      }
    }
    if (prefix == null && qName != null && !"".equals(qName)) {
      int i = qName.indexOf(':');
      if (i == -1) {
        if (isElement && defaultNS == null) {
          prefix = "";
        }
      } else {
        prefix = qName.substring(0, i);
      }
    }
    for (; prefix == null || nsSupport.getURI(prefix) != null; prefix = "__NS"
                    + ++prefixCounter)
                    ;
    nsSupport.declarePrefix(prefix, uri);
    doneDeclTable.put(uri, prefix);
    return prefix;
  }

  protected void write(char c) throws SAXException {
    try {
      output.write(c);
    } catch (IOException e) {
      throw new SAXException(e);
    }
  }

  protected void write(String s) throws SAXException {
    try {
      output.write(s);
    } catch (IOException e) {
      throw new SAXException(e);
    }
  }

  private void writeAttributes(Attributes atts) throws SAXException {
    int len = atts.getLength();
    for (int i = 0; i < len; i++) {
      char ch[] = null;
      if (atts.getValue(i) != null) {
        ch = atts.getValue(i).toCharArray();
      }
      write(' ');
      writeName(atts.getURI(i), atts.getLocalName(i), atts
              .getQName(i), false);
      write("=\"");
      if (ch != null) {
        writeEsc(ch, 0, ch.length, true);
      }
      write('"');
    }
  }

  protected void writeEsc(char ch[], int start, int length,
          boolean isAttVal) throws SAXException {
    for (int i = start; i < start + length; i++) {
      switch (ch[i]) {
        case '&':
          write("&amp;");
          break;
        case '<':
          write("&lt;");
          break;
        case '>':
          write("&gt;");
          break;
        case '\"':
          if (isAttVal) {
            write("&quot;");
          } else {
            write('\"');
          }
          break;
        default:
          if ((ch[i] > '\u007f') && (!outputCharsAsIs)) {
            write("&#");
            write(Integer.toString(ch[i]));
            write(';');
          } else {
            write(ch[i]);
          }
      }
    }
  }

  private void writeNSDecls() throws SAXException {
    Enumeration prefixes = nsSupport.getDeclaredPrefixes();
    while (prefixes.hasMoreElements()) {
      String prefix = (String) prefixes.nextElement();
      String uri = nsSupport.getURI(prefix);
      if (uri == null) {
        uri = "";
      }
      if (uri.length() > 0) {
        char ch[] = uri.toCharArray();
        write(' ');
        if ("".equals(prefix)) {
          write("xmlns=\"");
        } else {
          write("xmlns:");
          write(prefix);
          write("=\"");
        }
        writeEsc(ch, 0, ch.length, true);
        write('\"');
      }
    }
  }


  private void writeName(String uri, String localName, String qName,
          boolean isElement) throws SAXException {
    String prefix = doPrefix(uri, qName, isElement);
    if (prefix != null && !"".equals(prefix)) {
      write(prefix);
      write(':');
    }
    write(localName);
  }
  
}