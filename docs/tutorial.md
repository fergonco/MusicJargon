---
layout: default
title: Music Jargon
---

# A tutorial... bites the dust!

We will reproduce the famous song "Another one bytes the dust", from Queen.

We start by getting the rhythm right:

    r1 = [X...X...X......x] on 4/4
    r2 = [X.x.X.xX......xx] on 4/4

Over this rhythm we will play the famous riff with E, G and A notes on the bass:

    r1 = [X...X...X......x] on 4/4
    r2 = [X.x.X.xX......xx] on 4/4
    voices | acoustic_bass
           | - - - - - A3 G on r2
           | E3 E E E       on r1
           | E3 E G E A A G on r2

By default, tempo is 120 bpm but the song is 110. Let's set it right:

    r1 = [X...X...X......x] on 4/4
    r2 = [X.x.X.xX......xx] on 4/4
    voices    | acoustic_bass
    tempo 110 | - - - - - A3 G on r2
              | E3 E E E       on r1
              | E3 E G E A A G on r2

And actually this riff repeats itself twice before the guitar joins:

    r1 = [X...X...X......x] on 4/4
    r2 = [X.x.X.xX......xx] on 4/4
    voices         | acoustic_bass
    tempo 110      | - - - - - A3 G on r2
    start:         |
                   | E3 E E E       on r1
                   | E3 E G E A A G on r2
    repeat start 2 |

Now let's add the drums. We need two voices, one for the right hand and the other for the bassdrum and snare:

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    voices         | acoustic_bass        | drums "right hand" | drums "rhythm"
    tempo 110      | - - - - - A3 G on r2 |                    |
    start:         |                      |                    |
                   | E3 E E E  on r1      | hihat on eighth    | bd sn on quarter
                   | E3 E G E A A G on r2 | hihat on eighth    | bd sn on quarter
    repeat start 2 |                      |                    |

Here comes Brian May to join his friend John Deacon:

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    voices         | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
    tempo 110      | - - - - - A3 G on r2 |                      |                    |
    start:         |                      |                      |                    |
                   | E3 E E E  on r1      |                      | hihat on eighth    | bd sn on quarter
                   | E3 E G E A A G on r2 |                      | hihat on eighth    | bd sn on quarter
    repeat start 2 |                      |                      |                    |
                   | E3 E E E  on r1      | E4 E E E       on r1 | hihat on eighth    | bd sn on quarter
                   | E3 E G E A A G on r2 | E4 E G E A A G on r2 | hihat on eighth    | bd sn on quarter

But this is too much! Brian is always getting all the attention. This is John song, so please Brian, just accompany John and play a bit lower:

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    voices            | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
    tempo 110         | - - - - - A3 G on r2 |                      |                    |
    start:            |                      |                      |                    |
                      | E3 E E E  on r1      |                      | hihat on eighth    | bd sn on quarter
                      | E3 E G E A A G on r2 |                      | hihat on eighth    | bd sn on quarter
    repeat start 2    |                      |                      |                    |
    may:              |                      |                      |                    |
    dynamics          |                      | ppp                  |                    |
                      | E3 E E E  on r1      | E4 E E E       on r1 | hihat on eighth    | bd sn on quarter
                      | E3 E G E A A G on r2 | E4 E G E A A G on r2 | hihat on eighth    | bd sn on quarter
    repeat may 2      |                      |                      |                    |

Actually, when may enters, they are not playing the two last notes of the riff, so let's add silence notes:

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    voices            | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
    tempo 110         | - - - - - A3 G on r2 |                      |                    |
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

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    riff1   = E3 E E E
    riff2   = E3 E G E A A G 
    voices         | acoustic_bass        | electric_guitar      | drums "right hand" | drums "rhythm"
    tempo 110      | - - - - - A3 G on r2 |                      |                    |
    start:         |                      |                      |                    |
                   | riff1 on r1          |                      | hihat on eighth    | bd sn on quarter
                   | riff2 on r2          |                      | hihat on eighth    | bd sn on quarter
    repeat start 2 |                      |                      |                    |
    may:           |                      |                      |                    |
    dynamics       |                      | ppp                  |                    |
                   | riff1 on r1          | E4 E E E       on r1 | hihat on eighth    | bd sn on quarter
                   | E3 E G E A - - on r2 | E4 E G E A - - on r2 | hihat on eighth    | bd sn on quarter
    repeat may 2   |                      |                      |                    |

We can even reuse it in May's tab because the only difference is that it is one octave higher:

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    riff1   = E3 E E E
    riff2   = E3 E G E A A G 
    voices         | acoustic_bass        | electric_guitar            | drums "right hand" | drums "rhythm"
    tempo 110      | - - - - - A3 G on r2 |                            |                    |
    start:         |                      |                            |                    |
                   | riff1 on r1          |                            | hihat on eighth    | bd sn on quarter
                   | riff2 on r2          |                            | hihat on eighth    | bd sn on quarter
    repeat start 2 |                      |                            |                    |
    may:           |                      |                            |                    |
    dynamics       |                      | ppp                        |                    |
                   | riff1 on r1          | transpose8(riff1, 1) on r1 | hihat on eighth    | bd sn on quarter
                   | E3 E G E A - - on r2 | E4 E G E A - -      on r2  | hihat on eighth    | bd sn on quarter
    repeat may 2   |                      |                            |                    |

But now, Roger, can you spice up a bit those drums? You are doing the same all the time! What? You can't? Ok, in that case we will just write %, to indicate that you are just being a boring drummer:

    quarter = [xxxx] on 4/4
    eighth  = [XxXxXxXx] on 4/4
    r1      = [X...X...X......x] on 4/4
    r2      = [X.x.X.xX......xx] on 4/4
    riff1   = E3 E E E
    riff2   = E3 E G E A A G 
    voices         | acoustic_bass        | electric_guitar            | drums "right hand" | drums "rhythm"
    tempo 110      | - - - - - A3 G on r2 |                            |                    |
    start:         |                      |                            |                    |
                   | riff1 on r1          |                            | hihat on eighth    | bd sn on quarter
                   | riff2 on r2          |                            | %                  | %
    repeat start 2 |                      |                            |                    |
    may:           |                      |                            |                    |
    dynamics       |                      | ppp                        |                    |
                   | riff1 on r1          | transpose8(riff1, 1) on r1 | %                  | %
                   | E3 E G E A - - on r2 | E4 E G E A - -      on r2  | %                  | %
    repeat may 2   |                      |                            |                    |
 

