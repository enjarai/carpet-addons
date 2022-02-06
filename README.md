# Carpet Addons

My fork of carpet-addons updated to 1.18.1. 
Since this is mostly here for the carefulBreak rule (and I'm lazy), 
every rule that didn't work easily is removed.

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

### carefulBreakNoSneak
Don't require the player to sneak to use carefulBreak
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `FEATURE`, `EXPERIMENTAL`

### oldFlintAndSteelBehavior
Backports 1.12 flint and steel behavior. Flint and steel can be used for updating observers / buds.  
* Type: `boolean`  
* Default value: `false`  
* Required options: `true`, `false`  
* Categories: `FEATURE`, `SURVIVAL`

## License

MIT
