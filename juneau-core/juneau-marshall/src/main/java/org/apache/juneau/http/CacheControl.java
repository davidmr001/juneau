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
package org.apache.juneau.http;

/**
 * Represents a parsed <l>Cache-Control</l> HTTP request header.
 *
 * <p>
 * Used to specify directives that must be obeyed by all caching mechanisms along the request-response chain.
 *
 * <h6 class='figure'>Example</h6>
 * <p class='bcode'>
 * 	Cache-Control: no-cache
 * </p>
 *
 * <h6 class='topic'>RFC2616 Specification</h6>
 *
 * The Cache-Control general-header field is used to specify directives that MUST be obeyed by all caching mechanisms
 * along the request/response chain.
 * The directives specify behavior intended to prevent caches from adversely interfering with the request or response.
 * These directives typically override the default caching algorithms.
 * Cache directives are unidirectional in that the presence of a directive in a request does not imply that the same
 * directive is to be given in the response.
 *
 * <p>
 * Note that HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache (see section
 * 14.32).
 *
 * <p>
 * Cache directives MUST be passed through by a proxy or gateway application, regardless of their significance to that
 * application, since the directives might be applicable to all recipients along the request/response chain.
 * It is not possible to specify a cache- directive for a specific cache.
 *
 * <p class='bcode'>
 * 	Cache-Control   = "Cache-Control" ":" 1#cache-directive
 * 	cache-directive = cache-request-directive
 * 	     | cache-response-directive
 * 	cache-request-directive =
 * 	       "no-cache"                          ; Section 14.9.1
 * 	     | "no-store"                          ; Section 14.9.2
 * 	     | "max-age" "=" delta-seconds         ; Section 14.9.3, 14.9.4
 * 	     | "max-stale" [ "=" delta-seconds ]   ; Section 14.9.3
 * 	     | "min-fresh" "=" delta-seconds       ; Section 14.9.3
 * 	     | "no-transform"                      ; Section 14.9.5
 * 	     | "only-if-cached"                    ; Section 14.9.4
 * 	     | cache-extension                     ; Section 14.9.6
 * 	cache-response-directive =
 * 	       "public"                               ; Section 14.9.1
 * 	     | "private" [ "=" &lt;"&gt; 1#field-name &lt;"&gt; ] ; Section 14.9.1
 * 	     | "no-cache" [ "=" &lt;"&gt; 1#field-name &lt;"&gt; ]; Section 14.9.1
 * 	     | "no-store"                             ; Section 14.9.2
 * 	     | "no-transform"                         ; Section 14.9.5
 * 	     | "must-revalidate"                      ; Section 14.9.4
 * 	     | "proxy-revalidate"                     ; Section 14.9.4
 * 	     | "max-age" "=" delta-seconds            ; Section 14.9.3
 * 	     | "s-maxage" "=" delta-seconds           ; Section 14.9.3
 * 	     | cache-extension                        ; Section 14.9.6
 * 	cache-extension = token [ "=" ( token | quoted-string ) ]
 * </p>
 *
 * <p>
 * When a directive appears without any 1#field-name parameter, the directive applies to the entire request or response.
 * When such a directive appears with a 1#field-name parameter, it applies only to the named field or fields, and not
 * to the rest of the request or response. This mechanism supports extensibility; implementations of future versions
 * of the HTTP protocol might apply these directives to header fields not defined in HTTP/1.1.
 *
 * <p>
 * The cache-control directives can be broken down into these general categories:
 * <ul>
 * 	<li>Restrictions on what are cacheable; these may only be imposed by the origin server.
 * 	<li>Restrictions on what may be stored by a cache; these may be imposed by either the origin server or the user
 * 		agent.
 * 	<li>Modifications of the basic expiration mechanism; these may be imposed by either the origin server or the
 * 		user agent.
 * 	<li>Controls over cache revalidation and reload; these may only be imposed by a user agent.
 * 	<li>Control over transformation of entities.
 * 	<li>Extensions to the caching system.
 * </ul>
 *
 * <h6 class='topic'>Additional Information</h6>
 * <ul class='doctree'>
 * 	<li class='jp'>
 * 		<a class='doclink' href='package-summary.html#TOC'>org.apache.juneau.http</a>
 * 	<li class='extlink'>
 * 		<a class='doclink' href='https://www.w3.org/Protocols/rfc2616/rfc2616.html'>
 * 		Hypertext Transfer Protocol -- HTTP/1.1</a>
 * </ul>
 */
public final class CacheControl extends HeaderString {

	/**
	 * Returns a parsed <code>Cache-Control</code> header.
	 *
	 * @param value The <code>Cache-Control</code> header string.
	 * @return The parsed <code>Cache-Control</code> header, or <jk>null</jk> if the string was null.
	 */
	public static CacheControl forString(String value) {
		if (value == null)
			return null;
		return new CacheControl(value);
	}

	private CacheControl(String value) {
		super(value);
	}
}
