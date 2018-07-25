This OOP design is made for handling a turn-based game. 
The `Board` is responsible for retaining the information regarding the board of a single game. 
The board determines what size, and shapes there are.

Their are rules that work in concert together in a hierarchical fashion.

Game -> Board -> Turn -> Action

A `Game` is the current state of the "game". The `Game` connects the board with its `Player`s.
A `Turn` is a opportunity for a `Player` to perform an `Action`. 

* This architecture should allow for "Tic Tac Toe", "Checkers", "Chess", "Monopoly" and even "Settlers of Catan".
* This architecture is mostly for educational purposes but is robust nonetheless.
* This architecture is intended to be stateful, such that at any moment in time the game me be "saved" exited. 
* Upon continuing and restoration of the "Save", the game should continue from the point in which it was last changed.
* This architecture should allow for the game to be run on "serverless" architecture and be fairly resistant to concurrency issues.

# Actions, Events, Trigger & Status
## Action
An `Action` is anything that is executed, performed, invoked, etc.. 
Some example actions may be:
* Start the game
* Stop the game
* Pause the game
* Resume the game
* Begin a turn

## Event
An `Event` is the synthesis of a result of "something". Very often an `Event` is related to an `Action`.
However, there may be as few as zero or several `Events` for any given `Action`. 
Additionally, an `Event` may be `Trigger`ed through other circumstances. 
For instance a `Timer` may be created that at the conclusion of the countdown a `TimerCompletedEvent` is `Trigger`ed. 

## Trigger
Only a `Trigger` may yield an `Event`. The distinction is that a `Trigger` is a `Predicate` event-bus subscription system.
This part of the system is deliberately designed to be scalable and allow for a great amount of flexibility.

Very often an `Action` will call a `Trigger`.   

# Game
## Turn
A `Turn` is an isolated slot for a `Player` to do something. 
Generally that will be to perform an `Action` or series of `Action`s. 
A `Turn` has the `done()` method to indicate that the `Player` has completed their `Turn`.
It is possible to make an implementation that could have a time limit. 

Let's use chess as an example for a moment. 
When a `Player` is in "check" the only legal moves they make may be to remove themselves from check. 
To facilitate this sort of logic the `Turn` interface has a `Predicate<? extends Action>` to determine whether or not a specific `Action` may be invoked in this `Turn` or not.
 
A `Turn` of a certain type should be able to indicate to the `Game` if there are any state changes to the `Game`. 
For instance in a `Game` with points a `Turn` may want to indicate if and how many points the `Player` should be given. 
The way that this is accomplished is by returning a `Set` of `TurnStatus` objects. 
A `TurnStatus` may indicate that an `ActionPerformed`.

Upon the completion of the `Turn` the `Game` is responsible to process the various `TurnStatus` objects part of the `TurnCompleteEvent`. 
For "Tic Tac Toe" a `TicTacToeMove` is the `Action` that encodes the `Player` (x or o) and what `Position` they moved. 
Several subscriptions to the `TicTacToeMove` determines if either `Player` has `GameWonStatus` or `GameDrawStatus`.

Another matter that may be taken into consideration upon completion of a `Turn` is what the next `Turn` looks like. 
That means not only which `Player` is next but which implementation of the `Turn` is next as well.


  
 