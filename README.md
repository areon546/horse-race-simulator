# Horse Racing Simulator OOP Coursework

Part 1 contains a command line implementation of a horse racer simulator based on the assigned files given to complete the coursework. 

part 2 contains a GUI implementation of the same horse racing simulation, but with a couple additional classes, and a GUI (eventually)

## Setup steps:

1. Unzip the zip file downloaded. 
2. Run HorseRacer.class in the folder Part1, with the command 'java HorseRacer', for a demonstration of the program in the command line. 
3. Run HorseRacerGUI.class in the folder Part2, with the command 'java HorseRacerGUI' from the command line, for a demonstration of the program as a GUI. // TODO not currently implemented, will probably cause bugs because i put everything on the same path

## Dependencies
Java

## Usage Guidelines
If you wish to use the Race class in the command line, use the method 'startRace' on a Race object, in the form 
'raceObject.startRace' in your program after importing HorseRaceSimulator.Part1, or if you wish to use the GUI implementation, use the method 'startRaceGUI' on a Race object in your program after importing HorseRaceSimulator.Part2 in your program. 

Objects of the Race class have 3 lanes by default, to which Horse objects can be added to. This has to be done before running the command 'startRace' or 'startRaceGUI' in order for any horses to display within the race. If no horses are specified, the program will not run a race. 

The Race class has 3 constructors with the following signatures:
- Race(int raceTrackLength, int numLanes, String unit)
- Race(int raceTrackLength, int numLanes)
- Race(int raceTrackLength)

The parameter 'raceTrackLength' has to be specified and determins the lenght of the track. Similarly to what happens if no horses are added to the program, it will terminate a race before it starts if the methods 'startRace' or 'startRaceGUI' are called. Invalid values are decimals, 0, or negative numbers. 
Note: Due to how horses move, it is recommended to not set a length of a track longer than 20 since the longer the race, the more likely it is that all Horses fall during the race. 
Required for the race class to function. 

The parameter 'numLanes' determines the number of lanes that the program will create in the program. It is the maximum number of horses that will be added. It only accepts positive integers. 
Note: It is recommended that no more than 10 lanes are added, due to difficulties in differentiating between so many horses, however there is no limit beyond what the computer can handle. In the GUI version, there is a limit of 10 lanes, but this is enforced by the UI, rather than the class. 
If not specified, the default value is 3. 

The parameter unit has two accepted values. 'meters' and 'yards'. The races in this program are measured in meters, however the unit 'yards' can be used as an alternative. Since a yard is about 91% the length of a meter, races in yards are about 9% shorter than races in meters. 
If not specified, the default value is "meters". 

