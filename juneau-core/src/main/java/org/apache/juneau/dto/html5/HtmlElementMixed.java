// ***************************************************************************************************************************
// * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.  See the NOTICE file *
// * distributed with this work for additional information regarding copyright ownership.  The ASF licenses this file        *
// * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance            *
// * with the License.  You may obtain a copy of the License at                                                              *
// *                                                                                                                         *
// *  http://www.apache.org/licenses/LICENSE-2.0                                                                             *
// *                                                                                                                         *
// * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an  *
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the        *
// * specific language governing permissions and limitations under the License.                                              *
// ***************************************************************************************************************************
package org.apache.juneau.dto.html5;

import static org.apache.juneau.xml.annotation.XmlFormat.*;

import java.util.*;

import org.apache.juneau.annotation.*;
import org.apache.juneau.xml.annotation.*;

/**
 * A subclass of HTML elements that contain mixed content (elements and text).
 */
public class HtmlElementMixed extends HtmlElement {

	private LinkedList<Object> children;

	/**
	 * The children of this element.
	 * @return The children of this element.
	 */
	@Xml(format=MIXED)
	@BeanProperty(beanDictionary=HtmlBeanDictionary.class)
	public LinkedList<Object> getChildren() {
		return children;
	}

	/**
	 * Sets the children of this element.
	 *
	 * @param children The new children of this element.
	 * @return This object (for method chaining).
	 */
	public HtmlElement setChildren(LinkedList<Object> children) {
		this.children = children;
		return this;
	}

	/**
	 * Adds one or more child elements to this element.
	 * @param children The children to add as child elements.
	 * 	Can be a mixture of strings and {@link HtmlElement} objects.
	 * @return This object (for method chaining).
	 */
	@SuppressWarnings("hiding")
	public HtmlElement children(Object...children) {
		if (children.length != 0) {
			if (this.children == null)
				this.children = new LinkedList<Object>();
			for (Object c : children)
				this.children.add(c);
		}
		return this;
	}

	/**
	 * Adds a child element to this element.
	 * @param child The child to add as a child element.
	 * 	Can be a string or {@link HtmlElement}.
	 * @return This object (for method chaining).
	 */
	public HtmlElement child(Object child) {
		if (this.children == null)
			this.children = new LinkedList<Object>();
		this.children.add(child);
		return this;
	}
}