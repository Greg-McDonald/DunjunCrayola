
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
    private String name;
    private Actor actorReference;
    
    public StatusEffect()
    {
        numCycles = 1;
        strength = 0;
        name = "Unidentified Effect";
        actorReference = null;
    }
    
    public StatusEffect(int effectStrength, int cycleDuration)
    {
        numCycles = cycleDuration;
        strength = effectStrength;
        actorReference = null;
    }
    
    public void addEffectToActor(Actor actor)
    {
        if(actor != null)
        {
            actor.getStatusEffects().add(this);
            actorReference = actor;
            beginEffect();
        }
    }
    
    public void removeEffectFromActor()
    {
        if(actorReference != null)
        {
            endEffect();
            actorReference.getStatusEffects().remove(this);
            actorReference = null;
        }
    }
    
    public abstract void beginEffect(); //Called when the StatusEffect is first added to an Actor
    public abstract void endEffect(); //Called when a StatusEffect is removed from an Actor
    
    public final void update() //Called every time a "turn" or movement occurs, override affectActor to change behavior
    {
        if(actorReference != null && numCycles >= 0)
        {
            affectActor();
            numCycles--;
        }
        else if(numCycles < 0)
            removeEffectFromActor();
    }
    
    public abstract void affectActor(); //Called every update, actually changes the Actor associated with the effect
    
    public String getName(){return name;}
    public void setName(String effectName){if(effectName != null)name = effectName;else name = "Unidentified Effect";}
    public int getCyclesRemaining(){return numCycles;}
    public void setCyclesRemaining(int numCycles){this.numCycles = numCycles;}
    public double getEffectStrength(){return strength;}
    public void setEffectStrength(double effectStrength){this.strength = effectStrength;}
    protected Actor getActorReference(){return actorReference;}
    
    public boolean isValid(){return numCycles > 0;}
    
    public boolean equals(StatusEffect s)
    {
        return s.getCyclesRemaining() == this.getCyclesRemaining() && s.getEffectStrength() == this.getEffectStrength() && s.getActorReference().equals(this.getActorReference());
    }
}
