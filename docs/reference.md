---
layout: default
title: Music Jargon
---

# Reference

MusicJargon separates the different aspects of music and allows to transform and combine them to produce something we can hear. In a music staff, each note has a pitch, a strength and a duration which is defined by one or more complex symbols in a pentagram. In MusicJargon these concepts are independent and can be defined in one place and be reused on different places, combined differently, etc. For example it is possible to define a rhythm that will be reused in two parts of a song with a different sequence of notes.

More concretely, MusicJargon has the following data types:

* Aural: something we can hear. Basically it is a combination of a rhythm and a sequence of notes or drum instruments.
* Rhythms.
* Pitched sequences: notes like C#, but also chords.
* Drum sequences: drum instrument names like: hihat, bassdrum, snare, etc.
* Numbers
* Fractions: For time signatures 12/8 and note durations 1/8.
* Text

## Numbers, fractions and text

Text is anything between single quotes, "for example this".

Numbers are just integers, like 1, 2, 4.

And fractions are two numbers separated by a slash, like 4/4 or 1/8.

## Rhythms

Rhythms define note duration and a dynamic variation over the base dynamics. They are defined this way:

    [<rhythm_literal>] on <time_signature>

or this way:

    [<rhythm_literal>] with <note_length>

*rhythm_literal* is a sequence of *X* *x* and *.*, with these meanings:

* *.*: no beat
* *x*: normal note
* *X*: slightly louder note

*time_signature* and *note_length* are fraction expressions. In the case of *note_length* the fraction represents the length of each character. So:

    [XxXxXxXx] with 1/8

Would be 8 eighth notes, forming a typical 4/4 time signature.

If defined with a time signature expression the length of the character is calculated in order to match the given time signature. For example, in this rhythm each *X* represents a quarter note:

    [XXXX] on 4/4

While in this one each character represents a eighth note:

    [XxXxXxXx] on 4/4

## Pitched sequences

Pitched sequences are a sequence of notes, these notes can be single notes or chords and the sequence can mix these. Notes are separated by spaces and the syntax for an individual note is:

    <note-name><accidental>?<octave>?

where *note-name* is one of CDEFGAB uppercase letters, accidental is, optional, either the # or the b character and octave is also optional and defines in which octave to play the note.

By default, if there is no accidental, as expected, the pitch of the note is not modified.

The default octave is the last octave specified. Thus, in this sequence, all notes are in the third octave:

    A3 B C D E

For the first note, the default octave is the fourth so this is middle C:

    C

Silences are expressed as -. For example, the next expression means A, silence, C:

    A - C

Underscore repeats the previous note "tying" them. The next sequence means A, still A, C:

    A _ C

## Drum sequences

Drum sequences have the same structure as pitched sequences with the only difference that they use drums instruments instead of note names:

* hihat
* hh
* hihatopen
* hho
* hihatpedal
* hhp
* bassdrum
* bd
* snare
* sn
* ride
* rd
* crash
* cr
* tom1
* t1
* tom2
* t2
* tom3
* t3
* tom4
* t4
* tom5
* t5
* tom6
* t6

For example:

    hihat hihatopen hihat hihatopen hihat hihatopen hihat hihatopen 

## Aural

Sequences and rhythms are combined on aural expressions, which have this syntax:

    <sequence> on <rhythm>

Where *sequence* is a pitched or drum sequence expression and *rhythm* is a rhythm expression.

In this expression each component of the rhythm is matched with the component in the sequence that has the same place in order to produce a note of the known length, pitch and dynamics.

For example:

    hihat hihatopen hihat hihatopen hihat hihatopen hihat hihatopen on [XxXxXxXx]

Or:

    C D E F G A B on [xxxxxxx.]

If the sequence is shorter than the rhythm it is rolled over, which is very useful for drum sequences. For example, the previous drum aural expression can be defined so:

    hihat hihatopen on [XxXxXxXx]

When the sequence is over after the second eighth note, it will start again with another two hihat and open hihat eighth notes until the rhythm is filled.

## Named expressions

MusicJargon allows to associate a name to any expression. The syntax is:

    <name> = <expression>

Where *name* is the name given to the expression and *expression* is the expression we want to associate with the name.

Once an expression has a name it can be reused in other expressions. For example, let *beat* be defined as:

    beat = [XXXX] on 4/4

We could have an aural expression so:

    hihat on beat

This is quite useful to define rhythms and sequences in one place and not to type them over and over.

## Functions

MusicJargon allows the usage of functions, with this syntax:

    <functionName>(<params>)

where *functionName* is the name of the function and *params* is a comma separated list of expressions that are passed as parameters to the function.

They take any number of parameters as input and produce a single result. The types of the parameters and results can be any of the types supported by MusicJargon. On example of function is the *transpose8* one, which transposes the notes of a sequence one or more octaves up or down:

    transpose8(C5 D B A, 1)

See at the end of this reference for a [list of available functions](#list-of-functions).

### Sequence accessors

It is possible to access parts of sequences using the *{}* accessor operator:

    <sequence_expression>(<startIndex>[:[<endIndex>]])

If only *startIndex* is defined, the expression returns the zero-based index-th note in the sequence. If *endIndex* is defined it returns the notes between *startIndex* and *endIndex*, both included. And if the colon appears, but no *endIndex* (e.g.: *seq(1:)*) it returns the notes from *startIndex* until the end of the sequence.

This is valid for both pitched and drum sequences:

    eighths          = [X.x.X.x.X.x.] on 3/4
    mainRhythm       = [x.xx..xx..x.] on 3/4
    polyRhythm       = [x..x..x..x..] on 3/4
    filledPolyRhythm = [xxXxxXxxXxxX] on 3/4
    bassdrumSn       = bd sn bd bd bd sn
    sn2bd            = bd bd sn

    voices | drums "hihat"                | drums "rhythm"
           | concat(crash, hh) on eighths | snare + bassdrumSn(1:) on mainRhythm
           | hh                on eighths | bassdrumSn(1:3)        on mainRhythm
           | hh                on eighths | sn2bd                  on filledPolyRhythm
           | hh                on eighths | sn2bd                  on polyRhythm

## Songlines

As opposed to pentagrams, MusicJargon defines a song vertically by means of *songline* statements. These statements define the instruments that are used, the bars for each instrument, set the tempo, define the base dynamic, etc. 

On the same line, the data for different instruments is separated with the *\|* character.

## Voices

The voices *songline* defines the different instruments that will play the song. It is defined so:

    voices | <instrument_name> "comment" | <instrument_name> "comment" | ...

Where *instrument_name* is the name of one of the [available instruments](#instruments) and *comment* is any text that helps the author to identify the instrument.

Note that some instruments have two voices and they have to be added explicitly, for example the two hands for piano:

    voices | piano "left hand" | piano "right hand"

Note also that the vertical bar *\|* separates both instruments.

## Bar

Bars come vertically after one another under the corresponding instrument:

    voices | piano "left hand" | piano "right hand"
           | <bar>             | <bar>
           | <bar>             | <bar>
           | <bar>             | <bar>
           | ...               | ...

Note that formatting is not important but instead the number of \| characters is taken into account to know to which voice belongs each bar. An editor with block editing capabilities or aligning capabilities is recommended.

A bar is any aural expression.

## Voices not playing a bar

Just leave the bar empty. Empty bars make the corresponding voice mute during the bar.

    voices | piano "left hand" | piano "right hand"
           | <bar>             | <bar>
           |                   | <bar>
           | <bar>             |       
           | ...               | ...

## Comments

At any point it is possible to start a line with a single quote in order to make a comment that is not processed by MusicJargon.


    voices | piano "left hand" | piano "right hand"
           | <bar>             | <bar>
           | <bar>             | <bar>
           | <bar>             | <bar>
           | <bar>             | <bar>
    ' **********  chorus !!!  ************
           | <bar>             | <bar>
           | <bar>             | <bar>
           | <bar>             | <bar>
           | ...               | ...

## Tempo

Tempo lines can appear at any place in the bar section. Its syntax is:

    tempo [<note_fraction>=] <bpm> | ...

where *bpm* is the beats per minute and *note_fraction* is the fraction that identifies the note length. It is 1/4 (a quarter note) by default.

Note that in order to define the tempo for a doted quarter note you have to add 1/4 and 1/8 which gives 3/8:

    tempo 3/8=60

The tempo declaration can have any number of | characters afterwards, with no meaning at all but allowing for nice formatting and alignment.

## Dynamics

Dynamics change the base dynamics. Its syntax is:

    dynamics | <dynamic> | <dynamic> | ...

where *dynamic* is one of:

* pppp
* ppp
* pp
* p
* mp
* mf
* f
* ff
* fff
* ffff
* empty, for no dynamic change

For example, in the following section the left hand and right hand of piano are balanced and afterwards the right hand is a bit louder while the right hand does not change:

    voices   | piano "left hand" | piano "right hand"
    tempo 70 |                   |
    dynamics | mf                | mf
             | CEG               | C E G on myRhythm
    dynamics |                   | f
             | CEG               | C E G on myRhythm
 
## Repetitions

Labels define anchor points with the following syntax:

    <label_name>: | | ...

The label declaration can have any number of | characters afterwards, with no meaning at all but allowing for nice formatting and alignment.

Then the *repeat* keyword can be used to jump to a label:

    repeat <label_name> <times>

Where *label_name* is the label to jump to and times is the number of times it has to jump.










And when there is such a chord sequence it is possible to define sequences of single notes based on the notes of a chord of the sequence:

    ts      = time signature 6/8
    r       = rhythm [XxxXxx] on ts
    whole   = rhythm [x] on ts
    chords  = sequence C4EG EGB FAC5 E4G#B

    voices    | fingered_bass                 | overdrive_guitar
    dynamics  | f                             | p
    tempo 120 |                               |
              | 1 2 3 2 3 1 of chords(0) on r | chords(0) on r
              | 1 2 3 2 3 1 of chords(1) on r | chords(1) on r
              | 1 2 3 2 3 1 of chords(2) on r | chords(2) on r
              | 1 2 3 2 3 1 of chords(3) on r | chords(3) on r



## List of functions

### arpeggio

Generates a sequence of the arpeggiated notes of a chord. It receives a chord parameter and an optional string with the 1-based indices of the notes in the chord to use:

    beat         = rhythm [xxxx] on 4/4
    subbeat      = rhythm [XxXxXxXx] on 4/4
    chords       = sequence D4GB EAC5 E4GB CEG
    voices    | piano "right hand"                            | drums "right hand"                      
              | arpeggio(chords{0}, "12323")       on subbeat | crash + hihat on beat                 
              | arpeggio(chords{1})                on subbeat | hihat on beat                 

### transpose8

Transposes the input sequence a number of octaves. It receives a note sequence and a number as a parameter:

    beat         = rhythm [xxxx] on 4/4
    subbeat      = rhythm [XxXxXxXx] on 4/4
    chords       = sequence D4GB EAC5 E4GB CEG
    voices    | piano "right hand"                                    | drums "right hand"                      
              | transpose8(arpeggio(chords{0}, "12323"), 1 on subbeat | crash + hihat on beat                 
              | transpose8(arpeggio(chords{1})        ), 1 on subbeat | hihat on beat                 

### chordNotes

Takes a chord and a list of numbers that are interpreted as 1-based indices on the notes of the chord and returns these as a pitched sequence:

    ' C C E E G G
    chordNotes(CEG, 1, 1, 2, 2, 3, 3)

### concat

Returns a sequence resulting of the concatenation of two or more sequences. When matching a rhythm, if the sequence is to short, only the last sequence will be rolled over.

    ' One crash and 7 hihats
    concat(crash, hihat) on [XxXxXxXx]

## Instruments

A complete list of the instruments that can be used in *voices* section:

* drums
* piano
* honkytonk
* electric_piano
* epiano
* harpsichord
* clavinet
* celesta
* glockenspiel
* music_box
* vibraphone
* marimba
* xylophone
* tubular_bells
* electric_organ
* jazz_organ
* organ
* pipe_organ
* reed_organ
* accordion
* harmonica
* bandneon
* acoustic_guitar
* steel_guitar
* jazz_guitar
* electric_guitar
* muted_guitar
* overdrive_guitar
* distorted_guitar
* guitar_harmonics
* acoustic_bass
* fingered_bass
* picked_bass
* fretless_bass
* slap_bass
* synth_bass
* violin
* viola
* cello
* contrabass
* tremolo_strings
* pizzicato_strings
* harp
* timpani
* strings
* slow_strings
* synth_strings
* ahhs
* oohs
* synvox
* orchestra_hit
* trumpet
* trombone
* tuba
* muted_trumpet
* french_horn
* brass
* synth_brass
* soprano_saxophone
* alto_saxophone
* tenor_saxophone
* baritone_saxophone
* oboe
* english_horn
* bassoon
* clarinet
* piccolo
* flute
* recorder
* pan_flute
* bottle_blow
* shakuhachi
* whistle
* ocarina
* square_wave
* saw_wave
* synth_calliope
* chiffer_lead
* charang
* solo_vox
* fantasia
* warm_pad
* polysynth
* space_voice
* bowed_glass
* metal_pad
* halo_pad
* sweep_pad
* ice_rain
* soundtrack
* crystal
* atmosphere
* brightness
* goblin
* echo_drops
* star_theme
* sitar
* banjo
* shamisen
* koto
* kalimba
* bagpipes
* fiddle
* shannai
* tinkle_bell
* agogo
* steel_drums
* woodblocks
* taiko
* synth_drum
* tom_toms
* fretnoise
* breathnoise
* seashore
* bird
* telephone
* helicopter
* applause

