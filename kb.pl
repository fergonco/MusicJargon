project.

aim(project, 'Provide a high level language able to express a musical accompaniment in few lines without the need to write every note').

reference('https://www.midi.org/specifications/item/gm-level-1-sound-set').
reference('http://www.skytopia.com/project/articles/midi.html').
reference('http://www.personal.kent.edu/~sbirch/Music_Production/MP-II/MIDI/midi_file_format.htm').
reference('http://www.music-software-development.com/midi-tutorial.html').

functionality(errorPositions).
functionality(dynamics).
functionality(tempo).
functionality(tempoNote).
functionality(repeat).
functionality(comment).
functionality(voices).
functionality(niceLayout).
functionality(timeSignature).
functionality(rhythm).
functionality(literalTimeSignatures).
functionality(chordProgression).
functionality(noteSequence).
functionality(drumSequence).
functionality(instrumentBar).
functionality(noteLiteralInBar).
functionality(tiedNotes).
functionality(drumLiteralInBar).
functionality(silenceNotes).
functionality(silence).
desc(dynamics              , 'change in one line the dynamics of all or one instrument. Mute is a dynamic.').
desc(tempo                 , 'change the tempo for all instruments').
desc(tempoNote             , 'specify the length of the beat when setting the tempo').
desc(repeat                , 'jump to a label a number of times').
desc(voices                , 'choose the instrument to play among all midi instruments, including drums as special instrument. add a label to the instrument name').
desc(niceLayout            , 'layout the different lines nicely, having the "commands" like repeat, tempo, etc. in the first column').
desc(timeSignature         , 'define any time signature').
desc(rhythm                , 'define a rhythm on a time signature. define which components of the rythm have a higher dynamic').
desc(literalTimeSignatures , 'be able to specify the time signature as a literal in the rhythm definition').
desc(chordProgression      , 'define a chord progression').
desc(noteSequence          , 'define a note sequence, either based on a chord progression or specifying the notes explicitely. Define that a note takes two or more places in the sequence (tie)').
desc(drumSequence          , 'define a drums instrument sequence (bass drum, snare, hihat, etc.). It has to be flexible enough to be able to use any midi drum instrument').
desc(instrumentBar         , 'define what an instrument has to do in a specific bar (the sequence of notes to play): a combination of a note sequence with a rhythm. If the sequence has more notes than the rhythm, the remaining are ignored. If it has less, it is looped').
desc(noteLiteralInBar      , 'use note literals on the instrument bars to define the note sequence, for single notes and chords').
desc(tiedNotes             , 'make a note last two or more rhythm components on any type of note literal. Also the last one from a rhythm by specifying a tie on the first of the next bar').
desc(drumLiteralInBar      , 'use drum literals on the instrument bars').
desc(silenceNotes          , 'add silence notes in a sequence').
desc(silence               , 'define that an instrument does not play during a bar').

relate(dynamics      , silence            , 'empty bar means silence, empty dynamics bar means no dynamic change').
relate(instrumentBar , chordProgression).
relate(instrumentBar , noteSequence).
relate(instrumentBar , drumSequence).
relate(instrumentBar , rhythm).
relate(instrumentBar , silence).
relate(rhythm        , timeSignature).

language.
lexer.
parser.
model.
composedBy(language, lexer).
composedBy(language, parser).
composedBy(language, model).
package(lexer, 'org.fergonco.music.mjargon.lexer').
package(parser, 'org.fergonco.music.mjargon.parser').
package(model, 'org.fergonco.music.mjargon.model').

related(X,Y) :- relate(X, Y).
related(X,Y) :- relate(Y, X).

