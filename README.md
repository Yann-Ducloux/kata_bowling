Bowling
==

### Problem Description #

Create a program, which, given a valid sequence of rolls for one line of
American Ten-Pin Bowling, produces the total score for the game. Here are
some things that the program will do:
<ul>
    <li>We will check for valid rolls.</li>
    <li>We will check for correct number of rolls and frames.</li>
    <li>We will provide scores for intermediate frames.</li>
</ul>
I think you’ll see that improvements like those above would go in readily if
they were needed for real.
We can briefly summarize the scoring for this form of bowling:
<ul>
    <li>Each game, or “line” of bowling, includes ten turns, or “frames” for the
bowler.</li>
    <li>In each frame, the bowler gets up to two tries to knock down all the
pins.</li>
    <li>If in two tries, he fails to knock them all down, his score for that frame
is the total number of pins knocked down in his two tries.</li>
    <li>If in two tries he knocks them all down, this is called a “spare” and his
score for the frame is ten plus the number of pins knocked down on
his next throw (in his next turn).</li>
    <li>If on his first try in the frame he knocks down all the pins, this is called
a “strike”. His turn is over, and his score for the frame is ten plus the
simple total of the pins knocked down in his next two rolls.</li>
    <li>If he gets a spare or strike in the last (tenth) frame, the bowler gets to
throw one or two more bonus balls, respectively. These bonus throws
are taken as part of the same turn. If the bonus throws knock down all
the pins, the process does not repeat: the bonus throws are only used
to calculate the score of the final frame.</li>
    <li>The game score is the total of all frame scores.</li>
</ul>
More info on the rules at: How to Score for Bowling
Clues
What makes this game interesting to score is the lookahead in the scoring
for strike and spare. At the time we throw a strike or spare, we cannot

calculate the frame score: we have to wait one or two frames to find out
what the bonus is.

### Suggested Test Cases #
(When scoring “X” indicates a strike, “/” indicates a spare, “-” indicates a
miss)
<ul>
    <li>X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames * 30 points =
300</li>
    <li>9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10
frames * 9 points = 90</li>
    <li>5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with
a final 5) = 10 frames * 15 points = 150</li>
</ul>
