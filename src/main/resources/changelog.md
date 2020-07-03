## 1.15.2-1.1.1

* Added an updateJSON for easier updates.

## 1.15.2-1.1.0

* Added comparator input depending on the weapon's damaged state:
  * If the Sword Display is empty, it will emit 0 strength.
  * if the Sword Display contains an undamaged sword, it will always emit 15 strength.
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