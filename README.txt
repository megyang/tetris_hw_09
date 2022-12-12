=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 34342588
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays: The game board is a large 2D array with each type of block being
    their own unique, smaller 2D array that fill the board. This was appropriate to use
    because the typical tetris game play occurs on a gridded board and the placements
    of each block would be placed within each grid using coordinates.

  2. Collections: I used a LinkedList to implement the Queue for the game. In a typical
    tetris gameplay, the blocks are queued automatically and are previewed on the side.
    However, here, in order to allow the player more options in game play, they have the
    option to load as many previews of the blocks as they want, or choose not to load
    any previews for an extra challenge. A LinkedList was appropriate because rather
    than just storing and accessing the data randomly, I needed to implement a Queue and
    to be ordered so that it is FIFO.

  3. File I/O: File I/O was used to save and load the game state; if the player wanted to
    save a particular moment in the game and load it back if the player wanted to continue
    playing from that point in time. This way, if the player makes any moves that they
    don't particularly like, they can always reload to their more favorable state.
    File I/O was also used to save and load the leaderboard. This way, the leaderboard
    is maintained regardless of whether the game is closed.

  4. Inheritance/Subtyping: Many of my classes extended JPanel, JFrame, and Thread. This
    was essential to provide the various windows such as the starting window, game play,
    and the leaderboard. Additionally, each of these classes inherited many of the methods
    so the game can be more easily implemented.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Block: This class provided the properties that each tetris block would have such as the shape, color,
  the coordinates, and orientation for rotation. There are also methods that allow for the manipulation
  of these properties for other methods to call when implementing the game.

  GameBoard: This class is where the player plays the game. Here are all the bulk of the methods that are
  required for the game play. The blocks are produced and the bounds are set so that they are unable to go
  out of bounds during game play especially during movements like move left, right, down, hard drop, and
  especially rotate. Additionally, the methods to clear the lines when completed, the File I/O to save the game
  state, are defined here.

  LeaderBoardFrame: This class is the JFrame for the leaderboard. It contains the File I/O to save it as well as
  the methods to add the newest player and score to the JTable.

  MainMenuFrame: This class contains several of the actionlisteners to link the button to each of their functions.
  It is just the opening screen that allows the player to start, quit, see the leaderboard, and see the instructions.

  PlayFrame: This class hosts the entire game play including the GameBoard, preview blocks on the side, the score
  and level, and buttons that link to the File I/O, main menu, and add to queue buttons. Additionally, there is a
  timer that repaints the entire screen so that there is less delay and flickering. The save and load buttons allow the player
  to save any point in the game and restart there. The queue allows the player to see preview blocks if they so choose
  to make the game a bit easier. The main menu allows the player to return to the start window.

  PlayThread: This is how the game is run. Additionally, this class hosts the File I/O for the score and level to
  restore whenever load is pressed. It calls many of the other methods so that the game can proceed. Here is also where
  the points and levels are calculated and updated.

  QueueArea: This class is how the blocks on the side show up so that the player can preview them. It contains the
  methods to draw each additional block and add each one to the queue whenever the button is pressed.

  Tetris: This class manipulates each of the frame and when they are visible then allows the game to run; there are
  also the methods that start and stop the game when the player starts and loses.

  *Block: The list of all the classes with a letter preceding the word block are all the same except for a unique
  2D array. This differentiates each type of block and their corresponding shape and color.

  FileLineIterator and TetrisParser: These are classes reused from TwitterBot to read the file in and parse through
  each line so that the game can be reloaded at the specified state. TetrisParser was modified to check data format
  to ensure saved game can be properly loaded.

  InstructionsWindow: Provides the list of instructions on how to play the game.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Implementing the File I/O was a major obstacle in implementation to figure out how to be able to write and reload
  the state while remembering to keep track of every element in the state such as score and level.

  There were a lot of errors with arrays out of bounds initially that required much debugging as well as
  many NullPointerExceptions that were harder to troubleshoot becuase the GUI would just show up as completely black.

  Design wise, it was hard to plan and lay out the different elements of the game and navigate where to put which
  methods and where to call what. I also ended up shifting my game design away from what I initially planned
  in the game proposal.

  Additionally, I had to look up many various methods on how to do certain things because I am not completely
  familiar with Java Swing.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?


  I think there is a decent amount of separation of functionality. I was able to separate each of the many methods
  into various classes properly. Game play, game display, and game thread were all separated.
  I think there is good private state encapsulation.
  I used get, set, and other accessor methods that to manipulate the private fields and such.
  LinkedList for queuing blocks was not passed directly to the QueueArea class. The queue cannot be modified by the
  QueueArea class, Queue area is only used to display the queue.
  If I were to refactor, I would add configuration file to avoid hard coding things like file path and some parameters.
  If I had the time I might have added a pause and resume button as well as a function to hold a block,
  much like the original tetris game.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used
  while implementing your game.

  tutorials:
    https://coderanch.com/t/678800/java/Tetris-Game-Java-Swing
    https://www.ssaurel.com/blog/learn-to-create-a-tetris-game-in-java-with-swing/