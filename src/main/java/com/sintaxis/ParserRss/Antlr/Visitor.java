package com.sintaxis.ParserRss.Antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;

public class Visitor extends  ExpBaseVisitor<Object> {

    private ParseTreeListener listener;

    private StringBuilder sb = new StringBuilder();


    public StringBuilder visitPackageDeclaration(ParseTree ctx) {
        sb.append("Error en declaracion: ").append(ctx.getText());
        return sb;
    }


    @Override
    public Object visitChildren(RuleNode node) {
        // do whatever here
        return super.visitChildren(node);
    }

    @Override public Object visitEval(ExpParser.EvalContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitAdditionExp(ExpParser.AdditionExpContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitMultiplyExp(ExpParser.MultiplyExpContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitAtomExp(ExpParser.AtomExpContext ctx) { return visitChildren(ctx); }
}
