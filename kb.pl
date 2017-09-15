project.

aim(project, 'Provide a high level language able to express a musical accompaniment in few lines without the need to write every note').

reference('https://www.midi.org/specifications/item/gm-level-1-sound-set').
reference('http://www.skytopia.com/project/articles/midi.html').
reference('http://www.personal.kent.edu/~sbirch/Music_Production/MP-II/MIDI/midi_file_format.htm').
reference('http://www.music-software-development.com/midi-tutorial.html').

functionality(errorPositions).
functionality(dynamics).
functionality(tempo).
functionality(repeat).
functionality(comment).
functionality(voices).
functionality(niceLayout).
functionality(instrumentBar).
functionality(silence).
functionality(timeSignature).
functionality(rhythm).
functionality(chordProgression).
functionality(noteSequence).
functionality(drumSequence).
desc(dynamics         , 'change in one line the dynamics of all or one instrument. Mute is a dynamic.').
desc(tempo            , 'change the tempo for all instruments').
desc(repeat           , 'jump to a label a number of times').
desc(voices           , 'choose the instrument to play among all midi instruments, including drums as special instrument. add a label to the instrument name').
desc(niceLayout       , 'layout the different lines nicely, having the "commands" like repeat, tempo, etc. in the first column').
desc(instrumentBar    , 'define what an instrument has to do in a specific bar (the sequence of notes to play): a combination of a note sequence with a rhythm. If the sequence has more notes than the rhythm , the remaining are ignored. If it has less, it is looped').
desc(silence          , 'define that an instrument does not play during a bar').
desc(timeSignature    , 'define any time signature').
desc(rhythm           , 'define a rhythm on a time signature. define which components of the rythm have a higher dynamic').
desc(chordProgression , 'define a chord progression').
desc(noteSequence     , 'define a note sequence, either based on a chord progression or specifying the notes explicitely. Define that a note takes two or more places in the sequence (tie)').
desc(drumSequence     , 'define a drums instrument sequence (bass drum, snare, hihat, etc.). It has to be flexible enough to be able to use any midi drum instrument').

relate(dynamics      , silence            , '- is used for no dynamic change and silenced bar').
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

