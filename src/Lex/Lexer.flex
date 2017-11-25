package Lex;
import java_cup.runtime.*;
%%
%class Lexer

%cup
%line
%column
%ignorecase

L = [a-zA-Záéíóú]
D = [0-9]
Palabra = {L}({L}_|{L}|{D}|{D}_)*({L}|{D})*
Sign = [+-]
Exp = [eE]
SIMBOLO = [_]
CARACTER = [@\"#$%&?'´¨`°\\¬_]

WHITE = [ \b\r\f\t\n]
%{
        public String Lexeme; 
        public Symbol token(int simbolo){
		Lexema lexema = new Lexema( yytext() );
		Interface.setError("La cadena '"+yytext()+"' es invalida, se encontro en la linea "+(yyline+1)+", y en la columna "+(yycolumn+1));
		return new Symbol(simbolo,yyline,yycolumn,lexema);
	}
	public Symbol token(int simbolo,String componenteLexico){
		Cup.vars++;
		Lexema lexema = new Lexema( yytext() );
                Interface.addLexemes(yytext()+","+componenteLexico); // esta linea para el video Himura para la tabla de simbolos
		return new Symbol(simbolo,yyline,yycolumn,lexema);
	}
	

%}
%%
{WHITE} {/*Ignore*/}
("\"".*"\"")
{Lexeme = yytext(); return token(sym.CAD,"CAD");}
("//".*)
{}

("=")
{return token(sym.SIGNO_DE_ASIGNACION,"SIGNO_DE_ASIGNACION");}
("-") 
{return token(sym.RESTA,"RESTA");}
(",") 
{return token(sym.COMA,"COMA");}
(";") 
{return token(sym.PUNTO_Y_COMA,"PUNTO_Y_COMA");}
("(") 
{return token(sym.PARENTESIS_ABRIR,"PARENTESIS_ABIR");}
(")") {return token(sym.PARENTESIS_CERRAR,"PARENTESIS_CERRAR");}
("{") {return token(sym.LLAVE_ABRIR,"LLAVE_ABRIR");}
("}") {return token(sym.LLAVE_CERRAR,"LLAVE_CERRAR");}

{D}+({Exp}("+"?){D}+)? { Lexeme=yytext(); return token(sym.NUMERO,"NUMERO");}
("@"{Palabra}) {Lexeme=yytext(); return token(sym.ID,"ID");}
("start") {Lexeme=yytext(); return token(sym.START,"START");}
("sync") {Lexeme=yytext(); return token(sym.SYNC,"SYNC");}
("on") {Lexeme=yytext(); return token(sym.ON,"ON");}
("off") {Lexeme=yytext(); return token(sym.OFF,"OFF");}
("loop") {Lexeme=yytext(); return token(sym.LOOP,"LOOP");}
("sleep") {Lexeme=yytext(); return token(sym.SLEEP,"SLEEP");}
("fly") {Lexeme=yytext(); return token(sym.FLY,"FLY");}
("end") {Lexeme=yytext(); return token(sym.END,"END");}
("up") {Lexeme=yytext(); return token(sym.UP,"UP");}
("down") {Lexeme=yytext(); return token(sym.DOWN,"DOWN");}
("left") {Lexeme=yytext(); return token(sym.LEFT,"LEFT");}
("right") {Lexeme=yytext(); return token(sym.RIGHT,"RIGHT");}
("forward") {Lexeme=yytext(); return token(sym.FORWARD,"FORWARD");}
("backward") {Lexeme=yytext(); return token(sym.BACKWARD,"BACKWARD");}
("rotate") {Lexeme=yytext(); return token(sym.ROTATE,"ROTATE");}
("level") {Lexeme=yytext(); return token(sym.LEVEL,"LEVEL");}
("plan") {Lexeme=yytext(); return token(sym.PLAN,"PLAN");}
("new") {Lexeme=yytext(); return token(sym.NEW,"NEW");}
("speed") {Lexeme=yytext(); return token(sym.SPEED,"SPEED");}
("stand") {Lexeme=yytext(); return token(sym.STAND,"STAND");}
("var") {Lexeme=yytext(); return token(sym.VAR,"VAR");}
{Palabra} {Lexeme=yytext(); return token(sym.PALABRA,"PALABRA");}
. {Lexeme=yytext(); return token(sym.ERROR);}