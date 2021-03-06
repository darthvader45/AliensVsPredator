package org.avp.event.client;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import java.util.ArrayList;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityQueen;
import org.lwjgl.opengl.GL11;

import com.arisux.amdxlib.lib.client.render.Draw;
import com.arisux.amdxlib.lib.client.render.OpenGL;
import com.arisux.amdxlib.lib.game.Game;
import com.arisux.amdxlib.lib.world.CoordData;
import com.arisux.amdxlib.lib.world.entity.Entities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.world.WorldEvent;

public class BossBarEvent
{
    public static final BossBarEvent   instance = new BossBarEvent();
    public ArrayList<EntityLivingBase> bosses;

    public BossBarEvent()
    {
        bosses = new ArrayList<EntityLivingBase>();
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        this.bosses.clear();
    }

    @SubscribeEvent
    public void clientTick(ClientTickEvent event)
    {
        if (Game.minecraft().thePlayer != null)
        {
            ArrayList<EntityLivingBase> bossesToRemove = new ArrayList<EntityLivingBase>();

            for (EntityLivingBase boss : bosses)
            {
                if (boss.isDead)
                {
                    bossesToRemove.add(boss);
                }
            }

            for (EntityLivingBase boss : bossesToRemove)
            {
                this.bosses.remove(boss);
            }

            if (Game.minecraft().thePlayer.worldObj.getWorldTime() % 40 == 0)
            {
                @SuppressWarnings("unchecked")
                ArrayList<EntityQueen> queens = (ArrayList<EntityQueen>) Entities.getEntitiesInCoordsRange(Game.minecraft().thePlayer.worldObj, EntityQueen.class, new CoordData(Game.minecraft().thePlayer), 32);

                for (EntityQueen queen : queens)
                {
                    if (!bosses.contains(queen))
                    {
                        bosses.add(queen);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void renderTick(RenderGameOverlayEvent event)
    {
        if (event.type == ElementType.BOSSHEALTH)
        {
            OpenGL.pushMatrix();
            {
                float scale = 0.5F;
                OpenGL.scale(scale, scale, scale);

                for (EntityLivingBase boss : bosses)
                {
                    int index = bosses.indexOf(boss);
                    // TODO: Optimize this. It's inefficient.
                    // this.drawBossBar(boss, index, 0, 0);
                }
            }
            OpenGL.popMatrix();
        }
    }

    public void drawBossBar(EntityLivingBase boss, int index, int posX, int posY)
    {
        int tW = 233;
        int tH = 50;
        int offset = tW * 30 / 100;
        int health = (int) (boss.getHealth() * 100 / boss.getMaxHealth());
        int color = health < 50 ? health < 20 ? 0xFFFF0000 : 0xFFFFCC00 : 0xFF00FF00;
        String label = String.format("%s [%s]", boss.getCommandSenderName(), health + "%%");

        OpenGL.pushMatrix();
        {
            OpenGL.enable(GL11.GL_BLEND);
            OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            OpenGL.color4i(0xFFFFFFFF);
            AliensVsPredator.resources().QUEEN_BOSS_BAR.bind();
            posX = posX + (index * (tW));
            OpenGL.color4i(color);
            Draw.drawQuad(posX + (offset / 2), posY, (tW - offset) * health / 100, tH, 0, 0.15F, 0.85F, 0F, 0.5F);
            OpenGL.color(1F, 1F, 1F, 1F);
            Draw.drawQuad(posX, posY, tW, tH, 0, 0F, 1F, 0.5F, 1F);
            Draw.drawStringAlignCenter(label, posX + (tW / 2), posY + 16, color);

            Draw.drawStringAlignCenter((int) boss.posX + "/" + (int) boss.posY + "/" + (int) boss.posZ, posX + (tW / 2), posY + 26, 0xFFFFFFFF);

            OpenGL.disable(GL11.GL_BLEND);
        }
        OpenGL.popMatrix();
    }
}
