## 1.15.2-1.5.0

* Added an "owner" functionality
  * With this being added from now on, only the user who set the sword inside the block will be able to withdraw/break
    the block/change its modes.
  * There only exceptions are if the owner tag is null, or the player is in creative mode.

## 1.15.2-1.4.1

* Added Taiwanese translation

## 1.15.2-1.4.0

* Added Iron, Golden, Diamond and Emerald displays
* Added Right-Click functionality to reverse the direction that the sword is being rendered inside

## 1.15.2-1.3.0

* Added the rest of the wood variants: Acacia, Birch, Dark Oak, Jungle, Spruce
* The blocks should give the corresponding item variant when broken.
* The Sword Case recipe should now give you the corresponding recipe output.
* Updated the logo
* Changed the recipes, automated the recipe creation process & advancements.

## 1.15.2-1.2.1

* The prismarine recipes should now give you the prismarine versions of the display and the case.

## 1.15.2-1.2.0

* Added a wooden and a prismarine variant.
* Added sword cases, they are displays with a glass case around them.

## 1.15.2-1.1.1

* Added an updateJSON for easier updates.

## 1.15.2-1.1.0

* Added comparator input depending on the weapon's damaged state:
  * If the Sword Display is empty, it will emit 0 strength.
  * If the Sword Display contains an undamaged sword, it will always emit 15 strength.
  * If the Sword Display contains a damaged sword it will follow the following formula:
    ```java
        int x = stack.getMaxDamage() / (stack.getMaxDamage() - stack.getDamage());
        x = x > 15 ? 14 : x == 15 ? 13 : x;
        return 15 / x;
    ```
    What this means:
      * If the damage for the weapon is far too great, or if it is nearly broken the power output would be 1.
      * If the damage the weapon suffered is minimal or none at all, the power output would be 15 or 14.
      * Anything else will be calculated depending on how damaged the item is. 
* Added minimum required forge version to **1.15.2-31.2.22**
* It should work on servers now.

## 1.15.2-1.0.1

* Fixed a broken pack.mcmeta

## 1.15.2-1.0.0

* Initial 1.15.2 release