---
layout: default
title: Music Jargon
---

# Reference

MusicJargon works by splitting the concept of rhythm and the concept of pitch. In a music staff, each note has a pitch, a strength and a duration which is defined by one or more complex symbols in a pentagram. In MusicJargon there is no pentagram, just a text file with ascii (well, not only) characters. In order to specify "play a C# on the 7th octave very loud and lasting a quarter note" it is necessary to specify things in several places.

## Comments

At any point it is possible to start a line with a single quote in order to make a comment that is not processed by MusicJargon.

## Time signatures

Time signature expressions follow this notation:

	<number>/<number>

They can be used as literals when defining rhythms or declaring a named time_signature:

	<name> = time signature <time_signature_literal>

## Rhythms

Rhythms define note duration and a dynamic variation (the base dynamic is defined in the bar section). They are defined this way:

	<name> = rhythm [<rhythm_literal>] on <time_signature>

*rhythm_literal* is a sequence of *X* *x* and *.*, with these meanings:

* *.*: no beat
* *x*: normal note
* *X*: slightly higher note

and *time_signature* is a time signature expression.

Note that the length of the notes depends on the time signature and it is automatically calculated. For example, in this rhythm:

	quarterRhythm = rhythm [XXXX] on 4/4

Each *X* represents a quarter note. In this one:

	eighthRhythm = rhythm [XxXxXxXx] on 4/4

each character represents a eighth note.


So, we could define a We will rock you rhythm like this:

	wwry = rhythm [xxX.xxX.] on 4/4

## Bar section

Once there is a rhythm, one can place notes over it. We enter the bars section, which defines instruments, matches notes with rhythms, sets tempo, changes dynamics, etc. As opposed to pentagrams, MusicJargon lays the song vertically and the *|* character separates the different voices that sound at the same time.

## Voices

Voices are the first line on the bar section and define the instruments:

	voices | <instrument_name> "comment" | <instrument_name> "comment" | ...

See at the end for a list of possible instruments.

Note that some instruments have two voices and they have to be added explicitly, for example the two hands for piano:

	voices | piano "left hand" | piano "right hand"

Note also that the vertical bar *|* separates both instruments

## Bar

Bars come vertically after one another under the corresponding instrument:

	voices | piano "left hand" | piano "right hand"
	       | <bar>             | <bar>
	       | <bar>             | <bar>
	       | <bar>             | <bar>
	       | ...               | ...

Note that formatting is not important but instead the number of | characters is taken into account to know to which voice belongs each bar. An editor with block editing capabilities or aligning capabilities is recommended.

The syntax of individual bars is the following:

	<note_sequence> on <rhythm_name>

Where *note_sequence* defines a sequence of notes, which can be pitched notes (C, D, E♯, etc.) or drum "notes" (hihat, snare, etc.), and *rhythm_name* points to a previously defined rhythm.

The logic is the same in both pitched and drum sequences: match each *X* or *x* component from the rhythm with the note that takes the same place in the sequence. For example:
	
	myRhythm = rhythm [XxX.X...] on 4/4
	voices | piano
	       | A B C D on myRythm

would play a *A* eighth note, a B eighth note, a C quarter note and a D half note.

If the sequence is shorter than the rhythm it is rolled over, which is very useful for drum sequences. For example, this would play an accented hihat (hh) eighth note, followed by a normal open hihat eighth note and, as the sequence is over, it will start again with another two hihat and open hihat eighth notes until the rhythm is filled:

	theBeat = rhythm [XxXxXxXx] on 4/4
	voices | drums
	       | hh hho on theBeat

### Pitched sequences

Pitched sequences are a sequence of notes, these notes can be single notes or chords and the sequence can mix these. Note sequences are separated by spaces and the syntax for an individual note is:

	<note-name><accidental>?<octave>?

where *note-name* is one of CDEFGAB uppercase letters, accidental is, optional, either the ♯ or the ♭ character and octave is also optional and defines in which octave to play the note.

By default, if there is no accidental, as expected, the pitch of the note is not modified.

The default octave is the last octave specified. Thus, in this sequence, all notes are in the third octave:

	A3 B C D E

For the first note, the default octave is the fourth and

	C

is middle C.

Silences are expressed as -. For example:

	beat = rhythm [xxxx] on 4/4
	voices    | piano "right hand" | piano "left hand"
	tempo 100 |                    |
			  |                    | - - - C4 on beat
			  | D4GB on whole      | B A G A  on beat
			  | EAC5 on whole      | A        on whole

Underscore will make the previous note be stretched on the corresponding rhythm component:

	beat = rhythm [xxxx] on 4/4
	voices    | piano "right hand" | piano "left hand"
	tempo 100 |                    |
			  |                    | - - - C4 on beat
			  | D4GB on whole      | B _ G A  on beat
			  | EAC5 on whole      | _        on whole

When a sequence is going to be repeated several times in the song, it is possible to give it a name and reuse the name in the bar section:

	beat = rhythm [xxxx] on 4/4
	seq  = sequence B _ G A
	voices    | piano "right hand" | piano "left hand"
	tempo 100 |                    |
			  |                    | - - - C4 on beat
			  | D4GB on whole      | seq      on beat
			  | D4GB on whole      | seq      on beat
			  | EAC5 on whole      | _        on whole

And when there is such a chord sequence it is possible to define sequences of single notes based on the notes of a chord of the sequence:

	ts      = time signature 6/8
	r       = rhythm [XxxXxx] on ts
	whole   = rhythm [x] on ts
	chords  = sequence C4EG EGB FAC5 E4G♯B

	voices    | fingered_bass                 | overdrive_guitar
	dynamics  | f                             | p
	tempo 120 |                               |
	          | 1 2 3 2 3 1 of chords(0) on r | chords(0) on r
	          | 1 2 3 2 3 1 of chords(1) on r | chords(1) on r
	          | 1 2 3 2 3 1 of chords(2) on r | chords(2) on r
	          | 1 2 3 2 3 1 of chords(3) on r | chords(3) on r

### Drum sequences

Drum sequences have the same behavior as pitched sequences with two differences.

First, instead of note names we have drums instruments:

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

And second, when declared, they use the *drum sequence* keywords:

	beat       = rhythm [xxxx] on 4/4
	sixteenths = rhythm [x...x...xX..X..X] on 4/4
	theRhythm  = drum sequence bd sn bd sn sn sn
	voices    | drums "right hand" | drums "rhythm"
	tempo 100 |                    |
	          | hh on beat         | theRhythm on sixteenths
	          | hh on beat         | theRhythm on sixteenths
	          | hh on beat         | theRhythm on sixteenths
	          | hh on beat         | theRhythm on sixteenths

### Sequence accesors

It is possible to access parts of named sequences from bars:

	<sequence_name>(<startIndex>[:[<endIndex>]])

If only *startIndex* is defined, the expression returns the zero-based index-th note in the sequence. If *endIndex* is defined it returns the notes between *startIndex* and *endIndex*, both included. And if the colon appears, but no *endIndex* (e.g.: *seq(1:)*) it returns the notes from *startIndex* until the end of the sequence.

This is valid for both pitched and drum sequences:

	ts = time signature 3/4

	eighths          = rhythm [X.x.X.x.X.x.] on ts
	mainRhythm       = rhythm [x.xx..xx..x.] on ts
	polyRhythm       = rhythm [x..x..x..x..] on ts
	filledPolyRhythm = rhythm [xxXxxXxxXxxX] on ts
	bassdrumSn       = drum sequence bd sn bd bd bd sn
	sn2bd            = drum sequence bd bd sn
	voices      | drums "hihat"         | drums "rhythm"
	dynamics    | mf                    | mf
	tempo 70    |                       |
	a:          |                       |
	            | crash + hh on eighths | snare + bassdrumSn(1:) on mainRhythm
	            | hh on eighths         | bassdrumSn(1:3)        on mainRhythm
	            | hh on eighths         | sn2bd                  on filledPolyRhythm
	            | hh on eighths         | sn2bd                  on polyRhythm
	repeat a 10 |                       |

### Sequence functions

Sequence functions can be used in bars. They take a number of parameters, that can be numbers, string literals or note sequences and produce a new note sequence.

#### arpeggio

Generates a sequence of the arpeggiated notes of a chord. It receives a chord parameter and an optional string with the 1-based indices of the notes in the chord to use:

	beat         = rhythm [xxxx] on 4/4
	subbeat      = rhythm [XxXxXxXx] on 4/4
	chords       = sequence D4GB EAC5 E4GB CEG
	voices    | piano "right hand"                            | drums "right hand"                      
			  | arpeggio(chords{0}, "12323")       on subbeat | crash + hihat on beat                 
			  | arpeggio(chords{1})                on subbeat | hihat on beat                 

#### transpose8

Transposes the input sequence a number of octaves. It receives a note sequence and a number as a parameter:

	beat         = rhythm [xxxx] on 4/4
	subbeat      = rhythm [XxXxXxXx] on 4/4
	chords       = sequence D4GB EAC5 E4GB CEG
	voices    | piano "right hand"                                    | drums "right hand"                      
			  | transpose8(arpeggio(chords{0}, "12323"), 1 on subbeat | crash + hihat on beat                 
			  | transpose8(arpeggio(chords{1})        ), 1 on subbeat | hihat on beat                 


## Voices not playing a bar

Just leave the bar empty. Empty bars make the corresponding voice mute during the bar.

## Tempo

Tempo lines can appear at any place in the bar section. Its syntax is:

	tempo [<note_fraction>=] <bpm> | ...

where *bpm* is the beats per minute and *note_fraction* is the fraction that identifies the note length. It is 1/4 (a quarter note) by default.

Note that in order to define the tempo for a doted quarter note you have to add 1/4 and 1/8 which gives 3/8:

	tempo 3/8=60

The tempo declaration can have any number of | characters afterwards, with no meaning at all but allowing for nice formatting and alignment.

## Dynamics

Dynamics can appear at any place in the bar section. Its syntax is:

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

