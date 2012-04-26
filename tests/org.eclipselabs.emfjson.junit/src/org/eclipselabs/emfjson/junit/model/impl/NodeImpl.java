/*******************************************************************************
 * Copyright (c) 2012 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Hillairet - initial API and implementation
 *******************************************************************************/
package org.eclipselabs.emfjson.junit.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipselabs.emfjson.junit.model.ModelPackage;
import org.eclipselabs.emfjson.junit.model.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.emfjson.junit.model.impl.NodeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipselabs.emfjson.junit.model.impl.NodeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipselabs.emfjson.junit.model.impl.NodeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipselabs.emfjson.junit.model.impl.NodeImpl#getManyRef <em>Many Ref</em>}</li>
 *   <li>{@link org.eclipselabs.emfjson.junit.model.impl.NodeImpl#getChild <em>Child</em>}</li>
 *   <li>{@link org.eclipselabs.emfjson.junit.model.impl.NodeImpl#getUniqueChild <em>Unique Child</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends EObjectImpl implements Node {
	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Node target;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Node source;

	/**
	 * The cached value of the '{@link #getManyRef() <em>Many Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManyRef()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> manyRef;

	/**
	 * The cached value of the '{@link #getChild() <em>Child</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChild()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> child;

	/**
	 * The cached value of the '{@link #getUniqueChild() <em>Unique Child</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueChild()
	 * @generated
	 * @ordered
	 */
	protected Node uniqueChild;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Node newTarget, NotificationChain msgs) {
		Node oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__SOURCE, Node.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, ModelPackage.NODE__SOURCE, Node.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Node newSource, NotificationChain msgs) {
		Node oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Node newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, ModelPackage.NODE__TARGET, Node.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, ModelPackage.NODE__TARGET, Node.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getManyRef() {
		if (manyRef == null) {
			manyRef = new EObjectEList<Node>(Node.class, this, ModelPackage.NODE__MANY_REF);
		}
		return manyRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getChild() {
		if (child == null) {
			child = new EObjectContainmentEList<Node>(Node.class, this, ModelPackage.NODE__CHILD);
		}
		return child;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getUniqueChild() {
		return uniqueChild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUniqueChild(Node newUniqueChild, NotificationChain msgs) {
		Node oldUniqueChild = uniqueChild;
		uniqueChild = newUniqueChild;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__UNIQUE_CHILD, oldUniqueChild, newUniqueChild);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUniqueChild(Node newUniqueChild) {
		if (newUniqueChild != uniqueChild) {
			NotificationChain msgs = null;
			if (uniqueChild != null)
				msgs = ((InternalEObject)uniqueChild).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.NODE__UNIQUE_CHILD, null, msgs);
			if (newUniqueChild != null)
				msgs = ((InternalEObject)newUniqueChild).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.NODE__UNIQUE_CHILD, null, msgs);
			msgs = basicSetUniqueChild(newUniqueChild, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__UNIQUE_CHILD, newUniqueChild, newUniqueChild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__SOURCE, Node.class, msgs);
				return basicSetTarget((Node)otherEnd, msgs);
			case ModelPackage.NODE__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, ModelPackage.NODE__TARGET, Node.class, msgs);
				return basicSetSource((Node)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__TARGET:
				return basicSetTarget(null, msgs);
			case ModelPackage.NODE__SOURCE:
				return basicSetSource(null, msgs);
			case ModelPackage.NODE__CHILD:
				return ((InternalEList<?>)getChild()).basicRemove(otherEnd, msgs);
			case ModelPackage.NODE__UNIQUE_CHILD:
				return basicSetUniqueChild(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				return getLabel();
			case ModelPackage.NODE__TARGET:
				return getTarget();
			case ModelPackage.NODE__SOURCE:
				return getSource();
			case ModelPackage.NODE__MANY_REF:
				return getManyRef();
			case ModelPackage.NODE__CHILD:
				return getChild();
			case ModelPackage.NODE__UNIQUE_CHILD:
				return getUniqueChild();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				setLabel((String)newValue);
				return;
			case ModelPackage.NODE__TARGET:
				setTarget((Node)newValue);
				return;
			case ModelPackage.NODE__SOURCE:
				setSource((Node)newValue);
				return;
			case ModelPackage.NODE__MANY_REF:
				getManyRef().clear();
				getManyRef().addAll((Collection<? extends Node>)newValue);
				return;
			case ModelPackage.NODE__CHILD:
				getChild().clear();
				getChild().addAll((Collection<? extends Node>)newValue);
				return;
			case ModelPackage.NODE__UNIQUE_CHILD:
				setUniqueChild((Node)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case ModelPackage.NODE__TARGET:
				setTarget((Node)null);
				return;
			case ModelPackage.NODE__SOURCE:
				setSource((Node)null);
				return;
			case ModelPackage.NODE__MANY_REF:
				getManyRef().clear();
				return;
			case ModelPackage.NODE__CHILD:
				getChild().clear();
				return;
			case ModelPackage.NODE__UNIQUE_CHILD:
				setUniqueChild((Node)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case ModelPackage.NODE__TARGET:
				return target != null;
			case ModelPackage.NODE__SOURCE:
				return source != null;
			case ModelPackage.NODE__MANY_REF:
				return manyRef != null && !manyRef.isEmpty();
			case ModelPackage.NODE__CHILD:
				return child != null && !child.isEmpty();
			case ModelPackage.NODE__UNIQUE_CHILD:
				return uniqueChild != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (label: ");
		result.append(label);
		result.append(')');
		return result.toString();
	}

} //NodeImpl