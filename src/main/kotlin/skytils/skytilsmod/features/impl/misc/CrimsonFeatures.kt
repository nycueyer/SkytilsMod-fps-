/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2022 Skytils
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package skytils.skytilsmod.features.impl.misc

import gg.essential.universal.UGraphics
import gg.essential.universal.UMatrixStack
import net.minecraft.entity.item.EntityArmorStand
import net.minecraftforge.client.event.RenderLivingEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import skytils.skytilsmod.Skytils

object KuudraFeatures {
    private val tentacleNametagStack = UMatrixStack()

    @SubscribeEvent(priority = EventPriority.LOWEST)
    fun beforeNametag(event: RenderLivingEvent.Specials.Pre<EntityArmorStand>) {
        if (Skytils.config.bigTentacleTag && "Tentacle" in event.entity.name) {
            UGraphics.GL.pushMatrix()
            tentacleNametagStack.push()
            tentacleNametagStack.scale(10f, 10f, 0f)
            tentacleNametagStack.applyToGlobalState()
        }
    }

    @SubscribeEvent
    fun afterName(event: RenderLivingEvent.Specials.Post<EntityArmorStand>) {
        if (Skytils.config.bigTentacleTag && "Tentacle" in event.entity.name) {
            tentacleNametagStack.pop()
            UGraphics.GL.popMatrix()
        }
    }

}