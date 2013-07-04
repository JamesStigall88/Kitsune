#Kitsune
-Guillaume Ardaud <gardaud@acm.org>

Kitsune is a rudimentary simulation tool written in Java+Swing that allows to design the layout of a building, position agents and define their behaviors, and run a simulation of a fire evacuation. 

It was written as part of a Computer Science senior thesis at Oxford Brookes.

Running the software is as simple as:

````
cd final/ ;
unzip dist.zip ;
cd dist/ ;
java -jar Kitsune.jar
````

A sample layout can be found in `dist/layouts/ResearchBuilding.kitsune`.

#Using the software

A typical Kitsune workflow is as follows:
- Add a new floor
- Position walls, doors, exit doors
- Position agents
- Position fire sources
- Run the simulation
- Save the simulation output and/or created layout

#Adding a new floor
Adding a new floor is done by clicking the floor add button (). Similarly, the floor remove button () removes the current floor.
You can see which floor you're currently on by looking at the bottom of the floor view:

Floors are connected by staircases. Staircases are currently not implemented :(

#Positioning walls, doors, and exits
##Walls
Clicking the wall icon () makes the cursor a wall. Moving it on the floorplan allows you to preview where the wall will be positioned; clicking adds  a floor at that position. Click on an already added wall to remove it.

#Doors
Doors slightly slow down agents

#Exits
Exits are what allows the agents to exit the building. A layout should have at least one exit.

#Positioning agents
Select the agent button to make the cursor become an agent. Click anywhere on the plan to position an agent.
Upon positioning an agent, you will be asked to choose a behavior for it.
There are currently 3 behaviors available:
- Optimal - those agents take the most efficient path to the exit. In real life, those would be people who have followed safety training.
- Panic - those agents move in a random manner.
- Follower - those agents follow the agent nearest to them. In real life, those represent the average visitor or person in the building, or children.
More behaviors can be implemented in the source code.