/****************************************************************************************
 * Interface for game world builder.
 ****************************************************************************************/
public interface IWorldBuilder {
    IWorld Build(HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options, ILevel level);
}
