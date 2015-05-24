/*
 * Copyright (c) 2015 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Guillaume Hillairet - initial API and implementation
 *
 */
package org.emfjson.jackson.databind.ser.references;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.emfjson.jackson.JacksonOptions;
import org.emfjson.handlers.URIHandler;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public class ReferenceAsObjectSerializer extends AbstractReferenceSerializer {

	@Override
	public void serialize(EObject source, EObject target, JsonGenerator jg, JacksonOptions options) throws IOException {
		URIHandler handler = options.uriHandler;
		handler.setBaseURI(source.eResource().getURI());

		if (target == null) {
			jg.writeNull();
		} else {
			final URI uri = EcoreUtil.getURI(target);

			jg.writeStartObject();
			if (isExternal(source, target)) {
				String value = handler.deresolve(uri).toString();

				if (uri == null) {
					jg.writeNullField(options.refField);
				} else {
					jg.writeStringField(options.refField, value);
				}
			} else {
				jg.writeStringField(options.refField, uri.fragment());
			}
			jg.writeEndObject();
		}
	}

}
