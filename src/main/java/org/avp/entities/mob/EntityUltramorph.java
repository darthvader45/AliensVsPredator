package org.avp.entities.mob;

import org.avp.Sounds;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityUltramorph extends EntityXenomorph implements IMob
{
    public EntityUltramorph(World world)
    {
        super(world);

        this.jumpMovementFactor = 0.02F;
        this.experienceValue = 100;
        this.setSize(1.5F, 4F);
        this.ableToClimb = false;
        this.isDependant = false;
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setAvoidsWater(true);
        this.addStandardXenomorphAISet();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(230.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4700000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.5D);
    }

    @Override
    protected void dropRareDrop(int chance)
    {
        super.dropRareDrop(chance);
    }

    @Override
    protected String getHurtSound()
    {
        return Sounds.SOUND_ALIEN_HURT.getKey();
    }

    @Override
    protected String getLivingSound()
    {
        return Sounds.SOUND_ALIEN_LIVING.getKey();
    }

    @Override
    protected String getDeathSound()
    {
        return Sounds.SOUND_ALIEN_DEATH.getKey();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
    }
}
