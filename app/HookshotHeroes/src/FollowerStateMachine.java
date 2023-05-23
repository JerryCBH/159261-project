public class FollowerStateMachine extends NPCSimpleStateMachine implements IStateMachine{
    public FollowerStateMachine(){
        super.PATROL_REACTION_TIME = 0.5;
        super.SEEK_REACTION_TIME = 0.15;
        super.SIGHT = 19;
    }
}
