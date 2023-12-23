SYSTEM                  DESCRIPTION
--------------------------------------------------------------------------------------
GridRenderSystem        World Grid render logic
DebugCameraSystem       Debug camera logic (move, zoom)
DebugRenderSystem       Debug render logic
SnakeSystem             [passive] cleanup logic to manage snake destruction
DirectionSystem         Logic to update movement speed based on direction
SnakeMovementSystem     Logic to update position based on movement(speed)
BoundsSystem            Logic to update bounds based on position and dimension
--------------------------------------------------------------------------------------

SYSTEM/COMP         |  Position  | Dimension | Bounds | Snake | Direction | Movement |
--------------------------------------------------------------------------------------
GridRenderSystem    |            |           |        |       |           |          |
DebugCameraSystem   |            |           |        |       |           |          |
DebugRenderSystem   |            |           |    X   |       |           |          |
SnakeSystem         |            |           |        |   X   |           |          |
DirectionSystem     |            |           |        |       |     X     |     X    |
SnakeMovementSystem |     .      |           |        |   X   |           |     .    |
BoundsSystem        |     X      |     X     |    X   |       |           |          |
--------------------------------------------------------------------------------------

ENTITY/COMP         |  Position  | Dimension | Bounds | Snake | Direction | Movement |
--------------------------------------------------------------------------------------
SnakeHead           |     X      |     X     |    X   |       |           |          |
Snake               |            |           |        |   X   |           |          |
--------------------------------------------------------------------------------------