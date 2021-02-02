# Carpet Addons

If you need help dm me on discord: Timstario#3141

## Features
Use `/carpet <rulename> true` to enable:

### projectileRaycastLength
Changes the distance projectiles check for collisions. If set to 0 all Blocks to the destination will be checked which is the Vanilla behaviour.

This reduces lag for fast projectiles. In 1.12 the value was 200.
* Type: `int`  
* Default value: `0`  
* Suggested options: `0`, `200`  
* Categories: `OPTIMIZATION`, `EXPERIMENTAL`

### carefulBreak
Places the mined block in the player inventory when sneaking.  
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `SURVIVAL`, `FEATURE`, `EXPERIMENTAL`

### keepEnderPearlsTicked
Ender Pearls are ticking the whole time - Ender Pearl loading chunks.  
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `EXPERIMENTAL`

### donkeyDupeFeature 
Enables old multiplayer donkey / llama dupe bug.
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `SURVIVAL`, `EXPERIMENTAL`

### instantPotions
Potion can be used instantly.  
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `SURVIVAL`

### stackingPotions
Combines the duration of potions.  
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `SURVIVAL`

### stackingSplashPotions
Combines the duration of splash potions.  
* Type: `boolean` 
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `SURVIVAL`

### oldFlintAndSteelBehavior
Backports 1.12 flint and steel behavior. Flint and steel can be used for updating observers / buds.  
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `SURVIVAL`

## License

MIT
