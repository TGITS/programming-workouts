program DigitalRivers;
{
Solution to the 1999 British Informatics Olympiad exam
question 1: Digital Rivers

Solution copyright (c) 1999 The British Informatics Olympiad (BIO).

This program may be freely copied by persons or organisations
involved in the British Informatics Olympiad or the International
Olympiad in Informatics, on condition that no changes are made and
this notice is not altered. Distribution for profit is forbidden
unless permission is first obtained in writing from the BIO.

This program is for educational purposes only and comes with no
warranty, implied or otherwise, as to its fitness for any purpose.

Author:     Antony Rix
Internet: http://www.christs.cam.ac.uk/bio/
E-mail:     a.rix@lineone.net
S-mail:     The British Informatics Olympiad
            Christ's College
            Cambridge CB2 3BU
            United Kingdom
}

{ Start value }
var
    n: integer;

function next_river( n: integer ): integer;
var
    s: integer;
begin
    s := n;
    while s > 0 do begin
        n := n + (s mod 10);
        s := s div 10;
    end;
    next_river := n;
end;

procedure part1a;
var
    river1, river3, river9: integer;
begin
    { We will use these variables to follow the routes of rivers 1, 3 and 9 }
    river1 := 1; river3 := 3; river9 := 9;

    { If we have not found a match, move along each river until we meet or
      pass n.  If we still haven't met n, move one step along river n. }
    while (n <> river1) and (n <> river3) and (n <> river9) do begin
        while river1 < n do river1 := next_river( river1 );
        while river3 < n do river3 := next_river( river3 );
        while river9 < n do river9 := next_river( river9 );
        if (n <> river1) and (n <> river3) and (n <> river9) then
            n := next_river( n );
    end;

    { Show the solution }
    if river1 = n then writeln( 'First meets river 1 at ', n );
    if river3 = n then writeln( 'First meets river 3 at ', n );
    if river9 = n then writeln( 'First meets river 9 at ', n );
end;

procedure part1b;
var
    start: integer;
    current: integer;
    hits: array[1..500] of integer;
begin
    { hits will store the number of times we have reached each river. }
    for current := 1 to 500 do hits[current] := 0;

    { Follow rivers 1 to 500 and note each time a value is 'hit' }
    for start := 1 to 500 do begin
        current := start;
        while current < 500 do begin
            inc(hits[current]);
            current := next_river( current );
        end;
    end;

    { Find the first time we meet a value 100 times }
    for current := 1 to 500 do if hits[current] = 100 then begin
            writeln( 'First number in 100 rivers is ', current );
            break;
        end;
end;

begin
    writeln( 'Enter a number from 1 to 16384 for part (a), or 0 for part (b)' );
    readln( n );
    if (n > 0) and (n <= 16384) then
        part1a
    else
        part1b;
    writeln( 'Program finished.' );
end.