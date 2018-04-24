#TicTacToe

####by Alexander Sachs

**How this program app was written**

I read over the instructions and came up with the following check list:

overall checklist of what to do:

	- [x]1. Refactor
	- [x]	a. separate AI logic
	- [x]   b. separate Human player
	- [x]2. Make improvements
	- [x]3. Add tests
	- [x]4. Make additions
	- [x]5. Add tests

I listed the improvements and additions necessary for completion of the task below:

improvements:

	- [x] 1. Decouple code from console output
	- [x] 2. Does not handle bad user input
	- [x] 3. Make it unbeatable
	- [x] 4. More notifications of what is going on
	- [x] 5. Decouple code from console input

additions:

	- [x] 1. Allow user to chose game type
	- [x] 2. Allow user to choose which player goes first
    - [x] 3. Allow user to choose their symbol

After completion of the tasks above the following benefits have been recognized:

    1. AI and player are separated into classes making
     program easier to test, better organized, and more adaptable 
     to changing order and token
    2. AI is now unbeatable:
        using recursion, the AI will investigate outcomes given a
        possible move. If an outcome is found that results in a win
        or tie, it will take that move.
    3. Tests - some tests have been implemented, it is not full coverage
    4. The UI has been docoupled, allowing a user to pass in a UI in the Main class
    that implements the UI interface. The input has also been decoupled to the UI
    interface. This feature could go further, as longer Strings
    are currently passed to the ui object, where a future iteration might only 
    pass single word Strings and a format code for output.
    
**instructions**
* Gradle is included as the build system for the application. To build, execute `./gradlew build` or on Windows 
  `gradlew.bat build`
* To run the application execute `./gradlew run` or preferably run the .jar file in build/libs with 
`java -jar <file>`


/
/
/