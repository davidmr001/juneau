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
package org.apache.juneau.html;

import static org.apache.juneau.html.HtmlDocSerializer.*;

import org.apache.juneau.*;
import org.apache.juneau.serializer.*;

/**
 * Context object that lives for the duration of a single serialization of {@link HtmlSerializer} and its subclasses.
 *
 * <p>
 * See {@link SerializerContext} for details.
 *
 * <p>
 * This class is NOT thread safe.  It is meant to be discarded after one-time use.
 */
public class HtmlDocSerializerSession extends HtmlStrippedDocSerializerSession {

	private final String noResultsMessage;
	private final String[] style, stylesheet, script, navlinks, head, header, nav, aside, footer;
	private final boolean nowrap;
	private final HtmlDocTemplate template;

	/**
	 * Create a new session using properties specified in the context.
	 *
	 * @param ctx
	 * 	The context creating this session object.
	 * 	The context contains all the configuration settings for this object.
	 * @param args
	 * 	Runtime arguments.
	 */
	protected HtmlDocSerializerSession(HtmlDocSerializerContext ctx, SerializerSessionArgs args) {
		super(ctx, args);
		ObjectMap p = getProperties();

		header = p.getStringArray(HTMLDOC_header, ctx.nav);
		nav = p.getStringArray(HTMLDOC_nav, ctx.nav);
		aside = p.getStringArray(HTMLDOC_aside, ctx.aside);
		footer = p.getStringArray(HTMLDOC_footer, ctx.footer);
		navlinks = p.getStringArray(HTMLDOC_navlinks, ctx.navlinks);
		style = p.getStringArray(HTMLDOC_style, ctx.style);
		stylesheet = p.getStringArray(HTMLDOC_stylesheet, ctx.stylesheet);
		script = p.getStringArray(HTMLDOC_script, ctx.script);
		head = p.getStringArray(HTMLDOC_head, ctx.head);
		nowrap = p.getBoolean(HTMLDOC_nowrap, ctx.nowrap);
		noResultsMessage = p.getString(HTMLDOC_noResultsMessage, ctx.noResultsMessage);
		template = ctx.newInstance(HtmlDocTemplate.class, p.getWithDefault(HTMLDOC_template, ctx.template));
	}

	@Override /* Session */
	public ObjectMap asMap() {
		return super.asMap()
			.append("HtmlDocSerializerSession", new ObjectMap()
				.append("aside", aside)
				.append("head", head)
				.append("header", header)
				.append("footer", footer)
				.append("nav", nav)
				.append("navlinks", navlinks)
				.append("noResultsMessage", noResultsMessage)
				.append("nowrap", nowrap)
				.append("script", script)
				.append("style", style)
				.append("stylesheet", stylesheet)
				.append("template", template)
			);
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_style} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_style} setting value in this context.
	 * 	An empty array if not specified.
	 * 	Never <jk>null</jk>.
	 */
	public final String[] getStyle() {
		return style;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_stylesheet} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_stylesheet} setting value in this context.
	 * 	An empty array if not specified.
	 * 	Never <jk>null</jk>.
	 */
	public final String[] getStylesheet() {
		return stylesheet;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_script} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_script} setting value in this context.
	 * 	An empty array if not specified.
	 * 	Never <jk>null</jk>.
	 */
	public final String[] getScript() {
		return script;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_head} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_head} setting value in this context.
	 * 	An empty array if not specified.
	 * 	Never <jk>null</jk>.
	 */
	public final String[] getHead() {
		return head;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_nowrap} setting value in this context.
	 *
	 * @return The {@link HtmlDocSerializer#HTMLDOC_nowrap} setting value in this context.
	 */
	public final boolean isNoWrap() {
		return nowrap;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_header} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_header} setting value in this context.
	 * 	<jk>null</jk> if not specified.
	 * 	 Never an empty string.
	 */
	public final String[] getHeader() {
		return header;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_navlinks} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_navlinks} setting value in this context.
	 *		<jk>null</jk> if not specified.
	 *		Never an empty map.
	 */
	public final String[] getNavLinks() {
		return navlinks;
	}

	/**
	 * Returns the template to use for generating the HTML page.
	 *
	 * @return
	 * 	The HTML page generator.
	 * 	Never <jk>null</jk>.
	 */
	public final HtmlDocTemplate getTemplate() {
		return template;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_nav} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_nav} setting value in this context.
	 * 	<jk>null</jk> if not specified.
	 * 	Never an empty string.
	 */
	public final String[] getNav() {
		return nav;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_aside} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_aside} setting value in this context.
	 * 	<jk>null</jk> if not specified.
	 *  	Never an empty string.
	 */
	public final String[] getAside() {
		return aside;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_footer} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_footer} setting value in this context.
	 * 	<jk>null</jk> if not specified.
	 * 	Never an empty string.
	 */
	public final String[] getFooter() {
		return footer;
	}

	/**
	 * Returns the {@link HtmlDocSerializer#HTMLDOC_noResultsMessage} setting value in this context.
	 *
	 * @return
	 * 	The {@link HtmlDocSerializer#HTMLDOC_noResultsMessage} setting value in this context.
	 * 	<jk>null</jk> if not specified.
	 * 	Never an empty string.
	 */
	public final String getNoResultsMessage() {
		return noResultsMessage;
	}

	@Override /* Serializer */
	protected void doSerialize(SerializerPipe out, Object o) throws Exception {

		try (HtmlWriter w = getHtmlWriter(out)) {
			HtmlDocTemplate t = getTemplate();

			w.sTag("html").nl(0);
			w.sTag(1, "head").nl(1);
			t.head(this, w, o);
			w.eTag(1, "head").nl(1);
			w.sTag(1, "body").nl(1);
			t.body(this, w, o);
			w.eTag(1, "body").nl(1);
			w.eTag("html").nl(0);
		}
	}

	/**
	 * Calls the parent {@link #doSerialize(SerializerPipe, Object)} method which invokes just the HTML serializer.
	 *
	 * @param out
	 * 	Where to send the output from the serializer.
	 * @param o The object being serialized.
	 * @throws Exception
	 */
	public void parentSerialize(Object out, Object o) throws Exception {
		try (SerializerPipe pipe = createPipe(out)) {
			super.doSerialize(pipe, o);
		}
	}
}
