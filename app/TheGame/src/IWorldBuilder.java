/****************************************************************************************
 * Interface for game world builder.
 ****************************************************************************************/
public interface IWorldBuilder {
    IWorld Build(TheGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options);
}
