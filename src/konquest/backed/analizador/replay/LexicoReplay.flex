package konquest.backed.analizador.replay;

import java_cup.runtime.Symbol;
import konquest.gui.DialogoErrores;

%%
%class LexicoReplay
%type java_cup.runtime.Symbol
%cup
%public
%cupdebug
%full
%line
%column
%char

/*Identificadores*/
L=[a-zA-Z]
D=[0-9]
S=[_$-]
espacio=[\t\r]+
salto=[\n]+

%{
    private DialogoErrores errores;
    
    public void setErrores(DialogoErrores errores) {
        this.errores = errores;
    }

    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    private Symbol symbol(int type){
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }
%}
%%

<YYINITIAL> {

    /* Espacios*/
    
    {salto} {/*Ignore*/}
    {espacio} {/*Ignore*/}
    ("â")* {/*Ignore*/}
    (" ")* {/*Ignore*/}

    /* Tokens */
    
    ( REPLAY ) {return new Symbol(sym.Replay, yychar, yyline, yytext());}
    ( ARCHIVO ) {return new Symbol(sym.Archivo, yychar, yyline, yytext());}
    ( TEXTO ) {return new Symbol(sym.Texto, yychar, yyline, yytext());}

    /* Palabra reservada nombre */
    ( nombre ) {return new Symbol(sym.Nombre, yychar, yyline, yytext());}

    /* Palabra reservada ubicacion */
    ( ubicacion ) {return new Symbol(sym.Ubicacion, yychar, yyline, yytext());}

    /* Operador Igual */
    ( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

    /* Operador Division */
    ( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}

    /* Operadores Relacional Mayor */
    ( ">" ) {return new Symbol(sym.Mayor, yychar, yyline, yytext());}

    /* Operadores Relacional Menor */
    ( "<" ) {return new Symbol(sym.Menor, yychar, yyline, yytext());}

    /* Expresiones */

    /* Identificador */

    ("\"")(({L})(({L}|{D}|{S})*))("\"") {return new Symbol(sym.Identificador, yychar, yyline, new String(yytext()));}
    ("\"")(("/")({L}|{S}|{D})+)+("\"") {return new Symbol(sym.PathCarpeta, yychar, yyline, new String(yytext()));}
    ("\"")((("./")|("/"))({L}|{S}|{D})+)+(".gpr\"") {return new Symbol(sym.PathCPK, yychar, yyline, new String(yytext()));}
    ("\"")((("./")|("/"))({L}|{S}|{D})+)+(".txt\"") {return new Symbol(sym.PathTXT, yychar, yyline, new String(yytext()));}

     /* Error */

    . {errores.agregarErroresTXTLexico(
    "Error Lexico: " + " token: << " + yytext() + " >> columna: " + (yycolumn + 1) +
    " linea: " + (yyline + 1));
    return new Symbol(sym.Error, yychar, yyline, new String(yytext()));} 
}