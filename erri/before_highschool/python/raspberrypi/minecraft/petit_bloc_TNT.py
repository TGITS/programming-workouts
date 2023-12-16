from mcpi.minecraft import Minecraft

mc = Minecraft.create()

x, y, z = mc.player.getPos()
mc.setBlock(x+1, y, z, 46, 1) # Création d'un block de TNT