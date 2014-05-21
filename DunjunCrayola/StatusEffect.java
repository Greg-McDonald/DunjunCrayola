
/**
 * Write a description of class StatusEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class StatusEffect
{
    private int numCycles; //Used to determine the time for which an effect lasts.
    private double strength;
    private Actor actorReference;
    
    public StatusEffect()
    {
        numCycles = 1;
        strength = 0;
        actorReference = null;
    }
    
    public void addEffect(Actor actor)
    {
        if(actor != null)
        {
            actorReference = actor;
            beginEffect();
        }
    }
    
    public void removeEffect()
    {
        if(actorReference != null)
        {
            endEffect();
            actorReference = null;
        }
    }
    
    public abstract void beginEffect(); //Called when the StatusEffect is first added to an Actor
    public abstract void endEffect(); //Called when a StatusEffect is removed from an Actor
    
    public final void update() //Called every time a "turn" or movement occurs, should be overriden
    {
        numCycles--;
        if(actorReference != null)
            affectActor();
    }
    
    public abstract void affectActor(); //Called every update, actually changes the Actor associated with the effect
    
    public int getCyclesRemaining(){return numCycles;}
    public void setCyclesRemaining(int numCycles){this.numCycles = numCycles;}
    private Actor getActorReference(){return actorReference;}
    
    public boolean isValid(){return numCycles > 0;}
}
