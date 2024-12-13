# Ressources d'aide pour le jour 12

* https://www.reddit.com/r/adventofcode/comments/1hcdnk0/2024_day_12_solutions/

> part 2 , number of edges is equal to number of corners a block can have up to 4 corners

* https://github.com/Fadi88/AoC/tree/master/2024/day12
  * https://github.com/Fadi88/AoC/blob/master/2024/day12/code.py

* [2024 Day 12 (Part 2)] What kind of algorithm did you use on part 2? : https://www.reddit.com/r/adventofcode/comments/1hcpyic/comment/m1q1nrj/?utm_source=share&utm_medium=web3x&utm_name=web3xcss&utm_term=1&utm_content=share_button

> This was actually really pretty easy when you see the structure. No counting corners (or any dealing with corners) necessary. Regions are point sets, and the perimeter is defined by computing neighbors that are not in the point set.

* https://github.com/mtravers/aoc2024/blob/main/src/aoc2024/day12.clj

* https://topaz.github.io/paste/#XQAAAQB+DgAAAAAAAAA0m4poOBKGZ3IPXs+Mp5VlxQiwAdbCXtdhaMIRfVdMwwdsjE/GR0cVn8s+2+dxVl/FBBnwMqYzQGZXNxc5pLLUvpIAjXi77h0cIcm3ghSzLypveZLMfIGNEQZh/UV+ZeYVyy1NVs96cs9nQcFsDRd8Vmd59/vhkCV06h0XQuAuUynWKPdXlJuHzsHa7nlJ9nwtdzuu2GwaKeAiKmR4ZPDdKL7ObHpCVTelfOkCahUuTh5vQ0sF56wCetQvaoOIIlI/6UaZUzcohnnem6z1ymQLK1Enmf3mssAGJ0TEcARJKrfKbqZz0inevkHNiq2ErYi5grkXIxcuxFPp5iIfDiVArEt/lDxHJ2DLFE0KYtuZHItycJFuK/A3KlqqsP/N2PhY9iIR6w4MTWljcUwtN4vvia3weV2a8NRoJ1lZd5j3qumX/nihIoQUuU+0jblAcOn9HftZexNsUAVrA/C1xY1wNdRrCgfwyCXCuJAHEkaoUAIlkNhBlUlOTgzrLAnAgoQS11ug4w0MQ7/zXZgL0JKOXJOZz19EP5MN4HGav9+/KBPBC9OBrqxFbhMdSzAgoTTygBoYlCXgYwXKKh+NNALdDJg7BMlbIQ1My2MyD3OhFdz84FYV0bByA8tF6d8E5iAnvVHY6Led+cxVCxwdoJLjVbFLaTFQhM9AM/1nMJOIGevvcklyCEMV8+pW6WT27xNKV99Tdc76Y2q7f6ur+vYRjGe4x+nf5LQuM4NMrXiEr0d7FMvUkOOEZkbme14IqzgkL0bnwEZmYY7z2osDK2DLjGYeUhKBBUajgJq1xbdzLOOmXM6hDJE3g3j65m0n6QTtVW8ml4oMWINkURRYms5GUeAdqzeIjfATNU2FX9HCejRK/EX9Xg7DzNbAnl7Y+B1YiF9wvns7nLXDMZNx8VIzYnFIQpJ9ZaprPC8wmnzq76y5cunB8ZHNCpKzi5MDtwz8tl5lh2wfiZ+nysvuBM3hmFBjDTBRv83ixVg/0keDz6G1djbqkhUTimsJPk/UyLepVBOwmFHCg6Ist5v1csVdsNg4N85fyJfmk6qUsoyu4L8oGM4hIvWT+VQoMOelXLTlFlQhkYZxOKT2ZXiwc1jaZBefCxnVdGm8fwyXc1fTALhwTh6pyXEBh9mxS9oATd4S+7VO7hUfPGj10FnMkWCDgAA8Pa0StxH1GZziOZxrzR8tkGIid/7ASFPxykuZC/XwbtiZZzqINv+eo+JDM8zzPg1ZERoABOSImlajHbrR8pDyCqv5rqk+4Liza2/165aGg39Q9yCUcnf3IayEJj7kAbkAgU4GMVyqwtkPMMXskhRy8X7HLr8Zr/hZxltmxrrrC3IpkVBALYjZut2nXo8SF43VW3hm4ATR3ZtFX5sBJ0sd/qkRqqRiJXFs8lXLGCzNJR6tyqreOOehkaWvpRtcu/VEDRBTk9bDnj2ciUmmimMY2Yb1Inf9EkT64KZOzAZfe+2SBqjOScHyC7ZecX3qOKABAezlq9Ka3k0vEdtwULNrykqTaE2IrblSxxjCp/ZxjdTwWHxI4JamPKHmhrLXv0ESjEkJDHmavRrvRJA0lk04vo4iH3jGQZFdCzquG2zSYbdDBuvOlx5Gm+tIRnsPP0O0AWN50ToMZV7SUbPXgQDSUsjfRVpiA50+qaX7gbIAE81QFSfTDSthFyCMY7v3PQDoma5Nf3V7LhCGuNUT3iMnBRhbqdtXZKWzCgHO4v+/E3uyrZ1f7x2Xx2vprCpCXv39iNa/
  * https://www.reddit.com/user/RiemannIntegirl/
  * Part 1 and 2 Together
  * Idea:
    * Convert grid to complex numbers (a theme this year for me!).
    * Loop through all locations on the grid.
    * Get the area/region unless we already calculated this one
    * Calculate the perimeter by accumulating legal neighbors that aren't within the current region.
    * Calculate the sides by tracing outside perimeters clockwise, starting at the bottom leftmost point, and always keeping the area to our right, and count the number of turns we take. We proceed similarly on the inside holes, except we start at the bottom rightmost point in the perimeter section, and keep the area to our right the whole time. Use complex numbers to keep track of direction of movement, and where the shape is (always to our right). Keep removing points from border points left to process until no new sections of border are left. I could have used only a direction of movement pointer, but having a "wall is to the right" complex number too is just easier. Also rotations are calculated using complex multiplication.

* https://pastebin.com/HkB52jNa

* https://topaz.github.io/paste/#XQAAAQB/GQAAAAAAAAARiEJGPfQWNG6xo4rUnrU/FzgTXmJOWQF53DaV6F4jcQsDsZs/mYVTr44+AFCYLJlk1pihiH8JoAwbgG5ONt1u+uYqiO3+69gDwD5xN1CFGot7keKS4ilM1S/Jn37vzrj6sGOPGA6tn5ddo1oNs7gftyV8tvTyxMjVFX9F5uudTuOk0kT78eYxf9xl+BITEN33Dz/GP1uzYVbU+esoj2pZTuBuYdMfyd5+evPXRfg097S3t6IGRcMJbsAki4oK6NUHqjPHdUhKCO3f06EevXA2Dfh1nmDEsZWdJUyto8WqJsHwpaazYfoi8tg2eNpH8GUPMCuEPpj8+EkNF4gZH/8vEZ3AuatP6JhUPKCpi9fetw1FB//fKDeVKgcFw/nSB6j3qPRsmGyq3IUKO9JP8XPsNFaEl/xer21l6QOJRERaCIqRqRyH7sVADv4bwbu/1/BHuc36lb7m6akuvATr8kJGmQKUhrcaDItrCb769cod1YJxSp1cNYdKbs+mkgTUauH5nxWVbO+RU9fjhbu1WxZjJxybigImPjvyZleenXetvviW3VKXHhtpwaWq+mdoJO7YGssIX4acKh+DaP5wWnQQgzLT4uUPwIKXFx0gLD1LmA6Yx0agLkndc6AdapFW3YBG0U7QsH/BykplnRaTMCP46fRakfg2lG0zRZag+8y6NC7T3/Nfbr7hE8KDV42NMJ/ihZro9v+P9YhJ26gezy0eucJcr9iB2aUcDcfzIZULkxjsxKubLfgpKIcML159aoQsZhMxTc+Iq2Itjt1mSSUSwmQwRBEqjbrv0Z/3mZrKp7r/8WUOxo94qvhykMqGinjcqICA0uLy2aNzQQE7SUvuZ7pxOgcQyosZugHsaYno0pq0Vwj+RDXj92uXOmhXeII7qp+nhWA7Cndvws9B0/xYVpONyHE7yTHbUnMUh3GEiiLvcLOW9oeKlXciH5M5VJ7kz/PpW2YtQF78tcr103lr+rXq38CRby8FGATW9d2+wd5XpcAFlYo1hA3SnZaqHg/6QxlOzhwFrcl9yYDYpzMNteTPS9ZeugDsT16vXQZyz/FCElV928pjvJdSbpvUVnLft+Bi5C3OHxApiEUnSOxOG6hbi0YPrRO8qI3v7VmaVQDkwT2I+xblgG7U5px0Yde3NMq5xWaYgwjDcg9Adegw8s7xyC9GwgBsnU+//PtDqZk6OGhjufJFlTuS6oaHHL7uCUeTNolxLtWGfMHsbVhE3iqDdI/pncemOcIE8l1eHuhohMl3dlfuPIfcyKb9C7N2sUf//5SkRq9dgQ1jlVqw80GVdXzsgM3IwzLAGLmRqHLUOkWyfu34y067hQ5b6rhmkeukhE2zny7t33gJttD5/6CvA2kx2Z1sQIzcNo5lQ1b69qJ1k2fgQCcemPXS3iwLfIKJvKq/G1BJDfyNPZOwM3DRumDg9Ghf18+zai6M7cFnVdUKlY3B2ivpxY/wfITeR0VR1FV98m99dz/NQmXm6ggXSO3kdU+EqJIPiXItP6rNLM8dc8mODkMXa4ZvEQ9weYdeXUL8rlbPxOk+slLdMp69YMJ/aMFseE4TWoM6C7FOBKS9QXFFQLX+2BK8XAa/l1G8iko72VcA8R14vBDivKCbQc9Fkseod351NpG0w44M8aYD20zRdB/941trmAKRbD41TVxsODEOzag07Zoz8A4xQUt06Ml7XlhdXuusd3uRthfT7zmCO4ErH+En1C54esMJPtRtxYYAhFnACR/ViCY2OS10lXV48/lcu33L7AQL35gOk3CuYaCvSGMm16SvCiurUAakVIAmmFW/qa1hzMWh5dYbcrKR/78gCGA=

* https://topaz.github.io/paste/#XQAAAQDyCQAAAAAAAAAyGUj/TzqOZqwuJtj8ertUYvhKgwDkvnlzWMJRptds+8uERO2pFPX/RN1z5KwTctGjw+vuXQoNzkXnnPkLX1jIpL4qUnROmAXxVM6Yse4Bk6n5qM5TK1le0xjLA1xUpOpOcT9HYR73eDuu+USIM/Fdp3p7/I54jwx2+jLJIFyWdo22BKsoIFbj39g+45G6+maMitOUDToZJLqkFTdblyW2Qs5P83pSfY0bKOrdTHvtzx+OQ44G68anL4ygkeBZzvd8K1xoLjpx+Y/gBj0Y5p7TlnwhIY2TXn+3LkZjKYl8UZk1Qb3exx/AXaxrY9bmY+oe+Tvspn7rpsMbSC1v9QZ7sEQ3GTTY83EPVTHgQpN0ZYX1hqgK+FJYCpKl1WLMAeBNivGkXIqrnXAMV7W0if9UUuL3y1RcRWEzL16S2AoutHCKdqpuLLXys1zPhGDvULV+RgxdSyj5lfKxBZOtX3KyhwMpaRF7+Z2/7OFhqYvnjaALBpKshUnBAsYgv7r6d0X2t+UasBdFML3XifmIkfdHWUzH74nJGEcyUQzS931er/9HUmUCVfUTxu6WP5YZY5zhsFtm2/4ooMeRl0qu/N+xkG9mGDpDkKUVEXx76khV7dGCYGHvicLLjjc4j90u7bruUuVufYuypYe48yuuAI1u43fmjzCGNTNwoNqJZpQCFGCQm7LmOhp6MggtLO3djEyCHNm+t9GO+pEcaxpROQy4bY0W5am33MU4VDqV7UkLkG6DHiFDrEvNL2xaZ+iPqn67a1LuE5N2nQLvK4ZRMD6MJUkZ/YZE3agZ7lSlCHC1TaceDHBKjDhxtnrl45v2ULKiaAig9iS5nEQW2bkrqWXZxh17Bw9WcTDVWfLV5TSEsv77RYvh
  * Solution pas inintéressante

## With Shapely

* https://www.reddit.com/user/whyrememberpassword/

```python
import sys
from shapely import box, MultiPolygon
from collections import defaultdict

shapes = defaultdict(MultiPolygon)

inp = [s.rstrip('\n') for s in sys.stdin]
for r, line in enumerate(inp):
    for c, color in enumerate(line):
        shapes[color] = shapes[color].union( box(r, c, r + 1, c + 1) )

res = 0
res2 = 0
for color, polys in shapes.items():
    for poly in polys.geoms if hasattr(polys, "geoms") else [polys]:
        poly = poly.simplify(tolerance=1e-1)
        sides = len(poly.exterior.coords) - 1
        for interior in poly.interiors:
            sides += len(interior.coords) - 1
        res += poly.area * poly.length
        res2 += poly.area * sides

print(int(res))
print(int(res2))
```

