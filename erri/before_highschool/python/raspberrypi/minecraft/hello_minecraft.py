from mcpi.minecraft import Minecraft

mc = Minecraft.create()


position = mc.player.getPos()

mc.postToChat("notre position est : " + str(position))