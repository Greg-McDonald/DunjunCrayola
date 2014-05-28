
/**
 * Write a description of class StatBoostEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatModificationEffect extends StatusEffect
{
    private Statistic modifiedStat;
    
    public StatModificationEffect()
    {
        this(0,1);
    }

    public StatModificationEffect(int effectStrength, int cycleDuration)
    {
        super(effectStrength, cycleDuration);
        modifiedStat = Statistic.ATTACK;
    }

    public Statistic getModifiedStatistic(){return modifiedStat;}
    public void setModifiedStatistic(Statistic modStat){this.modifiedStat = modStat;}
    
    public void beginEffect()
    {
        Actor actorReference = getActorReference();
        switch(modifiedStat)
        {
            case ATTACK:
                actorReference.setAttack(actorReference.getAttack() + (int)this.getEffectStrength());
                break;
            case MAGIC:
                actorReference.setMagic(actorReference.getMagic() + (int)this.getEffectStrength());
                break;
            case DEFENSE:
                actorReference.setDefense(actorReference.getDefense() + (int)this.getEffectStrength());
                break;
            case RESISTANCE:
                actorReference.setResistance(actorReference.getResistance() + (int)this.getEffectStrength());
                break;
            case HEALTH:
                actorReference.setHealth(actorReference.getHealth() + (int)this.getEffectStrength());
                break;
            case MAX_HEALTH:
                actorReference.setMaxHealth(actorReference.getMaxHealth() + (int)this.getEffectStrength());
                break;
            case MANA:
                actorReference.setMana(actorReference.getMana() + (int)this.getEffectStrength());
                break;
            case MAX_MANA:
                actorReference.setMaxMana(actorReference.getMaxMana() + (int)this.getEffectStrength());
                break;
            default:
                //Do Nothing
                break;
        }
    }

    public void endEffect()
    {
        Actor actorReference = getActorReference();
        switch(modifiedStat)
        {
            case ATTACK:
                actorReference.setAttack(actorReference.getAttack() - (int)this.getEffectStrength());
                break;
            case MAGIC:
                actorReference.setMagic(actorReference.getMagic() - (int)this.getEffectStrength());
                break;
            case DEFENSE:
                actorReference.setDefense(actorReference.getDefense() - (int)this.getEffectStrength());
                break;
            case RESISTANCE:
                actorReference.setResistance(actorReference.getResistance() - (int)this.getEffectStrength());
                break;
            case HEALTH:
                actorReference.setHealth(actorReference.getHealth() - (int)this.getEffectStrength());
                break;
            case MAX_HEALTH:
                actorReference.setMaxHealth(actorReference.getMaxHealth() - (int)this.getEffectStrength());
                break;
            case MANA:
                actorReference.setMana(actorReference.getMana() - (int)this.getEffectStrength());
                break;
            case MAX_MANA:
                actorReference.setMaxMana(actorReference.getMaxMana() - (int)this.getEffectStrength());
                break;
            default:
                //Do Nothing
                break;
        }
    }

    public void affectActor()
    {
        //Do Nothing
    }
}
