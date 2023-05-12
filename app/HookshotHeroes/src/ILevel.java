public interface ILevel {
    void RenderLevel();
    ILevel GetNextLevel();
    ILevel GetPreviousLevel();
    GridCell[] GetTopStartingPos();
    GridCell[] GetBottomStartingPos();
    GridCell GetExitGrid();
    GridCell GetEntryGrid();
    LevelStartPos GetStartPos();
    void SetStartPos(LevelStartPos pos);
}
