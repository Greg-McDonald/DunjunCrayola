public class TakeTurnButton extends Button
{
    public TakeTurnButton(GamePanel gamePanel)
    {
        super(gamePanel);
    }
    
    public void performClickAction()
    {
        getGamePanelReference().takeTurn();
    }
}
