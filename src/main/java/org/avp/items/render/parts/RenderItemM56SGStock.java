package org.avp.items.render.parts;

import org.avp.items.render.ItemRendererGroup;

import com.arisux.amdxlib.lib.client.Model;
import com.arisux.amdxlib.lib.client.TexturedModel;
import com.arisux.amdxlib.lib.client.render.OpenGL;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemM56SGStock extends ItemRendererGroup
{
    public RenderItemM56SGStock(TexturedModel<? extends Model> model, ModelRenderer... modelRenderers)
    {
        super(model, modelRenderers);
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        super.renderInInventory(item, data);

        OpenGL.pushMatrix();
        {
            float glScale = 32F;
            OpenGL.translate(8F, 8F, 0F);

            OpenGL.scale(glScale, glScale, glScale);
            OpenGL.translate(-0.1F, -0.075F, 0.2F);
            this.renderPart();
        }
        OpenGL.popMatrix();
    }
}
