Sword Displays
---

![](https://raw.githubusercontent.com/sokratis12GR/Sword-Displays/master/sworddisplay_logo.png "Sword Displays")

[![](https://cf.way2muchnoise.eu/title/sword-displays_Get_Today!.svg)](https://www.curseforge.com/minecraft/mc-mods/sword-displays)


## Crafting Recipe:

![](https://raw.githubusercontent.com/sokratis12GR/Sword-Displays/master/recipes.png "Sword Display Recipe")

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
