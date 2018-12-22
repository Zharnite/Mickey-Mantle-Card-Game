# Mickey-Mantle-Card-Game
Final Project in CSE114

### Game Play Description 
* If that player has a 7 (sevens always lead a pile), they may play that card on the applicable suit pile (i.e. the 7 of hearts would go on the hearts pile).
* Any other card can only be played on a pile if an adjacent card has already been played. So for example, a 9 of Spades can only be played if an 8 of Spades has already been played, and an 8 of Spades could only have been played if a 7 of Spades had already been played.
* If a player cannot play any cards, that player's turn is skipped.
* A player may not skip a turn if they have a card that can be played.
* Suit lists are not circular but an Ace may be played either after a 2 or a King. So, for example, we may have a Spades pile with A,2,3,4,5,6,7 or 7,8,9,10,J,Q,K,A, but not 7,8,9,10,J,Q,K,A,2.

Description taken from [this site](http://www3.cs.stonybrook.edu/~cse114/L02/hw/FinalProject.html)
