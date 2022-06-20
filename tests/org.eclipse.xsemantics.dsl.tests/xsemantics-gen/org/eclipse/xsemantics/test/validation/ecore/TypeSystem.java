/**
 * Copyright (c) 2013-2017 Lorenzo Bettini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Lorenzo Bettini - Initial contribution and API
 */
package org.eclipse.xsemantics.test.validation.ecore;

import com.google.common.base.Objects;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsemantics.runtime.ErrorInformation;
import org.eclipse.xsemantics.runtime.Result;
import org.eclipse.xsemantics.runtime.RuleApplicationTrace;
import org.eclipse.xsemantics.runtime.RuleEnvironment;
import org.eclipse.xsemantics.runtime.RuleFailedException;
import org.eclipse.xsemantics.runtime.XsemanticsRuntimeSystem;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * some particular cases
 */
@SuppressWarnings("all")
public class TypeSystem extends XsemanticsRuntimeSystem {
  public static final String ECLASSEOBJECT = "org.eclipse.xsemantics.test.validation.ecore.EClassEObject";

  private PolymorphicDispatcher<Result<EObject>> typeDispatcher;

  private PolymorphicDispatcher<Result<EObject>> type2Dispatcher;

  public TypeSystem() {
    init();
  }

  public void init() {
    typeDispatcher = buildPolymorphicDispatcher1(
    	"typeImpl", 3, "|-", ":");
    type2Dispatcher = buildPolymorphicDispatcher1(
    	"type2Impl", 3, "||-", ":");
  }

  public Result<EObject> type(final EClass c) {
    return type(new RuleEnvironment(), null, c);
  }

  public Result<EObject> type(final RuleEnvironment _environment_, final EClass c) {
    return type(_environment_, null, c);
  }

  public Result<EObject> type(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EClass c) {
    try {
    	return typeInternal(_environment_, _trace_, c);
    } catch (Exception _e_type) {
    	return resultForFailure(_e_type);
    }
  }

  public Result<EObject> type2(final EClass c) {
    return type2(new RuleEnvironment(), null, c);
  }

  public Result<EObject> type2(final RuleEnvironment _environment_, final EClass c) {
    return type2(_environment_, null, c);
  }

  public Result<EObject> type2(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EClass c) {
    try {
    	return type2Internal(_environment_, _trace_, c);
    } catch (Exception _e_type2) {
    	return resultForFailure(_e_type2);
    }
  }

  public Result<Boolean> checkEClass(final EClass eClass) {
    return checkEClass(null, eClass);
  }

  public Result<Boolean> checkEClass(final RuleApplicationTrace _trace_, final EClass eClass) {
    try {
    	return checkEClassInternal(_trace_, eClass);
    } catch (Exception _e_CheckEClass) {
    	return resultForFailure(_e_CheckEClass);
    }
  }

  protected Result<Boolean> checkEClassInternal(final RuleApplicationTrace _trace_, final EClass eClass) throws RuleFailedException {
    EObject result = null;
    /* empty |- eClass : result */
    Result<EObject> result_1 = typeInternal(emptyEnvironment(), _trace_, eClass);
    checkAssignableTo(result_1.getFirst(), EObject.class);
    result = (EObject) result_1.getFirst();
    
    return new Result<Boolean>(true);
  }

  protected Result<EObject> typeInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EClass c) {
    try {
    	checkParamsNotNull(c);
    	return typeDispatcher.invoke(_environment_, _trace_, c);
    } catch (Exception _e_type) {
    	sneakyThrowRuleFailedException(_e_type);
    	return null;
    }
  }

  protected void typeThrowException(final String _error, final String _issue, final Exception _ex, final EClass c, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }

  protected Result<EObject> type2Internal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final EClass c) {
    try {
    	checkParamsNotNull(c);
    	return type2Dispatcher.invoke(_environment_, _trace_, c);
    } catch (Exception _e_type2) {
    	sneakyThrowRuleFailedException(_e_type2);
    	return null;
    }
  }

  protected void type2ThrowException(final String _error, final String _issue, final Exception _ex, final EClass c, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    throwRuleFailedException(_error, _issue, _ex, _errorInformations);
  }

  protected Result<EObject> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EClass eClass) throws RuleFailedException {
    try {
    	final RuleApplicationTrace _subtrace_ = newTrace(_trace_);
    	final Result<EObject> _result_ = applyRuleEClassEObject(G, _subtrace_, eClass);
    	addToTrace(_trace_, new Provider<Object>() {
    		public Object get() {
    			return ruleName("EClassEObject") + stringRepForEnv(G) + " |- " + stringRep(eClass) + " : " + stringRep(_result_.getFirst());
    		}
    	});
    	addAsSubtrace(_trace_, _subtrace_);
    	return _result_;
    } catch (Exception e_applyRuleEClassEObject) {
    	typeThrowException(ruleName("EClassEObject") + stringRepForEnv(G) + " |- " + stringRep(eClass) + " : " + "EObject",
    		ECLASSEOBJECT,
    		e_applyRuleEClassEObject, eClass, new ErrorInformation[] {new ErrorInformation(eClass)});
    	return null;
    }
  }

  protected Result<EObject> applyRuleEClassEObject(final RuleEnvironment G, final RuleApplicationTrace _trace_, final EClass eClass) throws RuleFailedException {
    EObject object = null; // output parameter
    /* 'foo' == new String() || 'bar' == new String() */
    if (!(Objects.equal("foo", new String()) || Objects.equal("bar", new String()))) {
      sneakyThrowRuleFailedException("\'foo\' == new String() || \'bar\' == new String()");
    }
    /* 'foo' == new String() && 'bar' == new String() */
    if (!(Objects.equal("foo", new String()) && Objects.equal("bar", new String()))) {
      sneakyThrowRuleFailedException("\'foo\' == new String() && \'bar\' == new String()");
    }
    String _string = new String();
    String _firstUpper = StringExtensions.toFirstUpper("bar");
    String _plus = (_string + _firstUpper);
    boolean _equals = Objects.equal("foo", _plus);
    /* 'foo' == new String() + 'bar'.toFirstUpper */
    if (!_equals) {
      sneakyThrowRuleFailedException("\'foo\' == new String() + \'bar\'.toFirstUpper");
    }
    String _string_1 = new String();
    String _firstUpper_1 = StringExtensions.toFirstUpper("bar");
    String _plus_1 = (_string_1 + _firstUpper_1);
    boolean _notEquals = (!Objects.equal("foo", _plus_1));
    /* 'foo' != new String() + 'bar'.toFirstUpper */
    if (!_notEquals) {
      sneakyThrowRuleFailedException("\'foo\' != new String() + \'bar\'.toFirstUpper");
    }
    String _string_2 = new String();
    String _firstUpper_2 = StringExtensions.toFirstUpper("bar");
    final String temp = (_string_2 + _firstUpper_2);
    boolean _contains = "foo".contains("f");
    /* 'foo'.contains('f') */
    if (!_contains) {
      sneakyThrowRuleFailedException("\'foo\'.contains(\'f\')");
    }
    "foo".concat("f");
    boolean _contains_1 = "foo".contains("f");
    /* !('foo'.contains('f')) */
    if (!(!_contains_1)) {
      sneakyThrowRuleFailedException("!(\'foo\'.contains(\'f\'))");
    }
    return new Result<EObject>(object);
  }
}
