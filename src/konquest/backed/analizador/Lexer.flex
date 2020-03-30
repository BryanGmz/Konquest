package konquest.backed.analizador;

import java_cup.runtime.Symbol;
import konquest.gui.*;

%%
%class Lexico
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
    
    /* Estructura */
    
    ( MAPA ) {return new Symbol(sym.Mapa, yychar, yyline, yytext());}
    ( PLANETAS ) {return new Symbol(sym.Planetas, yychar, yyline, yytext());}
    ( PLANETAS_NEUTRALES ) {return new Symbol(sym.PlanetasNeutrales, yychar, yyline, yytext());}
    ( JUGADORES ) {return new Symbol(sym.Jugadores, yychar, yyline, yytext());}

    /* Mapa */
    
    ( id ) {return new Symbol(sym.ID, yychar, yyline, yytext());}
    ( tamaÃ±o ) {return new Symbol(sym.TamañoS, yychar, yyline, yytext());}
    ( tamaño ) {return new Symbol(sym.Tamaño, yychar, yyline, yytext());}
    ( alAzar ) {return new Symbol(sym.AlAzar, yychar, yyline, yytext());}
    ( planetasNeutrales ) {return new Symbol(sym.PlanetasNeutralesMapa, yychar, yyline, yytext());}
    ( mapaCiego ) {return new Symbol(sym.MapaCiego, yychar, yyline, yytext());}
    ( acumular ) {return new Symbol(sym.Acumular, yychar, yyline, yytext());}
    ( finalizacion ) {return new Symbol(sym.Finalizacion, yychar, yyline, yytext());}
    ( NEUTRALES ) {return new Symbol(sym.Neutrales, yychar, yyline, yytext());}
    
    /* Neutrales */
    
    ( mostrarNaves ) {return new Symbol(sym.MostrarNaves, yychar, yyline, yytext());}
    ( mostrarEstadisticas ) {return new Symbol(sym.MostrarEstadisticas, yychar, yyline, yytext());}
    ( produccion ) {return new Symbol(sym.Produccion, yychar, yyline, yytext());}

    /* Planetas y Planetas Neutrales */
    
    ( nombre ) {return new Symbol(sym.Nombre, yychar, yyline, yytext());}
    ( naves ) {return new Symbol(sym.Naves, yychar, yyline, yytext());}
    ( porcentajeMuertes ) {return new Symbol(sym.PorcentajeMuertes, yychar, yyline, yytext());}
        
    /* Jugadores */

    ( planetas ) {return new Symbol(sym.PlanetasJugadores, yychar, yyline, yytext());}
    ( tipo ) {return new Symbol(sym.Tipo, yychar, yyline, yytext());}

    /* Otros */

    ( false ) {return new Symbol(sym.False, yychar, yyline, yytext());}
    ( true ) {return new Symbol(sym.True, yychar, yyline, yytext());}
    ( filas ) {return new Symbol(sym.Filas, yychar, yyline, yytext());}
    ( columnas ) {return new Symbol(sym.Columnas, yychar, yyline, yytext());}
    ( HUMANO ) {return new Symbol(sym.Humano, yychar, yyline, yytext());}
    ( DIFICIL ) {return new Symbol(sym.Dificil, yychar, yyline, yytext());}
    ( FACIL ) {return new Symbol(sym.Facil, yychar, yyline, yytext());}
    ( ":" ) {return new Symbol(sym.DosPuntos, yychar, yyline, yytext());}
    ( "," ) {return new Symbol(sym.Coma, yychar, yyline, yytext());}
    
    ( "{" ) {return new Symbol(sym.CorcheteA, yychar, yyline, yytext());}
    ( "}" ) {return new Symbol(sym.CorcheteC, yychar, yyline, yytext());}
    ( "[" ) {return new Symbol(sym.LlaveA, yychar, yyline, yytext());}
    ( "]" ) {return new Symbol(sym.LlaveC, yychar, yyline, yytext());}

    /* Expreciones */
    
    /* Nombre Planetas */

    ("\"")({L}{1, 3})("\"") {return new Symbol(sym.NombrePlanetas, yychar, yyline, new String(yytext()));}
    
    /* Nombre Jugadores */

    ("\"")({L})(({L}|{D}){1, 9})("\"") {return new Symbol(sym.NombreJugadores, yychar, yyline, new String(yytext()));}
    
    /* Numero */

    ({D})+ {return new Symbol(sym.Numero, yychar, yyline, new String(yytext()));}

    /* Decimal*/

    (0"."){D}+ {return new Symbol(sym.Decimal, yychar, yyline, new String(yytext()));}

    /* Identificador */

    ("\"")("_"|"$"|{L})(({L}|{D}|{S})*)("\"") {return new Symbol(sym.Identificador, yychar, yyline, new String(yytext()));}
    
    /* Error */

    . {errores.agregarErroresTXTLexico(
    "Error Lexico: " + " token: << " + yytext() + " >> columna: " + (yycolumn + 1) +
    " linea: " + (yyline + 1));
    //return new Symbol(sym.Error, yychar, yyline, new String(yytext()));
    } 
}