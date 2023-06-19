# Cleaning Robot VW

## Personal data

- Autor: Victor Ibañez Aparicio

## Goal

Create an application that helps control innovative autonomous cleaning robots. For the pilot test of our cleaning robot, we have selected a rectangular factory floor
that is currently vacant and free of obstacles. This floor will provide an ideal testing ground for our robot, allowing us to evaluate its capabilities in a controlled environment.
The robot will be tasked with cleaning the entire floor, including hard-to-reach corners and crevices. We believe that this test will demonstrate the effectiveness of our robot's
cleaning mechanism, as well as its ability to navigate around obstacles and operate autonomously. With this successful pilot test, we will be one step closer to launching our
cleaning robot into the market, and providing an innovative solution to the challenges of industrial cleaning.

A cleaning robot's position and orientation is represented by its X and Y coordinates and a letter representing one of the four cardinal compass points (N, E, S, W). The
workspace where the robot operates is divided up into a grid to simplify navigation. For instance, an example position of the robot could be 0, 0, N, which indicates that the 
robot is at the bottom-left corner and facing North.

To control the robot, the Maintenance Department sends a string of instructions consisting of the letters "L", "R", and "M". The letter "L" makes the robot spin 90 degrees
left, "R" makes it spin 90 degrees right, and "M" tells it to move forward one grid point in the same direction it is facing.

It is important to note that the square directly North of (X, Y) is (X, Y + 1).

The input for the robot control consists of two lines. The first line is the upper-right coordinates of the workspace, with the bottom-left coordinates assumed to be 0, 0. The
second line provides information on the robots deployed. Each robot is represented by two lines. The first line specifies its position, while the second line is a series of
instructions telling the robot how to explore the workspace. Each robot operates sequentially, meaning that the second robot will start moving only
after the first one has finished.

## Tech stack
- Java 17
- Kotlin
- Spring
- H2


## Assumptions

- The robot only works with a valid input file
- If the robot collides with a side then it is in the same position
- The Workspace will be delimited by first input file and (0,0)

## To run
### Execute app
    gradle :bootRun

### H2 console

- [Web console](http://localhost:8080/h2-console)
- user: sa
- pass: