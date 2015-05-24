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
package org.emfjson.jackson.databind.ser;

import org.eclipse.emf.ecore.EObject;

import org.emfjson.common.Options;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public interface IdSerializer {

	void serialize(EObject object, JsonGenerator jg, Options options) throws IOException;

}
