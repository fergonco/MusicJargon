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
	| repeat) EOL;

comment: COMMENT;

declaration: id=ID (variableDeclaration | labelDeclaration);

variableDeclaration: EQUALS value=expression;

expression: (
	  numericExpression
	| stringLiteral
	| referenceExpression
	| rhythmExpression
	| pitchSequenceExpression
	| drumSequenceExpression
);

numericExpression: numerator=NUMBER (SLASH denominator=NUMBER)?;

stringLiteral: STRING_LITERAL;

referenceExpression: id=ID ( onRhythm | parameters | );

onRhythm: ON rhythm=expression;

parameters: OPEN_PARENTHESIS parameterValues+=expression CLOSE_PARENTHESIS; 

rhythmExpression: value=RHYTHMEXPRESSION ON timeSignature=expression;

pitchSequenceExpression: (notes+=CHORD_LITERAL | notes+=UNDERSCORE)+;

drumSequenceExpression: instruments+=DRUM_INSTRUMENT+;

labelDeclaration: COLON VERTICAL_BAR*;

barline: (VERTICAL_BAR expressions+=expression)+ VERTICAL_BAR?;

tempo: TEMPO bpmOrNumerator=NUMBER (SLASH denominator=NUMBER EQUALS bpm=NUMBER)?;

voices: VOICES (VERTICAL_BAR instrumentNames+=ID STRING_LITERAL?)+ VERTICAL_BAR?;

dynamics: DYNAMICS (dynamicCodes+=VERTICAL_BAR dynamicCodes+=DYNAMIC?);

repeat: REPEAT labelId=ID times=NUMBER VERTICAL_BAR*;

NUMBER: '0'..'9';
RHYTHMEXPRESSION: '[' ('X' | 'x' | '.')+ ']';
SLASH: '/';
VERTICAL_BAR: '|';
COLON: ':';
EQUALS: '=';
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
OPEN_PARENTHESIS: '{';
CLOSE_PARENTHESIS: '}';
UNDERSCORE: '_';
COMMENT: '\'' ~( '\r' | '\n' )*;
ON: 'on';
TEMPO: 'tempo';
REPEAT: 'repeat';
VOICES: 'voices';
DYNAMICS: 'dynamics';
DYNAMIC: 'p' | 'pp' | 'ppp' | 'pppp' | 'mp' | 'mf' | 'f' | 'ff' | 'fff' | 'ffff';  
fragment HIHAT:'hihat';
fragment HH:'hh';
fragment HIHATOPEN:'hihatopen';
fragment HHO:'hho';
fragment HIHATPEDAL:'hihatpedal';
fragment HHP:'hhp';
fragment BASSDRUM:'bassdrum';
fragment BD:'bd';
fragment SNARE:'snare';
fragment SN:'sn';
fragment RIDE:'ride';
fragment RD:'rd';
fragment CRASH:'crash';
fragment CR:'cr';
fragment TOM1:'tom1';
fragment T1:'t1';
fragment TOM2:'tom2';
fragment T2:'t2';
fragment TOM3:'tom3';
fragment T3:'t3';
fragment TOM4:'tom4';
fragment T4:'t4';
fragment TOM5:'tom5';
fragment T5:'t5';
fragment TOM6:'tom6';
fragment T6:'t6';
DRUM_INSTRUMENT: (HIHAT|HH|HIHATOPEN|HHO|HIHATPEDAL|HHP|BASSDRUM|BD|SNARE|SN|RIDE|RD|CRASH|CR|TOM1|T1|TOM2|T2|TOM3|T3|TOM4|T4|TOM5|T5|TOM6|T6);
CHORD_LITERAL: '-' | 'A'..'G' ('A'..'G'♯♭)*;
STRING_LITERAL: '"' ~'"'* '"';
ID: 'a'..'z' ('a'..'z' | '_' | '0'..'9')*;

EOL: '\r'?'\n';

TS: ('\t'|' ')+ ->skip;