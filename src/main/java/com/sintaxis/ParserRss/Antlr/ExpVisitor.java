// Generated from D:/Usuarios/rodri/Desktop/PROYECTO/ParserRss/ParserRss/src/main/java/com/sintaxis/ParserRss\Exp.g4 by ANTLR 4.9.2
package com.sintaxis.ParserRss.Antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpParser#eval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval(ExpParser.EvalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpParser#additionExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExp(ExpParser.AdditionExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpParser#multiplyExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyExp(ExpParser.MultiplyExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpParser#atomExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExp(ExpParser.AtomExpContext ctx);
}