
/**
 * Write a description of class DamageOverTimeEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DamageOverTimeEffect extends StatusEffect
{
    public DamageOverTimeEffect()
    {
        super();
    }
    
    public DamageOverTimeEffect(int effectStrength, int cycleDuration)
    {
        super(effectStrength, cycleDuration);
    }
    
    public void beginEffect(){}
    public void endEffect(){}
    
    public void affectActor()
    {
        (this.getActorReference()).dealMagicDamage((int)this.getEffectStrength());
    }
}
