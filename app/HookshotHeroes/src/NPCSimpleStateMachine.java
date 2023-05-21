import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class NPCSimpleStateMachine {
    private double _time = 0;
    public final double PATROL_REACTION_TIME = 0.5;
    public final double SEEK_REACTION_TIME = 0.1;
    public NPCStates State = NPCStates.Patrol;

    public GridCell Execute(double dt, IWorld world, IWorldObject npc){
        GridCell nextCell = null;
        if (npc.WhoAmI() == WorldObjectType.Minotaur) {
            var minotaur = (Minotaur)npc;
            if (State == NPCStates.Patrol) {
                if (_time > PATROL_REACTION_TIME) {
                    _time = 0;
                    Random r = new Random();
                    var nextMove = r.nextInt(1, 5);
                    if (nextMove == 1) {
                        nextCell = minotaur.Move(KeyEvent.VK_UP);
                    } else if (nextMove == 2) {
                        nextCell = minotaur.Move(KeyEvent.VK_RIGHT);
                    } else if (nextMove == 3) {
                        nextCell = minotaur.Move(KeyEvent.VK_DOWN);
                    } else if (nextMove == 4) {
                        nextCell = minotaur.Move(KeyEvent.VK_LEFT);
                    }
                    minotaur.CheckObjectCollision(nextCell);
                } else {
                    _time += dt;
                }
            } else if (State == NPCStates.Seek) {
                if (_time > SEEK_REACTION_TIME) {
                    _time = 0;
                    var player = GetClosestPlayer(world, npc);
                    nextCell = DoShortestMovement(world, npc, player);
                    minotaur.CheckObjectCollision(nextCell);
                } else {
                    _time += dt;
                }
            }
            // Check players range
            CheckPlayersRange(world, npc, Minotaur.SIGHT);
        }
        return nextCell;
    }

    public void CheckPlayersRange(IWorld world, IWorldObject npc, int sight){
        double min = Double.MAX_VALUE;
        for (IWorldObject object : world.GetObjects()) {
            if (object.WhoAmI() == WorldObjectType.Player) {
                var diffX = object.GetOccupiedCells()[0].Column - npc.GetOccupiedCells()[0].Column;
                var diffY = object.GetOccupiedCells()[0].Row - npc.GetOccupiedCells()[0].Row;
                var dist = Math.sqrt(diffX * diffX + diffY * diffY);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        if (min <= sight) {
            State = NPCStates.Seek;
        } else {
            State = NPCStates.Patrol;
        }
    }

    public IWorldObject GetClosestPlayer(IWorld world, IWorldObject npc){
        double min = Double.MAX_VALUE;
        IWorldObject player = null;
        for (IWorldObject object : world.GetObjects()) {
            if (object.WhoAmI() == WorldObjectType.Player) {
                var diffX = object.GetOccupiedCells()[0].Column - npc.GetOccupiedCells()[0].Column;
                var diffY = object.GetOccupiedCells()[0].Row - npc.GetOccupiedCells()[0].Row;
                var dist = Math.sqrt(diffX * diffX + diffY * diffY);
                if (dist < min) {
                    min = dist;
                    player = object;
                }
            }
        }
        return player;
    }

    public GridCell DoShortestMovement(IWorld world, IWorldObject npc, IWorldObject player){
        GridCell nextCell = null;
        double min = Double.MAX_VALUE;
        var minIdx = 0;

        var currentCell = npc.GetOccupiedCells()[0];
        var option1 = new GridCell(currentCell.Row, currentCell.Column - 1);
        var option2 = new GridCell(currentCell.Row + 1, currentCell.Column);
        var option3 = new GridCell(currentCell.Row, currentCell.Column + 1);
        var option4 = new GridCell(currentCell.Row - 1, currentCell.Column);
        var options = new ArrayList<GridCell>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        for (int i = 0; i < options.size(); i++){
            var diffX = player.GetOccupiedCells()[0].Column - options.get(i).Column;
            var diffY = player.GetOccupiedCells()[0].Row - options.get(i).Row;
            var dist = Math.sqrt(diffX * diffX + diffY * diffY);
            if (dist < min) {
                min = dist;
                minIdx = i;
            }
        }

        if (minIdx == 0) {
            nextCell = npc.Move(KeyEvent.VK_LEFT);
        } else if (minIdx == 1) {
            nextCell = npc.Move(KeyEvent.VK_DOWN);
        } else if (minIdx == 2) {
            nextCell = npc.Move(KeyEvent.VK_RIGHT);
        } else if (minIdx == 3) {
            nextCell = npc.Move(KeyEvent.VK_UP);
        }

        return nextCell;
    }
}
