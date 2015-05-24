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
package org.emfjson.jackson.databind.deser;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.emfjson.EMFJs;
import org.emfjson.common.Cache;
import org.emfjson.common.EObjects;
import org.emfjson.common.ReferenceEntry;
import org.emfjson.jackson.JacksonOptions;
import org.emfjson.jackson.errors.JSONException;
import org.emfjson.jackson.resource.JsonResource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EObjectDeserializer extends JsonDeserializer<EObject> implements ContextualDeserializer {

	private final Cache cache = new Cache();
	private final ResourceSet resourceSet;
	private final JacksonOptions options;

	public EObjectDeserializer(ResourceSet resourceSet, JacksonOptions options) {
		this.resourceSet = resourceSet;
		this.options = options;
	}

	@Override
	public EObject deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		final EClass root = findRoot(ctxt);

		EObject current = null;
		if (root != null) {
			current = EcoreUtil.create(root);
		}

		return doDeserialize(jp, current, ctxt);
	}

	public EObject deserialize(JsonParser jp, DeserializationContext ctxt, EReference containment) throws IOException {
		EObject current = null;

		if (containment != null) {
			final EClass defaultType = containment.getEReferenceType();

			if (!defaultType.isAbstract()) {
				current = EcoreUtil.create(defaultType);
			}
		}

		return doDeserialize(jp, current, ctxt);
	}

	protected EObject doDeserialize(JsonParser jp, EObject current,  DeserializationContext ctxt) throws IOException {
		final Resource resource = (Resource) ctxt.getAttribute("resource");
		final TokenBuffer buffer = new TokenBuffer(jp);

		while (jp.nextToken() != JsonToken.END_OBJECT) {
			final String fieldName = jp.getCurrentName();

			if (options.typeField.equalsIgnoreCase(fieldName)) {

				if (current == null) {
					current = create(resourceSet, jp.nextTextValue());
				}

			} else if (options.idField.equalsIgnoreCase(fieldName)) {

				options.idDeserializer.deserialize(jp, resource, current);

			} else {

				if (current != null) {
					readFeature(jp, current, fieldName, ctxt);
				} else {
					buffer.copyCurrentStructure(jp);
				}

			}
		}

		postDeserialize(buffer, current, ctxt);
		buffer.close();

		return current;
	}

	protected void postDeserialize(TokenBuffer buffer, EObject object, DeserializationContext ctxt) throws IOException {
		if (object == null)
			return;

		final JsonParser bufferedParser = buffer.asParser();

		while (bufferedParser.nextToken() != null) {
			readFeature(bufferedParser, object, bufferedParser.getCurrentName(), ctxt);
		}

		bufferedParser.close();
	}

	private EClass findRoot(DeserializationContext ctxt) {
		Object root = ctxt.getAttribute(EMFJs.OPTION_ROOT_ELEMENT);
		if (root instanceof EClass) {
			return (EClass) root;
		}

		return options.rootElement;
	}

	private void readFeature(JsonParser jp, EObject current, String fieldName, DeserializationContext ctxt) throws IOException {
		final EClass eClass = current.eClass();
		final EStructuralFeature feature = cache.getEStructuralFeature(eClass, fieldName);
		@SuppressWarnings("unchecked")
		final List<ReferenceEntry> entries = (List<ReferenceEntry>) ctxt.getAttribute("entries");

		if (feature != null) {

			if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {
				jp.nextToken();
			}

			if (feature instanceof EAttribute) {
				readAttribute(jp, current, (EAttribute) feature, ctxt);
			} else {
				EReference reference = (EReference) feature;

				if (reference.isContainment()) {
					readContainment(jp, current, reference, ctxt);
				} else {
					readReference(jp, current, reference, entries);
				}
			}

		} else {
			// we didn't find a feature so consume
			// the field and move on
			jp.nextToken();
			jp.skipChildren();
		}
	}

	@SuppressWarnings("unchecked")
	private void readContainment(JsonParser jp, EObject owner, EReference reference, DeserializationContext ctxt) throws IOException {
		final Resource resource = (Resource) ctxt.getAttribute("resource");
		final Class<?> type = reference.getEReferenceType().getInstanceClass();

		if (type != null && Map.Entry.class.isAssignableFrom(type)) {
			EMap<String, String> map = (EMap<String, String>) owner.eGet(reference);

			if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
				while (jp.nextToken() != JsonToken.END_OBJECT) {
					EObject entry = EObjects.createEntry(jp.getCurrentName(), jp.nextTextValue());
					map.add((Map.Entry<String, String>) entry);
				}
			}

		} else {

			if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
				while (jp.nextToken() != JsonToken.END_ARRAY) {

					try {
						EObject value = deserialize(jp, ctxt, reference);
						EObjects.setOrAdd(owner, reference, value);
					} catch (Exception e) {
						if (resource != null) {
							resource.getErrors().add(new JSONException(e, jp.getCurrentLocation()));
						}
					}
				}
			} else {

				try {
					EObject value = deserialize(jp, ctxt, reference);
					EObjects.setOrAdd(owner, reference, value);
				} catch (Exception e) {
					if (resource != null) {
						resource.getErrors().add(new JSONException(e, jp.getCurrentLocation()));
					}
				}
			}
		}
	}

	private void readReference(JsonParser jp, EObject owner, EReference reference, List<ReferenceEntry> entries) throws IOException {
		if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
			while (jp.nextToken() != JsonToken.END_ARRAY) {
				entries.add(options.referenceDeserializer.deserialize(jp, owner, reference, options));
			}
		} else {
			entries.add(options.referenceDeserializer.deserialize(jp, owner, reference, options));
		}
	}

	private void readAttribute(JsonParser jp, EObject owner, EAttribute attribute, DeserializationContext ctxt) throws IOException {
		final Resource resource = (Resource) ctxt.getAttribute("resource");
		final Class<?> type = attribute.getEAttributeType().getInstanceClass();

		if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
			while (jp.nextToken() != JsonToken.END_ARRAY) {
				Object value;
				if (type != null && Enumerator.class.isAssignableFrom(type)) {
					value = EcoreUtil.createFromString((EDataType) attribute.getEType(), jp.getText());
				} else {
					value = jp.readValueAs(type);
				}

				try {
					EObjects.setOrAdd(owner, attribute, value);
				} catch (Exception e) {
					if (resource != null) {
						resource.getErrors().add(new JSONException(e, jp.getCurrentLocation()));
					}
				}
			}
		} else {
			Object value;
			if (type != null && Enumerator.class.isAssignableFrom(type)) {
				value = EcoreUtil.createFromString((EDataType) attribute.getEType(), jp.getText());
			} else {
				value = jp.readValueAs(type);
			}

			try {
				EObjects.setOrAdd(owner, attribute, value);
			} catch (Exception e) {
				if (resource != null) {
					resource.getErrors().add(new JSONException(e, jp.getCurrentLocation()));
				}
			}
		}
	}

	protected EObject create(ResourceSet resourceSet, String type) {
		EClass eClass = cache.getEClass(resourceSet, type);
		if (eClass != null) {
			return EcoreUtil.create(eClass);
		}
		return null;
	}

	@Override
	public Class<?> handledType() {
		return EObject.class;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		@SuppressWarnings("unchecked")
		List<ReferenceEntry> entries = (List<ReferenceEntry>) ctxt.getAttribute("entries");
		Resource resource = (Resource) ctxt.getAttribute("resource");

		if (property instanceof ResourceProperty) {
			ResourceProperty p = (ResourceProperty) property;
			resource = p.getResource();
			entries = p.getEntries();
		}

		if (resource == null) {
			resource = new JsonResource(URI.createURI("default"));
			resourceSet.getResources().add(resource);
		}

		EReference containment = null;
		if (property instanceof EContainmentProperty) {
			containment = ((EContainmentProperty) property).getContainment();
		}

		if (entries == null) {
			entries = new ArrayList<>();
		}

		ctxt.setAttribute("entries", entries);
		ctxt.setAttribute("containment", containment);
		ctxt.setAttribute("resource", resource);

		return this;
	}

}