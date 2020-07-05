Sword Displays
---

![](https://i.imgur.com/BhNFAPN.png "Sword Displays")

[![](http://cf.way2muchnoise.eu/full_sword-displays_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/sword-displays)
[![](http://cf.way2muchnoise.eu/versions/sword-displays.svg)](https://www.curseforge.com/minecraft/mc-mods/sword-displays)

## Crafting Recipes:

![](https://i.imgur.com/pGCvCtB.png "Sword Case Recipe")
![](https://i.imgur.com/OqDhlsF.png "Sword Display Recipe")

## Comparator Caculations

```java
        // x = maxDamage : (maxDamage - currentDamage)
        // if (x > 15) x = 14;
        // if (x == 15) x = 13;
        // return 15 / x
        int x = stack.getMaxDamage() / (stack.getMaxDamage() - stack.getDamage());
        x = x > 15 ? 14 : x == 15 ? 13 : x;
        return 15 / x;
```
