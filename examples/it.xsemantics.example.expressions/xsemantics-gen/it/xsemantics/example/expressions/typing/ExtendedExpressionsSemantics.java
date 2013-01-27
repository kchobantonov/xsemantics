package it.xsemantics.example.expressions.typing;

import it.xsemantics.example.expressions.expressions.AndOrExpression;
import it.xsemantics.example.expressions.expressions.ArithmeticSigned;
import it.xsemantics.example.expressions.expressions.BooleanLiteral;
import it.xsemantics.example.expressions.expressions.BooleanNegation;
import it.xsemantics.example.expressions.expressions.BooleanType;
import it.xsemantics.example.expressions.expressions.Expression;
import it.xsemantics.example.expressions.expressions.ExpressionsFactory;
import it.xsemantics.example.expressions.expressions.IntType;
import it.xsemantics.example.expressions.expressions.Minus;
import it.xsemantics.example.expressions.expressions.MultiOrDiv;
import it.xsemantics.example.expressions.expressions.NumberLiteral;
import it.xsemantics.example.expressions.expressions.Plus;
import it.xsemantics.example.expressions.expressions.StringLiteral;
import it.xsemantics.example.expressions.expressions.StringType;
import it.xsemantics.example.expressions.expressions.Type;
import it.xsemantics.example.expressions.typing.ExpressionsSemantics;
import it.xsemantics.runtime.ErrorInformation;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleApplicationTrace;
import it.xsemantics.runtime.RuleEnvironment;
import it.xsemantics.runtime.RuleFailedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

/**
 * This system is more involved:
 * we want to implicitly convert string literals to numbers
 * and to booleans when this is possible
 */
@SuppressWarnings("all")
public class ExtendedExpressionsSemantics extends ExpressionsSemantics {
  public final static String STRINGLITERAL = "it.xsemantics.example.expressions.typing.rules.StringLiteral";
  
  public final static String MULTIORDIV = "it.xsemantics.example.expressions.typing.rules.MultiOrDiv";
  
  public final static String MINUS = "it.xsemantics.example.expressions.typing.rules.Minus";
  
  public final static String PLUS = "it.xsemantics.example.expressions.typing.rules.Plus";
  
  public final static String BOOLEANNEGATION = "it.xsemantics.example.expressions.typing.rules.BooleanNegation";
  
  public final static String ANDOR = "it.xsemantics.example.expressions.typing.rules.AndOr";
  
  public final static String ARITHMETICSIGNED = "it.xsemantics.example.expressions.typing.rules.ArithmeticSigned";
  
  public final static String STRINGTOINT = "it.xsemantics.example.expressions.typing.rules.StringToInt";
  
  public final static String STRINGTOBOOL = "it.xsemantics.example.expressions.typing.rules.StringToBool";
  
  public final static String INTTOINT = "it.xsemantics.example.expressions.typing.rules.IntToInt";
  
  public final static String BOOLTOBOOL = "it.xsemantics.example.expressions.typing.rules.BoolToBool";
  
  public final static String INTERPRETSTRINGLITERAL = "it.xsemantics.example.expressions.typing.rules.InterpretStringLiteral";
  
  public final static String INTERPRETMINUS = "it.xsemantics.example.expressions.typing.rules.InterpretMinus";
  
  public final static String INTERPRETMULTIORDIV = "it.xsemantics.example.expressions.typing.rules.InterpretMultiOrDiv";
  
  public final static String INTERPRETARITHMETICSIGNED = "it.xsemantics.example.expressions.typing.rules.InterpretArithmeticSigned";
  
  public final static String INTERPRETANDOR = "it.xsemantics.example.expressions.typing.rules.InterpretAndOr";
  
  public final static String INTERPRETBOOLEANNEGATION = "it.xsemantics.example.expressions.typing.rules.InterpretBooleanNegation";
  
  private PolymorphicDispatcher<Result<Boolean>> coerceDispatcher;
  
  public ExtendedExpressionsSemantics() {
    init();
  }
  
  @Override
  public void init() {
    super.init();
    coerceDispatcher = buildPolymorphicDispatcher1(
    	"coerceImpl", 4, "|~", "|>");
  }
  
  public Result<Boolean> coerce(final Expression expression, final Type expectedType) {
    return coerce(new RuleEnvironment(), null, expression, expectedType);
  }
  
  public Result<Boolean> coerce(final RuleEnvironment _environment_, final Expression expression, final Type expectedType) {
    return coerce(_environment_, null, expression, expectedType);
  }
  
  public Result<Boolean> coerce(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final Expression expression, final Type expectedType) {
    try {
    	return coerceInternal(_environment_, _trace_, expression, expectedType);
    } catch (Exception _e_coerce) {
    	return resultForFailure(_e_coerce);
    }
  }
  
  protected Result<Boolean> coerceInternal(final RuleEnvironment _environment_, final RuleApplicationTrace _trace_, final Expression expression, final Type expectedType) {
    try {
    	checkParamsNotNull(expression, expectedType);
    	return coerceDispatcher.invoke(_environment_, _trace_, expression, expectedType);
    } catch (Exception _e_coerce) {
    	sneakyThrowRuleFailedException(_e_coerce);
    	return null;
    }
  }
  
  protected void coerceThrowException(final String _error, final String _issue, final Exception _ex, final Expression expression, final Type expectedType, final ErrorInformation[] _errorInformations) throws RuleFailedException {
    
    String _stringRep = this.stringRep(expression);
    String _plus = ("cannot convert " + _stringRep);
    String _plus_1 = (_plus + 
      " to type ");
    String _stringRep_1 = this.stringRep(expectedType);
    String _plus_2 = (_plus_1 + _stringRep_1);
    String error = _plus_2;
    EObject source = expression;
    throwRuleFailedException(error,
    	_issue, _ex, new ErrorInformation(source, null));
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral str) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRuleStringLiteral(G, _subtrace_, str);
      addToTrace(_trace_, ruleName("StringLiteral") + stringRepForEnv(G) + " |- " + stringRep(str) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleStringLiteral) {
      typeThrowException(ruleName("StringLiteral") + stringRepForEnv(G) + " |- " + stringRep(str) + " : " + "Type",
      	STRINGLITERAL,
      	e_applyRuleStringLiteral, str, new ErrorInformation[] {new ErrorInformation(str)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRuleStringLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral str) throws RuleFailedException {
    Type resultType = null; // output parameter
    
    /* { val expected = env(G, 'expected', Type) G |~ str |> expected resultType = expected } or resultType = ExpressionsFactory::eINSTANCE.createStringType */
    try {
      Type _xblockexpression = null;
      {
        /* env(G, 'expected', Type) */
        Type _environmentaccess = environmentAccess(G, "expected", Type.class);
        final Type expected = _environmentaccess;
        /* G |~ str |> expected */
        coerceInternal(G, _trace_, str, expected);
        Type _resultType = resultType = expected;
        _xblockexpression = (_resultType);
      }
    } catch (Exception e) {
      StringType _createStringType = ExpressionsFactory.eINSTANCE.createStringType();
      resultType = _createStringType;
    }
    return new Result<Type>(resultType);
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiOrDiv multiOrDiv) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRuleMultiOrDiv(G, _subtrace_, multiOrDiv);
      addToTrace(_trace_, ruleName("MultiOrDiv") + stringRepForEnv(G) + " |- " + stringRep(multiOrDiv) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleMultiOrDiv) {
      typeThrowException(ruleName("MultiOrDiv") + stringRepForEnv(G) + " |- " + stringRep(multiOrDiv) + " : " + "IntType",
      	MULTIORDIV,
      	e_applyRuleMultiOrDiv, multiOrDiv, new ErrorInformation[] {new ErrorInformation(multiOrDiv)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRuleMultiOrDiv(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiOrDiv multiOrDiv) throws RuleFailedException {
    
    {
      IntType intType = ExpressionsFactory.eINSTANCE.createIntType();
      /* G, 'expected' <- intType |- multiOrDiv.left : intType */
      Expression _left = multiOrDiv.getLeft();
      Result<Type> result = typeInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _left);
      checkAssignableTo(result.getFirst(), IntType.class);
      intType = (IntType) result.getFirst();
      
      /* G, 'expected' <- intType |- multiOrDiv.right : intType */
      Expression _right = multiOrDiv.getRight();
      Result<Type> result_1 = typeInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _right);
      checkAssignableTo(result_1.getFirst(), IntType.class);
      intType = (IntType) result_1.getFirst();
      
    }
    IntType _createIntType = ExpressionsFactory.eINSTANCE.createIntType();
    return new Result<Type>(_createIntType);
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Minus minus) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRuleMinus(G, _subtrace_, minus);
      addToTrace(_trace_, ruleName("Minus") + stringRepForEnv(G) + " |- " + stringRep(minus) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleMinus) {
      typeThrowException(ruleName("Minus") + stringRepForEnv(G) + " |- " + stringRep(minus) + " : " + "IntType",
      	MINUS,
      	e_applyRuleMinus, minus, new ErrorInformation[] {new ErrorInformation(minus)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRuleMinus(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Minus minus) throws RuleFailedException {
    
    {
      IntType intType = ExpressionsFactory.eINSTANCE.createIntType();
      /* G, 'expected' <- intType |- minus.left : intType */
      Expression _left = minus.getLeft();
      Result<Type> result = typeInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _left);
      checkAssignableTo(result.getFirst(), IntType.class);
      intType = (IntType) result.getFirst();
      
      /* G, 'expected' <- intType |- minus.right : intType */
      Expression _right = minus.getRight();
      Result<Type> result_1 = typeInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _right);
      checkAssignableTo(result_1.getFirst(), IntType.class);
      intType = (IntType) result_1.getFirst();
      
    }
    IntType _createIntType = ExpressionsFactory.eINSTANCE.createIntType();
    return new Result<Type>(_createIntType);
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Plus plus) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRulePlus(G, _subtrace_, plus);
      addToTrace(_trace_, ruleName("Plus") + stringRepForEnv(G) + " |- " + stringRep(plus) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRulePlus) {
      typeThrowException(ruleName("Plus") + stringRepForEnv(G) + " |- " + stringRep(plus) + " : " + "Type",
      	PLUS,
      	e_applyRulePlus, plus, new ErrorInformation[] {new ErrorInformation(plus)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRulePlus(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Plus plus) throws RuleFailedException {
    Type type = null; // output parameter
    
    {
      /* G |- plus.left : var Type leftType */
      Expression _left = plus.getLeft();
      Type leftType = null;
      Result<Type> result = typeInternal(G, _trace_, _left);
      checkAssignableTo(result.getFirst(), Type.class);
      leftType = (Type) result.getFirst();
      
      /* G |- plus.right : var Type rightType */
      Expression _right = plus.getRight();
      Type rightType = null;
      Result<Type> result_1 = typeInternal(G, _trace_, _right);
      checkAssignableTo(result_1.getFirst(), Type.class);
      rightType = (Type) result_1.getFirst();
      
      /* { val expected = env(G, 'expected', Type) G |~ plus.left |> expected G |~ plus.right |> expected type = expected } or { (leftType instanceof StringType || rightType instanceof StringType) type = ExpressionsFactory::eINSTANCE.createStringType } or { (leftType instanceof IntType && rightType instanceof IntType) type = leftType } */
      try {
        Type _xblockexpression = null;
        {
          /* env(G, 'expected', Type) */
          Type _environmentaccess = environmentAccess(G, "expected", Type.class);
          final Type expected = _environmentaccess;
          /* G |~ plus.left |> expected */
          Expression _left_1 = plus.getLeft();
          coerceInternal(G, _trace_, _left_1, expected);
          /* G |~ plus.right |> expected */
          Expression _right_1 = plus.getRight();
          coerceInternal(G, _trace_, _right_1, expected);
          Type _type = type = expected;
          _xblockexpression = (_type);
        }
      } catch (Exception e) {
        /* { (leftType instanceof StringType || rightType instanceof StringType) type = ExpressionsFactory::eINSTANCE.createStringType } or { (leftType instanceof IntType && rightType instanceof IntType) type = leftType } */
        try {
          Type _xblockexpression_1 = null;
          {
            boolean _or = false;
            if ((leftType instanceof StringType)) {
              _or = true;
            } else {
              _or = ((leftType instanceof StringType) || (rightType instanceof StringType));
            }
            /* leftType instanceof StringType || rightType instanceof StringType */
            if (!_or) {
              sneakyThrowRuleFailedException("leftType instanceof StringType || rightType instanceof StringType");
            }
            StringType _createStringType = ExpressionsFactory.eINSTANCE.createStringType();
            Type _type_1 = type = _createStringType;
            _xblockexpression_1 = (_type_1);
          }
        } catch (Exception e_1) {
          {
            boolean _and = false;
            if (!(leftType instanceof IntType)) {
              _and = false;
            } else {
              _and = ((leftType instanceof IntType) && (rightType instanceof IntType));
            }
            /* leftType instanceof IntType && rightType instanceof IntType */
            if (!_and) {
              sneakyThrowRuleFailedException("leftType instanceof IntType && rightType instanceof IntType");
            }
            type = leftType;
          }
        }
      }
    }
    return new Result<Type>(type);
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanNegation negation) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRuleBooleanNegation(G, _subtrace_, negation);
      addToTrace(_trace_, ruleName("BooleanNegation") + stringRepForEnv(G) + " |- " + stringRep(negation) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleBooleanNegation) {
      typeThrowException(ruleName("BooleanNegation") + stringRepForEnv(G) + " |- " + stringRep(negation) + " : " + "BooleanType",
      	BOOLEANNEGATION,
      	e_applyRuleBooleanNegation, negation, new ErrorInformation[] {new ErrorInformation(negation)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRuleBooleanNegation(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanNegation negation) throws RuleFailedException {
    BooleanType boolType = null; // output parameter
    
    {
      BooleanType _createBooleanType = ExpressionsFactory.eINSTANCE.createBooleanType();
      boolType = _createBooleanType;
      /* G, 'expected' <- boolType |- negation.expression : boolType */
      Expression _expression = negation.getExpression();
      Result<Type> result = typeInternal(environmentComposition(
        G, environmentEntry("expected", boolType)
      ), _trace_, _expression);
      checkAssignableTo(result.getFirst(), BooleanType.class);
      boolType = (BooleanType) result.getFirst();
      
    }
    return new Result<Type>(boolType);
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AndOrExpression andOr) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRuleAndOr(G, _subtrace_, andOr);
      addToTrace(_trace_, ruleName("AndOr") + stringRepForEnv(G) + " |- " + stringRep(andOr) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleAndOr) {
      typeThrowException(ruleName("AndOr") + stringRepForEnv(G) + " |- " + stringRep(andOr) + " : " + "BooleanType",
      	ANDOR,
      	e_applyRuleAndOr, andOr, new ErrorInformation[] {new ErrorInformation(andOr)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRuleAndOr(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AndOrExpression andOr) throws RuleFailedException {
    BooleanType boolType = null; // output parameter
    
    {
      BooleanType _createBooleanType = ExpressionsFactory.eINSTANCE.createBooleanType();
      boolType = _createBooleanType;
      /* G, 'expected' <- boolType |- andOr.left : boolType */
      Expression _left = andOr.getLeft();
      Result<Type> result = typeInternal(environmentComposition(
        G, environmentEntry("expected", boolType)
      ), _trace_, _left);
      checkAssignableTo(result.getFirst(), BooleanType.class);
      boolType = (BooleanType) result.getFirst();
      
      /* G, 'expected' <- boolType |- andOr.right : boolType */
      Expression _right = andOr.getRight();
      Result<Type> result_1 = typeInternal(environmentComposition(
        G, environmentEntry("expected", boolType)
      ), _trace_, _right);
      checkAssignableTo(result_1.getFirst(), BooleanType.class);
      boolType = (BooleanType) result_1.getFirst();
      
    }
    return new Result<Type>(boolType);
  }
  
  @Override
  protected Result<Type> typeImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArithmeticSigned signed) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Type> _result_ = applyRuleArithmeticSigned(G, _subtrace_, signed);
      addToTrace(_trace_, ruleName("ArithmeticSigned") + stringRepForEnv(G) + " |- " + stringRep(signed) + " : " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleArithmeticSigned) {
      typeThrowException(ruleName("ArithmeticSigned") + stringRepForEnv(G) + " |- " + stringRep(signed) + " : " + "IntType",
      	ARITHMETICSIGNED,
      	e_applyRuleArithmeticSigned, signed, new ErrorInformation[] {new ErrorInformation(signed)});
      return null;
    }
  }
  
  @Override
  protected Result<Type> applyRuleArithmeticSigned(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArithmeticSigned signed) throws RuleFailedException {
    
    {
      IntType intType = ExpressionsFactory.eINSTANCE.createIntType();
      /* G, 'expected' <- intType |- signed.expression : intType */
      Expression _expression = signed.getExpression();
      Result<Type> result = typeInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _expression);
      checkAssignableTo(result.getFirst(), IntType.class);
      intType = (IntType) result.getFirst();
      
    }
    IntType _createIntType = ExpressionsFactory.eINSTANCE.createIntType();
    return new Result<Type>(_createIntType);
  }
  
  protected Result<Boolean> coerceImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral string, final IntType type) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Boolean> _result_ = applyRuleStringToInt(G, _subtrace_, string, type);
      addToTrace(_trace_, ruleName("StringToInt") + stringRepForEnv(G) + " |~ " + stringRep(string) + " |> " + stringRep(type));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleStringToInt) {
      coerceThrowException(ruleName("StringToInt") + stringRepForEnv(G) + " |~ " + stringRep(string) + " |> " + stringRep(type),
      	STRINGTOINT,
      	e_applyRuleStringToInt, string, type, new ErrorInformation[] {new ErrorInformation(string), new ErrorInformation(type)});
      return null;
    }
  }
  
  protected Result<Boolean> applyRuleStringToInt(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral string, final IntType type) throws RuleFailedException {
    
    String _value = string.getValue();
    Integer.parseInt(_value);
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> coerceImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral string, final BooleanType type) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Boolean> _result_ = applyRuleStringToBool(G, _subtrace_, string, type);
      addToTrace(_trace_, ruleName("StringToBool") + stringRepForEnv(G) + " |~ " + stringRep(string) + " |> " + stringRep(type));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleStringToBool) {
      coerceThrowException(ruleName("StringToBool") + stringRepForEnv(G) + " |~ " + stringRep(string) + " |> " + stringRep(type),
      	STRINGTOBOOL,
      	e_applyRuleStringToBool, string, type, new ErrorInformation[] {new ErrorInformation(string), new ErrorInformation(type)});
      return null;
    }
  }
  
  protected Result<Boolean> applyRuleStringToBool(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral string, final BooleanType type) throws RuleFailedException {
    
    boolean _or = false;
    String _value = string.getValue();
    boolean _equalsIgnoreCase = _value.equalsIgnoreCase("true");
    if (_equalsIgnoreCase) {
      _or = true;
    } else {
      String _value_1 = string.getValue();
      boolean _equalsIgnoreCase_1 = _value_1.equalsIgnoreCase("false");
      _or = (_equalsIgnoreCase || _equalsIgnoreCase_1);
    }
    /* string.value.equalsIgnoreCase("true") || string.value.equalsIgnoreCase("false") */
    if (!Boolean.valueOf(_or)) {
      sneakyThrowRuleFailedException("string.value.equalsIgnoreCase(\"true\") || string.value.equalsIgnoreCase(\"false\")");
    }
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> coerceImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NumberLiteral number, final IntType type) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Boolean> _result_ = applyRuleIntToInt(G, _subtrace_, number, type);
      addToTrace(_trace_, ruleName("IntToInt") + stringRepForEnv(G) + " |~ " + stringRep(number) + " |> " + stringRep(type));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleIntToInt) {
      coerceThrowException(ruleName("IntToInt") + stringRepForEnv(G) + " |~ " + stringRep(number) + " |> " + stringRep(type),
      	INTTOINT,
      	e_applyRuleIntToInt, number, type, new ErrorInformation[] {new ErrorInformation(number), new ErrorInformation(type)});
      return null;
    }
  }
  
  protected Result<Boolean> applyRuleIntToInt(final RuleEnvironment G, final RuleApplicationTrace _trace_, final NumberLiteral number, final IntType type) throws RuleFailedException {
    
    return new Result<Boolean>(true);
  }
  
  protected Result<Boolean> coerceImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanLiteral bool, final BooleanType type) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Boolean> _result_ = applyRuleBoolToBool(G, _subtrace_, bool, type);
      addToTrace(_trace_, ruleName("BoolToBool") + stringRepForEnv(G) + " |~ " + stringRep(bool) + " |> " + stringRep(type));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleBoolToBool) {
      coerceThrowException(ruleName("BoolToBool") + stringRepForEnv(G) + " |~ " + stringRep(bool) + " |> " + stringRep(type),
      	BOOLTOBOOL,
      	e_applyRuleBoolToBool, bool, type, new ErrorInformation[] {new ErrorInformation(bool), new ErrorInformation(type)});
      return null;
    }
  }
  
  protected Result<Boolean> applyRuleBoolToBool(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanLiteral bool, final BooleanType type) throws RuleFailedException {
    
    return new Result<Boolean>(true);
  }
  
  @Override
  protected Result<Object> interpretImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral string) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Object> _result_ = applyRuleInterpretStringLiteral(G, _subtrace_, string);
      addToTrace(_trace_, ruleName("InterpretStringLiteral") + stringRepForEnv(G) + " |- " + stringRep(string) + " ~> " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleInterpretStringLiteral) {
      interpretThrowException(ruleName("InterpretStringLiteral") + stringRepForEnv(G) + " |- " + stringRep(string) + " ~> " + "Object",
      	INTERPRETSTRINGLITERAL,
      	e_applyRuleInterpretStringLiteral, string, new ErrorInformation[] {new ErrorInformation(string)});
      return null;
    }
  }
  
  @Override
  protected Result<Object> applyRuleInterpretStringLiteral(final RuleEnvironment G, final RuleApplicationTrace _trace_, final StringLiteral string) throws RuleFailedException {
    Object result = null; // output parameter
    
    {
      Type expected = null;
      /* { expected = env(G, 'expected', IntType) result = Integer::parseInt(string.value) } or { expected = env(G, 'expected', BooleanType) result = Boolean::parseBoolean(string.value) } or result = string.value */
      try {
        Object _xblockexpression = null;
        {
          /* env(G, 'expected', IntType) */
          IntType _environmentaccess = environmentAccess(G, "expected", IntType.class);
          expected = _environmentaccess;
          String _value = string.getValue();
          int _parseInt = Integer.parseInt(_value);
          Object _result = result = Integer.valueOf(_parseInt);
          _xblockexpression = (_result);
        }
      } catch (Exception e) {
        /* { expected = env(G, 'expected', BooleanType) result = Boolean::parseBoolean(string.value) } or result = string.value */
        try {
          Object _xblockexpression_1 = null;
          {
            /* env(G, 'expected', BooleanType) */
            BooleanType _environmentaccess_1 = environmentAccess(G, "expected", BooleanType.class);
            expected = _environmentaccess_1;
            String _value_1 = string.getValue();
            boolean _parseBoolean = Boolean.parseBoolean(_value_1);
            Object _result_1 = result = Boolean.valueOf(_parseBoolean);
            _xblockexpression_1 = (_result_1);
          }
        } catch (Exception e_1) {
          String _value_2 = string.getValue();
          result = _value_2;
        }
      }
    }
    return new Result<Object>(result);
  }
  
  @Override
  protected Result<Object> interpretImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Minus plus) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Object> _result_ = applyRuleInterpretMinus(G, _subtrace_, plus);
      addToTrace(_trace_, ruleName("InterpretMinus") + stringRepForEnv(G) + " |- " + stringRep(plus) + " ~> " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleInterpretMinus) {
      interpretThrowException(ruleName("InterpretMinus") + stringRepForEnv(G) + " |- " + stringRep(plus) + " ~> " + "Integer",
      	INTERPRETMINUS,
      	e_applyRuleInterpretMinus, plus, new ErrorInformation[] {new ErrorInformation(plus)});
      return null;
    }
  }
  
  @Override
  protected Result<Object> applyRuleInterpretMinus(final RuleEnvironment G, final RuleApplicationTrace _trace_, final Minus plus) throws RuleFailedException {
    Integer result = null; // output parameter
    
    {
      IntType intType = ExpressionsFactory.eINSTANCE.createIntType();
      /* G, 'expected' <- intType |- plus.left ~> var Integer leftResult */
      Expression _left = plus.getLeft();
      Integer leftResult = null;
      Result<Object> result_1 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _left);
      checkAssignableTo(result_1.getFirst(), Integer.class);
      leftResult = (Integer) result_1.getFirst();
      
      /* G, 'expected' <- intType |- plus.right ~> var Integer rightResult */
      Expression _right = plus.getRight();
      Integer rightResult = null;
      Result<Object> result_2 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _right);
      checkAssignableTo(result_2.getFirst(), Integer.class);
      rightResult = (Integer) result_2.getFirst();
      
      int _intValue = leftResult.intValue();
      int _intValue_1 = rightResult.intValue();
      int _minus = (_intValue - _intValue_1);
      result = Integer.valueOf(_minus);
    }
    return new Result<Object>(result);
  }
  
  @Override
  protected Result<Object> interpretImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiOrDiv multiOrDiv) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Object> _result_ = applyRuleInterpretMultiOrDiv(G, _subtrace_, multiOrDiv);
      addToTrace(_trace_, ruleName("InterpretMultiOrDiv") + stringRepForEnv(G) + " |- " + stringRep(multiOrDiv) + " ~> " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleInterpretMultiOrDiv) {
      interpretThrowException(ruleName("InterpretMultiOrDiv") + stringRepForEnv(G) + " |- " + stringRep(multiOrDiv) + " ~> " + "Integer",
      	INTERPRETMULTIORDIV,
      	e_applyRuleInterpretMultiOrDiv, multiOrDiv, new ErrorInformation[] {new ErrorInformation(multiOrDiv)});
      return null;
    }
  }
  
  @Override
  protected Result<Object> applyRuleInterpretMultiOrDiv(final RuleEnvironment G, final RuleApplicationTrace _trace_, final MultiOrDiv multiOrDiv) throws RuleFailedException {
    Integer result = null; // output parameter
    
    {
      IntType intType = ExpressionsFactory.eINSTANCE.createIntType();
      /* G, 'expected' <- intType |- multiOrDiv.left ~> var Integer leftResult */
      Expression _left = multiOrDiv.getLeft();
      Integer leftResult = null;
      Result<Object> result_1 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _left);
      checkAssignableTo(result_1.getFirst(), Integer.class);
      leftResult = (Integer) result_1.getFirst();
      
      /* G, 'expected' <- intType |- multiOrDiv.right ~> var Integer rightResult */
      Expression _right = multiOrDiv.getRight();
      Integer rightResult = null;
      Result<Object> result_2 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _right);
      checkAssignableTo(result_2.getFirst(), Integer.class);
      rightResult = (Integer) result_2.getFirst();
      
      String _op = multiOrDiv.getOp();
      boolean _equals = ObjectExtensions.operator_equals(_op, "*");
      if (_equals) {
        int _intValue = leftResult.intValue();
        int _intValue_1 = rightResult.intValue();
        int _multiply = (_intValue * _intValue_1);
        result = Integer.valueOf(_multiply);
      } else {
        int _intValue_2 = leftResult.intValue();
        int _intValue_3 = rightResult.intValue();
        int _divide = (_intValue_2 / _intValue_3);
        result = Integer.valueOf(_divide);
      }
    }
    return new Result<Object>(result);
  }
  
  @Override
  protected Result<Object> interpretImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArithmeticSigned signed) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Object> _result_ = applyRuleInterpretArithmeticSigned(G, _subtrace_, signed);
      addToTrace(_trace_, ruleName("InterpretArithmeticSigned") + stringRepForEnv(G) + " |- " + stringRep(signed) + " ~> " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleInterpretArithmeticSigned) {
      interpretThrowException(ruleName("InterpretArithmeticSigned") + stringRepForEnv(G) + " |- " + stringRep(signed) + " ~> " + "Integer",
      	INTERPRETARITHMETICSIGNED,
      	e_applyRuleInterpretArithmeticSigned, signed, new ErrorInformation[] {new ErrorInformation(signed)});
      return null;
    }
  }
  
  @Override
  protected Result<Object> applyRuleInterpretArithmeticSigned(final RuleEnvironment G, final RuleApplicationTrace _trace_, final ArithmeticSigned signed) throws RuleFailedException {
    Integer result = null; // output parameter
    
    {
      IntType intType = ExpressionsFactory.eINSTANCE.createIntType();
      /* G, 'expected' <- intType |- signed.expression ~> var Integer expResult */
      Expression _expression = signed.getExpression();
      Integer expResult = null;
      Result<Object> result_1 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", intType)
      ), _trace_, _expression);
      checkAssignableTo(result_1.getFirst(), Integer.class);
      expResult = (Integer) result_1.getFirst();
      
      int _minus = (-expResult);
      result = Integer.valueOf(_minus);
    }
    return new Result<Object>(result);
  }
  
  @Override
  protected Result<Object> interpretImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AndOrExpression andOr) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Object> _result_ = applyRuleInterpretAndOr(G, _subtrace_, andOr);
      addToTrace(_trace_, ruleName("InterpretAndOr") + stringRepForEnv(G) + " |- " + stringRep(andOr) + " ~> " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleInterpretAndOr) {
      interpretThrowException(ruleName("InterpretAndOr") + stringRepForEnv(G) + " |- " + stringRep(andOr) + " ~> " + "Boolean",
      	INTERPRETANDOR,
      	e_applyRuleInterpretAndOr, andOr, new ErrorInformation[] {new ErrorInformation(andOr)});
      return null;
    }
  }
  
  @Override
  protected Result<Object> applyRuleInterpretAndOr(final RuleEnvironment G, final RuleApplicationTrace _trace_, final AndOrExpression andOr) throws RuleFailedException {
    Boolean result = null; // output parameter
    
    {
      BooleanType boolType = ExpressionsFactory.eINSTANCE.createBooleanType();
      /* G, 'expected' <- boolType |- andOr.left ~> var Boolean leftResult */
      Expression _left = andOr.getLeft();
      Boolean leftResult = null;
      Result<Object> result_1 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", boolType)
      ), _trace_, _left);
      checkAssignableTo(result_1.getFirst(), Boolean.class);
      leftResult = (Boolean) result_1.getFirst();
      
      /* G, 'expected' <- boolType |- andOr.right ~> var Boolean rightResult */
      Expression _right = andOr.getRight();
      Boolean rightResult = null;
      Result<Object> result_2 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", boolType)
      ), _trace_, _right);
      checkAssignableTo(result_2.getFirst(), Boolean.class);
      rightResult = (Boolean) result_2.getFirst();
      
      String _op = andOr.getOp();
      boolean _equals = ObjectExtensions.operator_equals(_op, "&&");
      if (_equals) {
        boolean _and = false;
        boolean _booleanValue = leftResult.booleanValue();
        if (!_booleanValue) {
          _and = false;
        } else {
          boolean _booleanValue_1 = rightResult.booleanValue();
          _and = (_booleanValue && _booleanValue_1);
        }
        result = Boolean.valueOf(_and);
      } else {
        boolean _or = false;
        boolean _booleanValue_2 = leftResult.booleanValue();
        if (_booleanValue_2) {
          _or = true;
        } else {
          boolean _booleanValue_3 = rightResult.booleanValue();
          _or = (_booleanValue_2 || _booleanValue_3);
        }
        result = Boolean.valueOf(_or);
      }
    }
    return new Result<Object>(result);
  }
  
  @Override
  protected Result<Object> interpretImpl(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanNegation neg) throws RuleFailedException {
    try {
      RuleApplicationTrace _subtrace_ = newTrace(_trace_);
      Result<Object> _result_ = applyRuleInterpretBooleanNegation(G, _subtrace_, neg);
      addToTrace(_trace_, ruleName("InterpretBooleanNegation") + stringRepForEnv(G) + " |- " + stringRep(neg) + " ~> " + stringRep(_result_.getFirst()));
      addAsSubtrace(_trace_, _subtrace_);
      return _result_;
    } catch (Exception e_applyRuleInterpretBooleanNegation) {
      interpretThrowException(ruleName("InterpretBooleanNegation") + stringRepForEnv(G) + " |- " + stringRep(neg) + " ~> " + "Boolean",
      	INTERPRETBOOLEANNEGATION,
      	e_applyRuleInterpretBooleanNegation, neg, new ErrorInformation[] {new ErrorInformation(neg)});
      return null;
    }
  }
  
  @Override
  protected Result<Object> applyRuleInterpretBooleanNegation(final RuleEnvironment G, final RuleApplicationTrace _trace_, final BooleanNegation neg) throws RuleFailedException {
    Boolean result = null; // output parameter
    
    {
      BooleanType boolType = ExpressionsFactory.eINSTANCE.createBooleanType();
      /* G, 'expected' <- boolType |- neg.expression ~> var Boolean expResult */
      Expression _expression = neg.getExpression();
      Boolean expResult = null;
      Result<Object> result_1 = interpretInternal(environmentComposition(
        G, environmentEntry("expected", boolType)
      ), _trace_, _expression);
      checkAssignableTo(result_1.getFirst(), Boolean.class);
      expResult = (Boolean) result_1.getFirst();
      
      boolean _not = (!expResult);
      result = Boolean.valueOf(_not);
    }
    return new Result<Object>(result);
  }
}
