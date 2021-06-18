package carpetaddons;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.*;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */

@SuppressWarnings("CanBeFinal")
public class CarpetAddonsSettings
{
    private static class projectileRaycastLengthValidator extends Validator<Integer> {

    @Override
    public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
        return newValue >= 0 ? newValue : null;
    }

    @Override
    public String description() {
        return "You must choose a value greater or equal to 0";
    }

    }
    @Rule(
            desc = "Changes the distance projectiles check for collisions. If set to 0 all Blocks to the destination will be checked which is the Vanilla behaviour.",
            extra = {"This reduces lag for fast projectiles. In 1.12 the value was 200."},
            category = {EXPERIMENTAL,OPTIMIZATION, "carpetaddons"},
            options = {"0","200"},
            strict = false,
            validate = {projectileRaycastLengthValidator.class}
    )
    public static int projectileRaycastLength = 0;


    @Rule(
            desc = "Combines the duration of splash potions",
            category = {SURVIVAL, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean stackingSplashPotions = false;

    @Rule(
            desc = "Backports 1.12 flint and steel behavior. Flint and steel can be used for updating observers / buds",
            category = {FEATURE, SURVIVAL, "carpetaddons"}
    )
    public static boolean oldFlintAndSteelBehavior = false;

    @Rule(
            desc = "Potion can be used instantly",
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
    public static boolean keepEnderPearlsTicked = false;


    @Rule(
            desc = "Places the mined block in the player inventory when sneaking",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean carefulBreak = false;
}
