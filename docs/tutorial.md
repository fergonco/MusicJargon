---
layout: default
title: Music Jargon
---

# A tutorial... bites the dust!

We will reproduce the famous song "Another one bytes the dust", from Queen.

MusicJargon splits the concept of rhythm and harmony so we start by getting the rhythm right:

	r1 = rhythm [X...X...X......x] on 4/4
	r2 = rhythm [X.x.X.xX......xx] on 4/4

Over this rhythm we will play the famous riff with E, G and A notes on the bass:

	r1 = rhythm [X...X...X......x] on 4/4
	r2 = rhythm [X.x.X.xX......xx] on 4/4
	voices   | acoustic_bass    
	         | E3 E E E  on r1
	         | E3 E G E A A G on r2

By default, tempo is 120 bpm but the song is 110. Let's set it right:

	r1 = rhythm [..xxX...X...X...] on 4/4
	r2 = rhythm [...xX.x.X.xX....] on 4/4
	voices    | acoustic_bass
	tempo 110 |
	          | E3 E E E  on r1
	          | E3 E G E A A G on r2

And actually this riff repeats itself twice before the guitar joins:

	r1 = rhythm [..xxX...X...X...] on 4/4
	r2 = rhythm [...xX.x.X.xX....] on 4/4
	voices         | acoustic_bass
	tempo 110      |
	start:         |
	               | E3 E E E  on r1
	               | E3 E G E A A G on r2
	repeat start 2 |

Now let's add the drums. We need two voices, one for the right hand and the other for the bassdrum and snare:

	quarter = rhythm [xxxx] on 4/4
	eighth  = rhythm [XxXxXxXx] on 4/4
	r1      = rhythm [X...X...X......x] on 4/4
	r2      = rhythm [X.x.X.xX......xx] on 4/4
	voices         | acoustic_bass        | drums "right hand" | drums "rhythm"
	tempo 110      |                      |                    |
	               | - - - - - A3 G on r2 |                    |
	start:         |                      |                    |
	               | E3 E E E  on r1      | hihat on eighth    | bd sn on quarter
	               | E3 E G E A A G on r2 | hihat on eighth    | bd sn on quarter
	repeat start 2 |                      |                    |

Here comes Brian May to join his friend John Deacon:

	quarter = rhythm [xxxx] on 4/4
	eighth  = rhythm [XxXxXxXx] on 4/4
	r1      = rhythm [X...X...X......x] on 4/4
	r2      = rhythm [X.x.X.xX......xx] on 4/4
	voices         | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
	tempo 110      |                      |                      |                    |
	               | - - - - - A3 G on r2 |                      |                    |
	start:         |                      |                      |                    |
	               | E3 E E E  on r1      |                      | hihat on eighth    | bd sn on quarter
	               | E3 E G E A A G on r2 |                      | hihat on eighth    | bd sn on quarter
	repeat start 2 |                      |                      |                    |
	               | E3 E E E  on r1      | E3 E E E       on r1 | hihat on eighth    | bd sn on quarter
	               | E3 E G E A A G on r2 | E3 E G E A A G on r2 | hihat on eighth    | bd sn on quarter

But this is too much! Brian is always getting all the attention. This is John song, so please Brian, just accompany John and play a bit lower:

	quarter = rhythm [xxxx] on 4/4
	eighth  = rhythm [XxXxXxXx] on 4/4
	r1      = rhythm [X...X...X......x] on 4/4
	r2      = rhythm [X.x.X.xX......xx] on 4/4
	voices            | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
	tempo 110         |                      |                      |                    |
	                  | - - - - - A3 G on r2 |                      |                    |
	start:            |                      |                      |                    |
	                  | E3 E E E  on r1      |                      | hihat on eighth    | bd sn on quarter
	                  | E3 E G E A A G on r2 |                      | hihat on eighth    | bd sn on quarter
	repeat start 2    |                      |                      |                    |
	may:              |                      |                      |                    |
	dynamics          |                      | ppp                  |                    |
	                  | E3 E E E  on r1      | E4 E E E       on r1 | hihat on eighth    | bd sn on quarter
	                  | E3 E G E A - - on r2 | E4 E G E A - - on r2 | hihat on eighth    | bd sn on quarter
	repeat may 2      |                      |                      |                    |

We are typing the same sequence again and again, so let's name it and reuse where possible:

	quarter = rhythm [xxxx] on 4/4
	eighth  = rhythm [XxXxXxXx] on 4/4
	r1      = rhythm [X...X...X......x] on 4/4
	r2      = rhythm [X.x.X.xX......xx] on 4/4
	riff1 = sequence E3 E E E
	riff2 = sequence E3 E G E A A G 
	voices         | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
	tempo 110      |                      |                      |                    |
	               | - - - - - A3 G on r2 |                      |                    |
	start:         |                      |                      |                    |
	               | riff1 on r1          |                      | hihat on eighth    | bd sn on quarter
	               | riff2 on r2          |                      | hihat on eighth    | bd sn on quarter
	repeat start 2 |                      |                      |                    |
	may:           |                      |                      |                    |
	dynamics       |                      | ppp                  |                    |
	               | riff1 on r1          | E4 E E E       on r1 | hihat on eighth    | bd sn on quarter
	               | E3 E G E A - - on r2 | E4 E G E A - - on r2 | hihat on eighth    | bd sn on quarter
	repeat may 2   |                      |                      |                    |

We can even reuse it in Mays tab because the only difference is that it is one octave higher:

	quarter = rhythm [xxxx] on 4/4
	eighth  = rhythm [XxXxXxXx] on 4/4
	r1      = rhythm [X...X...X......x] on 4/4
	r2      = rhythm [X.x.X.xX......xx] on 4/4
	riff1 = sequence E3 E E E
	riff2 = sequence E3 E G E A A G 
	voices         | acoustic_bass        | electric_guitar            | drums "right hand" | drums "rhythm"
	tempo 110      |                      |                            |                    |
	               | - - - - - A3 G on r2 |                            |                    |
	start:         |                      |                            |                    |
	               | riff1 on r1          |                            | hihat on eighth    | bd sn on quarter
	               | riff2 on r2          |                            | hihat on eighth    | bd sn on quarter
	repeat start 2 |                      |                            |                    |
	may:           |                      |                            |                    |
	dynamics       |                      | ppp                        |                    |
	               | riff1 on r1          | transpose8(riff1, 1) on r1 | hihat on eighth    | bd sn on quarter
	               | E3 E G E A - - on r2 | E4 E G E A - -      on r2  | hihat on eighth    | bd sn on quarter
	repeat may 2   |                      |                            |                    |

