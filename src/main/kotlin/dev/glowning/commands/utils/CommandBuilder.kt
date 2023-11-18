package dev.glowning.commands.utils

import dev.glowning.utils.Permission
import net.dv8tion.jda.api.interactions.commands.OptionType

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CommandBuilder(
    val name: String,
    val description: String = "No description yet.",
    val permission: Permission = Permission.USER,
    val optionsName: Array<String> = [],
    val optionsDescription: Array<String> = [],
    val optionsType: Array<OptionType> = [],
    val optionsRequired: BooleanArray = [],
)
