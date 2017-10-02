grammar MJargon;

@header {
package org.fergonco.music.mjargon.antlr;
}

script: lines+=scriptLine* EOF;

scriptLine:	(
	comment
	| declaration
	| barline
	| tempo
	| voices
	| dynamics
	| repeat
	| ) EOL;

comment: COMMENT;

declaration: id=ID (variableDeclaration | labelDeclaration);

variableDeclaration: EQUALS value=expression;

expression: left=leftExpression (OPEN_BRACE index=NUMBER (colon=COLON toIndex=NUMBER?)? CLOSE_BRACE)? (ON rhythm=expression)?;

leftExpression: (
	  numericExpression
	| stringLiteral
	| referenceExpression
	| rhythmExpression
	| pitchSequenceExpression
	| drumSequenceExpression
);

numericExpression: numerator=NUMBER (SLASH denominator=NUMBER)?;

stringLiteral: text=STRING_LITERAL;

referenceExpression: id=ID (OPEN_PARENTHESIS parameterValues+=expression (COMA parameterValues+=expression)* CLOSE_PARENTHESIS)?;

rhythmExpression: value=RHYTHMEXPRESSION (ON timeSignature=expression | WITH beatDuration=expression);

pitchSequenceExpression: (notes+=CHORD_LITERAL | notes+=UNDERSCORE)+;

drumSequenceExpression: (
    instruments+=HIHAT|
    instruments+=HH|
    instruments+=HIHATOPEN|
    instruments+=HHO|
    instruments+=HIHATPEDAL|
    instruments+=HHP|
    instruments+=BASSDRUM|
    instruments+=BD|
    instruments+=SNARE|
    instruments+=SN|
    instruments+=RIDE|
    instruments+=RD|
    instruments+=CRASH|
    instruments+=CR|
    instruments+=TOM1|
    instruments+=T1|
    instruments+=TOM2|
    instruments+=T2|
    instruments+=TOM3|
    instruments+=T3|
    instruments+=TOM4|
    instruments+=T4|
    instruments+=TOM5|
    instruments+=T5|
    instruments+=TOM6|
    instruments+=T6
)+;

labelDeclaration: COLON VERTICAL_BAR*;

barline: (VERTICAL_BAR {$expressions.add(null);} expressions+=expression?)+ {$expressions.add(null);};

tempo: TEMPO bpmOrNumerator=NUMBER (SLASH denominator=NUMBER EQUALS bpm=NUMBER)? VERTICAL_BAR*;

voices: VOICES (VERTICAL_BAR instrumentNames+=ID STRING_LITERAL?)+ VERTICAL_BAR?;

dynamics: DYNAMICS (VERTICAL_BAR {$dynamicCodes.add(null);} dynamicCodes+=DYNAMIC?)*;

repeat: REPEAT labelId=ID times=NUMBER VERTICAL_BAR*;

NUMBER: '0'..'9'+;
RHYTHMEXPRESSION: '[' ('X' | 'x' | '.')+ ']';
SLASH: '/';
VERTICAL_BAR: '|';
COLON: ':';
COMA: ',';
EQUALS: '=';
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
UNDERSCORE: '_';
COMMENT: '\'' ~( '\r' | '\n' )*;
ON: 'on';
WITH: 'with';
TEMPO: 'tempo';
REPEAT: 'repeat';
VOICES: 'voices';
DYNAMICS: 'dynamics';
DYNAMIC: 'p' | 'pp' | 'ppp' | 'pppp' | 'mp' | 'mf' | 'f' | 'ff' | 'fff' | 'ffff';  
HIHAT:'hihat';
HH:'hh';
HIHATOPEN:'hihatopen';
HHO:'hho';
HIHATPEDAL:'hihatpedal';
HHP:'hhp';
BASSDRUM:'bassdrum';
BD:'bd';
SNARE:'snare';
SN:'sn';
RIDE:'ride';
RD:'rd';
CRASH:'crash';
CR:'cr';
TOM1:'tom1';
T1:'t1';
TOM2:'tom2';
T2:'t2';
TOM3:'tom3';
T3:'t3';
TOM4:'tom4';
T4:'t4';
TOM5:'tom5';
T5:'t5';
TOM6:'tom6';
T6:'t6';
CHORD_LITERAL: '-' | 'A'..'G' ('A'..'G' | '0'..'9' | '♯' | '♭')*;
STRING_LITERAL: '"' ~'"'* '"';
ID: 'a'..'z' ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;

EOL: '\r'?'\n';

TS: ('\t'|' ')+ ->skip;