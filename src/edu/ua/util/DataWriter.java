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

import java.io.Writer;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class DataWriter extends XMLWriter {
  
  private final static Object SEEN_NOTHING = new Object();
  private final static Object SEEN_ELEMENT = new Object();
  private final static Object SEEN_DATA = new Object();
  
  // Internal state.
  private Object state = SEEN_NOTHING;
  private Stack<Object> stateStack = new Stack<Object>();
  private int indentStep = 0;
  private int depth = 0;
  

  /**
   * Create a new data writer for standard output.
   */
  public DataWriter() {
    super();
  }

  /**
   * Create a new data writer for standard output.
   *
   * <p>Use the XMLReader provided as the source of events.</p>
   *
   * @param xmlreader The parent in the filter chain.
   */
  public DataWriter(XMLReader xmlreader) {
    super(xmlreader);
  }

  /**
   * Create a new data writer for the specified output.
   *
   * @param writer The character stream where the XML document will be written.
   */
  public DataWriter(Writer writer) {
    super(writer);
  }

  /**
   * Create a new data writer for the specified output. <p>Use the XMLReader
   * provided as the source of events.</p>
   *
   * @param xmlreader The parent in the filter chain.
   * @param writer The character stream where the XML document will be written.
   */
  public DataWriter(XMLReader xmlreader, Writer writer) {
    super(xmlreader, writer);
  }
  
  // Accessors and setters.
  /**
   * Return the current indent step.
   *
   * <p>Return the current indent step: each start tag will be indented by this
   * number of spaces times the number of ancestors that the element has.</p>
   *
   * @return The number of spaces in each indentation step, or 0 or less for no
   * indentation.
   * @see #setIndentStep
   */
  public int getIndentStep() {
    return indentStep;
  }

  /**
   * Set the current indent step.
   *
   * @param indentStep The new indent step (0 or less for no indentation).
   * @see #getIndentStep
   */
  public void setIndentStep(int indentStep) {
    this.indentStep = indentStep;
  }

  /**
   * Reset the writer so that it can be reused.
   *
   * <p>This method is especially useful if the writer failed with an exception
   * the last time through.</p>
   *
   * @see com.megginson.sax.XMLWriter#reset
   */
  public void reset() {
    depth = 0;
    state = SEEN_NOTHING;
    stateStack = new Stack<Object>();
    super.reset();
  }

  /**
   * Write a start tag.
   *
   * <p>Each tag will begin on a new line, and will be indented by the current
   * indent step times the number of ancestors that the element has.</p>
   *
   * <p>The newline and indentation will be passed on down the filter chain
   * through regular characters events.</p>
   *
   * @param uri The element's Namespace URI.
   * @param localName The element's local name.
   * @param qName The element's qualified (prefixed) name.
   * @param atts The element's attribute list.
   * @exception org.xml.sax.SAXException If there is an error writing the start
   * tag, or if a filter further down the chain raises an exception.
   * @see XMLWriter#startElement(String, String, String, Attributes)
   */
  public void startElement(String uri, String localName,
          String qName, Attributes atts) throws SAXException {
    stateStack.push(SEEN_ELEMENT);
    state = SEEN_NOTHING;
    if (depth > 0) {
      super.characters(LINE_SEPARATOR);
    }
    doIndent();
    super.startElement(uri, localName, qName, atts);
    depth++;
  }

  /**
   * Write an end tag.
   *
   * <p>If the element has contained other elements, the tag will appear
   * indented on a new line; otherwise, it will appear immediately following
   * whatever came before.</p>
   *
   * <p>The newline and indentation will be passed on down the filter chain
   * through regular characters events.</p>
   *
   * @param uri The element's Namespace URI.
   * @param localName The element's local name.
   * @param qName The element's qualified (prefixed) name.
   * @exception org.xml.sax.SAXException If there is an error writing the end
   * tag, or if a filter further down the chain raises an exception.
   * @see XMLWriter#endElement(String, String, String)
   */
  public void endElement(String uri, String localName, String qName)
          throws SAXException {
    depth--;
    if (state == SEEN_ELEMENT) {
      super.characters(LINE_SEPARATOR);
      doIndent();
    }
    super.endElement(uri, localName, qName);
    state = stateStack.pop();
  }

  /**
   * Write a empty element tag.
   *
   * <p>Each tag will appear on a new line, and will be indented by the current
   * indent step times the number of ancestors that the element has.</p>
   *
   * <p>The newline and indentation will be passed on down the filter chain
   * through regular characters events.</p>
   *
   * @param uri The element's Namespace URI.
   * @param localName The element's local name.
   * @param qName The element's qualified (prefixed) name.
   * @param atts The element's attribute list.
   * @exception org.xml.sax.SAXException If there is an error writing the empty
   * tag, or if a filter further down the chain raises an exception.
   * @see XMLWriter#emptyElement(String, String, String, Attributes)
   */
  public void emptyElement(String uri, String localName,
          String qName, Attributes atts) throws SAXException {
    state = SEEN_ELEMENT;
    if (depth > 0) {
      super.characters(LINE_SEPARATOR);
    }
    doIndent();
    super.emptyElement(uri, localName, qName, atts);
  }

  /**
   * Write a sequence of characters.
   *
   * @param ch The characters to write.
   * @param start The starting position in the array.
   * @param length The number of characters to use.
   * @exception org.xml.sax.SAXException If there is an error writing the
   * characters, or if a filter further down the chain raises an exception.
   * @see XMLWriter#characters(char[], int, int)
   */
  public void characters(char ch[], int start, int length)
          throws SAXException {
    state = SEEN_DATA;
    super.characters(ch, start, length);
  }

  /**
   * Print indentation for the current level.
   *
   * @exception org.xml.sax.SAXException If there is an error writing the
   * indentation characters, or if a filter further down the chain raises an
   * exception.
   */
  private void doIndent() throws SAXException {
    if (indentStep > 0 && depth > 0) {
      int n = indentStep * depth;
      char ch[] = new char[n];
      for (int i = 0; i < n; i++) {
        ch[i] = ' ';
      }
      characters(ch, 0, n);
    }
  }
  
}