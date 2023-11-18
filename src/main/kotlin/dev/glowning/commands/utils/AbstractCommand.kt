package dev.glowning.commands.utils

import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionMapping

abstract class AbstractCommand : ListenerAdapter() {
    lateinit var event: SlashCommandInteractionEvent
    lateinit var user: User
    lateinit var commandName: String
    lateinit var commandOptions: List<OptionMapping>

    abstract override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent)
    abstract fun execute()
}