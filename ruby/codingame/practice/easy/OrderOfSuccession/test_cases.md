# Test cases

## Origin

* https://www.codingame.com/training/easy/order-of-succession

## Problem Statement

### Goal

You have to output the order of succession to the British throne of a list of given people.
The order is simple:
From a descendant A, the next in the order is A’s first child B.
Then, the next one is B’s first child C if any and so on.
If C has no child, then the next one is B’s second child D.
Then D’s children if any. Then B’s third child E… then A’s second child F…

Let’s draw it with a tree:

      A1
    ┌─┴─┐
    B2  F6
 ┌──┼──┐
 C3 D4 E5

You see the order of succession: begin on the left of the tree, walk to the next level whenever possible otherwise continue to the right. Repeat until the whole tree is covered.
Thus, the order is A-B-C-D-E-F.

In fact, in siblings of the same person, the male descendants are ordered before the female descendants. For example, if the order of birth of the children (M for male, F for female) is Fa Ma Me Fe then the order of succession in these siblings is Ma Me Fa Fe.

Ordering rules
(a) in order of generation
(b) in order of gender
(c) in order of age (year of birth)

Outputting rules
(a) exclude dead people
(b) exclude people who are catholic (but include siblings of catholic people)

Note that this puzzle has been written in June, 2017 (some people might have died since this date).

### Input
Line 1: The number of people n
Next n lines: Name Parent Year of birth Year of death Religion Gender

If the people is not dead then the year of death is replaced by the hyphen -.

### Output

One name per line, in the order of succession to the throne: first the Queen, then all her descendants.
Constraints
Exactly one people does not have a parent (the parent’s name is replaced by the hyphen -).
No two siblings of the same gender of a person have the same year of birth.
1 ≤ n ≤ 100

## Test case 1

### Input

6
Elizabeth - 1926 - Anglican F
Charles Elizabeth 1948 - Anglican M
William Charles 1982 - Anglican M
George William 2013 - Anglican M
Charlotte William 2015 - Anglican F
Henry Charles 1984 - Anglican M

### Expected Output

Elizabeth
Charles
William
George
Charlotte
Henry

## Test case 2

### Input

18
Elizabeth - 1926 - Anglican F
Charles Elizabeth 1948 - Anglican M
William Charles 1982 - Anglican M
George William 2013 - Anglican M
Charlotte William 2015 - Anglican F
Henry Charles 1984 - Anglican M
Andrew Elizabeth 1960 - Anglican M
Beatrice Andrew 1988 - Anglican F
Eugenie Andrew 1990 - Anglican F
Edward Elizabeth 1964 - Anglican M
James Edward 2007 - Anglican M
Louise Edward 2003 - Anglican F
Anne Elizabeth 1950 - Anglican F
Peter Anne 1977 - Anglican M
Savannah Peter 2010 - Anglican F
Isla Peter 2012 - Anglican F
Zara Anne 1981 - Anglican F
Mia Zara 2014 - Anglican F

### Expected Output

Elizabeth
Charles
William
George
Charlotte
Henry
Andrew
Beatrice
Eugenie
Edward
James
Louise
Anne
Peter
Savannah
Isla
Zara
Mia

## Test case 3

### Input

26
KingGeorgeVI - 1895 1952 Anglican M
QueenElizabethII KingGeorgeVI 1926 - Anglican F
CharlesPrinceofWales QueenElizabethII 1948 - Anglican M
PrinceWilliamDukeofCambridge CharlesPrinceofWales 1982 - Anglican M
PrinceGeorgeofCambridge PrinceWilliamDukeofCambridge 2013 - Anglican M
PrincessCharlotteofCambridge PrinceWilliamDukeofCambridge 2015 - Anglican F
PrinceHenryofWales CharlesPrinceofWales 1984 - Anglican M
PrinceAndrewDukeofYork QueenElizabethII 1960 - Anglican M
PrincessBeatriceofYork PrinceAndrewDukeofYork 1988 - Anglican F
PrincessEugenieofYork PrinceAndrewDukeofYork 1990 - Anglican F
PrinceEdwardEarlofWessex QueenElizabethII 1964 - Anglican M
JamesViscountSevern PrinceEdwardEarlofWessex 2007 - Anglican M
LadyLouiseWindsor PrinceEdwardEarlofWessex 2003 - Anglican F
AnnePrincessRoyal QueenElizabethII 1950 - Anglican F
PeterPhillips AnnePrincessRoyal 1977 - Anglican M
SavannahPhillips PeterPhillips 2010 - Anglican F
IslaPhillips PeterPhillips 2012 - Anglican F
ZaraTindall AnnePrincessRoyal 1981 - Anglican F
MiaTindall ZaraTindall 2014 - Anglican F
PrincessMargaretCountessofSnowdon KingGeorgeVI 1930 2002 Anglican F
DavidArmstrong-Jones2ndEarlofSnowdon PrincessMargaretCountessofSnowdon 1961 - Anglican M
CharlesArmstrong-JonesViscountLinley DavidArmstrong-Jones2ndEarlofSnowdon 1999 - Anglican M
LadyMargaritaArmstrong-Jones DavidArmstrong-Jones2ndEarlofSnowdon 2002 - Anglican F
LadySarahChatto PrincessMargaretCountessofSnowdon 1964 - Anglican F
SamuelChatto LadySarahChatto 1996 - Anglican M
ArthurChatto LadySarahChatto 1999 - Anglican M

### Expected Output

QueenElizabethII
CharlesPrinceofWales
PrinceWilliamDukeofCambridge
PrinceGeorgeofCambridge
PrincessCharlotteofCambridge
PrinceHenryofWales
PrinceAndrewDukeofYork
PrincessBeatriceofYork
PrincessEugenieofYork
PrinceEdwardEarlofWessex
JamesViscountSevern
LadyLouiseWindsor
AnnePrincessRoyal
PeterPhillips
SavannahPhillips
IslaPhillips
ZaraTindall
MiaTindall
DavidArmstrong-Jones2ndEarlofSnowdon
CharlesArmstrong-JonesViscountLinley
LadyMargaritaArmstrong-Jones
LadySarahChatto
SamuelChatto
ArthurChatto

## Test case 4

### Input

66
KingGeorgeV - 1865 1936 Anglican M
KingEdwardVIII KingGeorgeV 1894 1972 Anglican M
KingGeorgeVI KingGeorgeV 1895 1952 Anglican M
QueenElizabethII KingGeorgeVI 1926 - Anglican F
CharlesPofWales QueenElizabethII 1948 - Anglican M
PrinceWilliamDofCambridge CharlesPofWales 1982 - Anglican M
PrinceGeorgeofCambridge PrinceWilliamDofCambridge 2013 - Anglican M
PrincessCharlotteofCambridge PrinceWilliamDofCambridge 2015 - Anglican F
PrinceHenryofWales CharlesPofWales 1984 - Anglican M
PrinceAndrewDofYork QueenElizabethII 1960 - Anglican M
PrincessBeatriceofYork PrinceAndrewDofYork 1988 - Anglican F
PrincessEugenieofYork PrinceAndrewDofYork 1990 - Anglican F
PrinceEdwardEofWessex QueenElizabethII 1964 - Anglican M
JamesViscountSevern PrinceEdwardEofWessex 2007 - Anglican M
LadyLouiseWindsor PrinceEdwardEofWessex 2003 - Anglican F
AnnePrincessRoyal QueenElizabethII 1950 - Anglican F
PeterPhillips AnnePrincessRoyal 1977 - Anglican M
SavannahPhillips PeterPhillips 2010 - Anglican F
IslaPhillips PeterPhillips 2012 - Anglican F
ZaraTindall AnnePrincessRoyal 1981 - Anglican F
MiaTindall ZaraTindall 2014 - Anglican F
PrincessMargaretCofSnowdon KingGeorgeVI 1930 2002 Anglican F
DavidA-Jones2ndEofSnowdon PrincessMargaretCofSnowdon 1961 - Anglican M
CharlesA-JonesViscountLinley DavidA-Jones2ndEofSnowdon 1999 - Anglican M
LadyMargaritaA-Jones DavidA-Jones2ndEofSnowdon 2002 - Anglican F
LadySarahChatto PrincessMargaretCofSnowdon 1964 - Anglican F
SamuelChatto LadySarahChatto 1996 - Anglican M
ArthurChatto LadySarahChatto 1999 - Anglican M
PrinceHenryDofGloucester KingGeorgeV 1900 1974 Anglican M
PrinceRichardDofGloucester PrinceHenryDofGloucester 1944 - Anglican M
AlexanderWindsorEofUlster PrinceRichardDofGloucester 1974 - Anglican M
XanWindsorLordCulloden AlexanderWindsorEofUlster 2007 - Anglican M
LadyCosimaWindsor AlexanderWindsorEofUlster 2010 - Anglican F
LadyDavinaLewis PrinceRichardDofGloucester 1977 - Anglican F
SennaLewis LadyDavinaLewis 2010 - Anglican F
TaneLewis LadyDavinaLewis 2012 - Anglican M
LadyRoseGilman PrinceRichardDofGloucester 1980 - Anglican F
LylaGilman LadyRoseGilman 2010 - Anglican F
RufusGilman LadyRoseGilman 2012 - Anglican M
PrinceGeorgeDofKent KingGeorgeV 1902 1942 Anglican M
PrinceEdwardDofKent PrinceGeorgeDofKent 1935 - Anglican M
GeorgeWindsorEofStAndrews PrinceEdwardDofKent 1962 - Anglican M
EdwardWindsorLordDownpatrick GeorgeWindsorEofStAndrews 1988 - Catholic M
LadyMarinaCharlotteWindsor GeorgeWindsorEofStAndrews 1992 - Catholic F
LadyAmeliaWindsor GeorgeWindsorEofStAndrews 1995 - Anglican F
LordNicholasWindsor PrinceEdwardDofKent 1970 - Catholic M
AlbertWindsor LordNicholasWindsor 2007 - Anglican M
LeopoldWindsor LordNicholasWindsor 2009 - Anglican M
LouisWindsor LordNicholasWindsor 2014 - Anglican M
LadyHelenTaylor PrinceEdwardDofKent 1964 - Anglican F
ColumbusTaylor LadyHelenTaylor 1994 - Anglican M
CassiusTaylor LadyHelenTaylor 1996 - Anglican M
EloiseTaylor LadyHelenTaylor 2003 - Anglican F
EstellaTaylor LadyHelenTaylor 2004 - Anglican F
PrinceMichaelofKent PrinceGeorgeDofKent 1942 - Anglican M
LordFrederickWindsor PrinceMichaelofKent 1979 - Anglican M
MaudWindsor LordFrederickWindsor 2013 - Anglican F
IsabellaWindsor LordFrederickWindsor 2016 - Anglican F
LadyGabriellaWindsor PrinceMichaelofKent 1981 - Anglican F
PrincessAlexandra PrinceGeorgeDofKent 1936 - Anglican F
JamesOgilvy PrincessAlexandra 1964 - Anglican M
AlexanderOgilvy JamesOgilvy 1996 - Anglican M
FloraOgilvy JamesOgilvy 1994 - Anglican F
MarinaMowatt PrincessAlexandra 1966 - Anglican F
ChristianMowatt MarinaMowatt 1993 - Anglican M
ZenouskaMowatt MarinaMowatt 1990 - Anglican F

### Expected Output

QueenElizabethII
CharlesPofWales
PrinceWilliamDofCambridge
PrinceGeorgeofCambridge
PrincessCharlotteofCambridge
PrinceHenryofWales
PrinceAndrewDofYork
PrincessBeatriceofYork
PrincessEugenieofYork
PrinceEdwardEofWessex
JamesViscountSevern
LadyLouiseWindsor
AnnePrincessRoyal
PeterPhillips
SavannahPhillips
IslaPhillips
ZaraTindall
MiaTindall
DavidA-Jones2ndEofSnowdon
CharlesA-JonesViscountLinley
LadyMargaritaA-Jones
LadySarahChatto
SamuelChatto
ArthurChatto
PrinceRichardDofGloucester
AlexanderWindsorEofUlster
XanWindsorLordCulloden
LadyCosimaWindsor
LadyDavinaLewis
TaneLewis
SennaLewis
LadyRoseGilman
RufusGilman
LylaGilman
PrinceEdwardDofKent
GeorgeWindsorEofStAndrews
LadyAmeliaWindsor
AlbertWindsor
LeopoldWindsor
LouisWindsor
LadyHelenTaylor
ColumbusTaylor
CassiusTaylor
EloiseTaylor
EstellaTaylor
PrinceMichaelofKent
LordFrederickWindsor
MaudWindsor
IsabellaWindsor
LadyGabriellaWindsor
PrincessAlexandra
JamesOgilvy
AlexanderOgilvy
FloraOgilvy
MarinaMowatt
ChristianMowatt
ZenouskaMowatt