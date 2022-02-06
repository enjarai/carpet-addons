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
    @Rule(
            desc = "Changes the distance projectiles check for collisions. If set to 0 all Blocks to the destination will be checked which is the Vanilla behaviour.",
            extra = {"This reduces lag for fast projectiles. In 1.12 the value was 200."},
            category = {EXPERIMENTAL,OPTIMIZATION, "carpetaddons"},
            options = {"0","100","200"},
            strict = false,
            validate = Validator.NONNEGATIVE_NUMBER.class
    )
    public static int projectileRaycastLength = 0;

//    @Rule(
//            desc = "Enables old donkey / llama dupe bug.",
//            category = {SURVIVAL, EXPERIMENTAL,FEATURE, "carpetaddons"}
//    )
//    public static boolean donkeyDupeFeature = false;


    @Rule(
            desc = "Backports 1.12 flint and steel behavior. Flint and steel can be used for updating observers / buds",
            category = {FEATURE, SURVIVAL, "carpetaddons"}
    )
    public static boolean oldFlintAndSteelBehavior = false;


//    @Rule(
//            desc = "Projectiles are ticked the whole time - Projectile loading chunks",
//            category = {FEATURE, EXPERIMENTAL, "carpetaddons"}
//    )
//    public static boolean keepEnderPearlsTicked = false;


    @Rule(
            desc = "Places the mined block in the player inventory when sneaking",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean carefulBreak = false;

    @Rule(
            desc = "Don't require the player to sneak to use carefulBreak",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, "carpetaddons"}
    )
    public static boolean carefulBreakNoSneak = false;
}
