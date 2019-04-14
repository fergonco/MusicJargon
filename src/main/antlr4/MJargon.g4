grammar MJargon;

@header {
package org.fergonco.music.mjargon.antlr;
}

script: lines+=scriptLine* structure=play? EOF;

play: PLAY (parts+=ID | EOL)+ ;

scriptLine:	(
	defaultDeclaration
	| declaration
	| labelEnd
	| voices
	| repeat
	| labelableLine
	| ) comment? (EOL|EOF);

labelEnd: END labelId=ID;

labelableLine: (
	barline
	| dynamics
);
comment: COMMENT;

defaultDeclaration: DEFAULT TIME SIGNATURE defaultTimeSignature=numericExpression;

declaration: id=ID (variableDeclaration | labelDeclaration);

variableDeclaration: EQUALS value=expression;

expression: left=leftExpression (OPEN_BRACE index=NUMBER (colon=COLON toIndex=NUMBER?)? CLOSE_BRACE)? (ON rhythm=leftExpression)?;

leftExpression: (
	  numericExpression
	| stringLiteral
	| referenceExpression
	| rhythmExpression
	| noteSequenceExpression
	| auralExpression
);

numericExpression: minus=SILENCE? numerator=NUMBER (SLASH denominator=NUMBER)?;

stringLiteral: text=STRING_LITERAL;

referenceExpression: id=ID (OPEN_PARENTHESIS parameterValues+=expression (COMA parameterValues+=expression)* CLOSE_PARENTHESIS)?;

rhythmExpression: 
	(value=RHYTHMEXPRESSION timeDefinition=onTimeSignature?)
	| (full=FULL ON timeSignature=expression);

onTimeSignature: (
	ON timeSignature=expression
	| WITH beatDuration=expression
);

noteSequenceExpression: literals+=noteLiteral+;

noteLiteral:
	times=NUMBER? (
		silence=SILENCE | 
		underscore=UNDERSCORE | 
		(
			note=(
				EXPLICIT_CHORD |
				CHORD_NAME |
				HIHAT|
				HH|
				HIHATOPEN|
				HHO|
				HIHATPEDAL|
				HHP|
				BASSDRUM|
				BD|
				SNARE|
				SN|
				RIDE|
				RD|
				RIDEBELL|
				RDB|
				CRASH|
				CR|
				TOM1|
				T1|
				TOM2|
				T2|
				TOM3|
				T3|
				TOM4|
				T4|
				TOM5|
				T5|
				TOM6|
				T6
			)
			accent=ACCENT?
		)
	);

auralExpression: LESS_THAN noteSequence=noteSequenceExpression GREATER_THAN timeSignature=onTimeSignature?;

labelDeclaration: COLON labelableLine;

barline: tempoDeclaration=tempo? (VERTICAL_BAR {$expressions.add(null);} expressions+=expressionOrReference?)+ {$expressions.add(null);};

expressionOrReference: 
	same=SAME 
	| LIKE label=ID (shiftSign=(PLUS | SILENCE) shiftAmount=NUMBER)? 
	| plus=PLUS
	| expr=expression;

tempo: TEMPO bpmOrNumerator=NUMBER (SLASH denominator=NUMBER EQUALS bpm=NUMBER)?;

voices: VOICES (VERTICAL_BAR instrumentNames+=ID STRING_LITERAL?)+ VERTICAL_BAR?;

dynamics: DYNAMICS (VERTICAL_BAR {$dynamicCodes.add(null);} dynamicCodes+=DYNAMIC?)*;

repeat: REPEAT labelId=ID times=leftExpression VERTICAL_BAR*;

NUMBER: '0'..'9'+;
RHYTHMEXPRESSION: '[' ('X' | 'x' | '.')+ ']';
LESS_THAN: '<';
GREATER_THAN: '>';
SLASH: '/';
VERTICAL_BAR: '|';
COLON: ':';
COMA: ',';
ACCENT: '!';
EQUALS: '=';
SAME: '%';
LIKE: 'like';
PLUS: '+';
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
UNDERSCORE: '_';
COMMENT: '\'' ~( '\r' | '\n' )*;
ON: 'on';
WITH: 'with';
TEMPO: 'tempo';
FULL: 'full';
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
RIDEBELL:'ridebell';
RDB:'rdb';
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
SILENCE: '-';
DEFAULT: 'default';
TIME: 'time';
SIGNATURE: 'signature';
fragment NOTE: 'A'..'G' ('#' | 'b')? ('0'..'9')?;
EXPLICIT_CHORD: NOTE+;
CHORD_NAME: NOTE ('maj' | 'min' | 'aug' | 'dim') ('/' NOTE)?;
STRING_LITERAL: '"' ~'"'* '"';
END: 'end';
PLAY: 'play';
ID: 'a'..'z' ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;

EOL: '\r'?'\n';

TS: ('\t'|' ')+ ->skip;