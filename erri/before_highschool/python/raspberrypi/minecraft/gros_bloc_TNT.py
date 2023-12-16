from mcpi.minecraft import Minecraft

mc = Minecraft.create()

x, y, z = mc.player.getPos()

taille_cube_de_blocs =15

mc.setBlocks(x+1, y+1, z+1, x+taille_cube_de_blocs+1, y+taille_cube_de_blocs+1, z+taille_cube_de_blocs+1, 46, 1)
