from mcpi.minecraft import Minecraft

mc = Minecraft.create()


position = mc.player.getPos()

mc.postToChat("notre position est : " + str(position))

x, y, z = mc.player.getPos()
# mc.setBlocks(x+1, y+1, z+1, x+11, y+11, z+11, 46, 1)
# mc.setBlock(x+1, y, z, 46, 1) # Création d'un block de TNT
taille_cube_de_blocs = 10
mc.setBlocks(x+1, y+1, z+1, x+taille_cube_de_blocs+1, y+taille_cube_de_blocs+1, z+taille_cube_de_blocs+1, 46, 1)