package carpetaddons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */

@SuppressWarnings("CanBeFinal")
public class CarpetAddonsSettings
{

    @Rule(
            desc = "instant potions",
            category = {FEATURE, SURVIVAL, "carpetaddons"}
    )
    public static boolean instantPotions = false;

    @Rule(
            desc = "Combines the duration of potions",
            category = {SURVIVAL, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean stackingPotions = false;

    @Rule(
            desc = "Projectiles are ticked the whole time - Projectile loading chunks",
            category = {FEATURE, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean keepProjectilesTicked = false;


    @Rule(
            desc = "Places the mined block in the player inventory when sneaking",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean carefulBreak = false;
}
