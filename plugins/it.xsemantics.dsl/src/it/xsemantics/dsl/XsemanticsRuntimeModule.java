/*
 * generated by Xtext
 */
package it.xsemantics.dsl;

import it.xsemantics.dsl.generator.XsemanticsOutputConfigurationProvider;
import it.xsemantics.dsl.generator.XsemanticsXbaseCompiler;
import it.xsemantics.dsl.typing.XsemanticsTypeComputer;
import it.xsemantics.dsl.validation.XsemanticsXExpressionHelper;

import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputer;
import org.eclipse.xtext.xbase.util.XExpressionHelper;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class XsemanticsRuntimeModule extends it.xsemantics.dsl.AbstractXsemanticsRuntimeModule {

	public Class<? extends ITypeComputer> bindITypeComputer() {
		return XsemanticsTypeComputer.class;
	}

	public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
		return XsemanticsOutputConfigurationProvider.class;
	}

	public Class<? extends OutputConfigurationProvider> bindOutputConfigurationProvider() {
		return XsemanticsOutputConfigurationProvider.class;
	}
	
	public Class<? extends XExpressionHelper> bindXExpressionHelper() {
		return XsemanticsXExpressionHelper.class;
	}

	public Class<? extends XbaseCompiler> bindXbaseCompiler() {
		return XsemanticsXbaseCompiler.class;
	}

}
