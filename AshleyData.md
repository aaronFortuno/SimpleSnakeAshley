SYSTEM                  DESCRIPTION
-------------------------------------------------------------------------------
GridRenderSystem        World Grid render logic
DebugCameraSystem       Debug camera logic (move, zoom)
DebugRenderSystem       Debug render logic
SnakeSystem             [passive] cleanup logic to manage snake destruction
DirectionSystem         Logic to update movement speed based on direction
SnakeMovementSystem     Logic to update position based on movement(speed)
BoundsSystem            Logic to update bounds based on position and dimension
PlayerControlSystem     Player control logic / input handling logic
WorldWrapSystem         Logic to keep entity inside world bounds
CoinSystem              Logic to reposition and make it available again (spawn)
CollisionSystem         Collision detection logic
RenderSystem            Rendering logic
HudRenderSystem         Hud rendering logic
-------------------------------------------------------------------------------

SYSTEM/COMP         | Position | Dimension | Bounds | Snake | Direction | Movement | Player | WorldWrap | Coin | BodyPart | Texture | ZOrder |
----------------------------------------------------------------------------------------------------------------------------------------------
GridRenderSystem    |          |           |        |       |           |          |        |           |      |          |         |        |
DebugCameraSystem   |          |           |        |       |           |          |        |           |      |          |         |        |
DebugRenderSystem   |          |           |    X   |       |           |          |        |           |      |          |         |        |
SnakeSystem         |          |           |        |   X   |           |          |        |           |      |          |         |        |
DirectionSystem     |          |           |        |       |     X     |     X    |        |           |      |          |         |        |
SnakeMovementSystem |    .     |           |        |   X   |           |     .    |        |           |      |          |         |        |
BoundsSystem        |    X     |     X     |    X   |       |           |          |        |           |      |          |         |        |
PlayerControlSystem |          |           |        |       |     X     |          |   X    |           |      |          |         |        |
WorldWrapSystem     |    X     |           |        |       |           |          |        |     X     |      |          |         |        |
CoinSystem          |    X     |           |        |       |           |          |        |           |   X  |          |         |        |
CollisionSystem     |          |           |    X   |   X   |           |          |        |           |   X  |     X    |         |        |
RenderSystem        |    X     |     X     |        |       |           |          |        |           |      |          |    X    |    X   |
HudRenderSystem     |          |           |        |       |           |          |        |           |      |          |         |        |
----------------------------------------------------------------------------------------------------------------------------------------------

ENTITY/COMP         | Position | Dimension | Bounds | Snake | Direction | Movement | Player | WorldWrap | Coin | BodyPart | Texture | ZOrder |
----------------------------------------------------------------------------------------------------------------------------------------------
SnakeHead           |    X     |     X     |    X   |       |     X     |     X    |   X    |     X     |      |          |    X    |    X   |
Snake               |          |           |        |   X   |           |          |        |           |      |          |         |        |
Coin                |    X     |     X     |    X   |       |           |          |        |           |   X  |          |    X    |    X   |
SnakeBodyPart       |    X     |     X     |    X   |       |           |          |        |           |      |     X    |    X    |    X   |
Background          |    X     |     X     |    X   |       |           |          |        |           |      |          |         |    X   |
----------------------------------------------------------------------------------------------------------------------------------------------