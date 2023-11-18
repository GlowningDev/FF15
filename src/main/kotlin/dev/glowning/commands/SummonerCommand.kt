package dev.glowning.commands

import dev.glowning.FF15
import dev.glowning.commands.utils.AbstractCommand
import dev.glowning.commands.utils.CommandBuilder
import dev.glowning.utils.Region
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType

@CommandBuilder(
    name = "summoner",
    description = "Get information about a summoner",
    optionsName = ["region", "name"],
    optionsDescription = ["The region of the summoner", "The name of the summoner"],
    optionsType = [OptionType.STRING, OptionType.STRING],
    optionsRequired = [true, true]
)
class SummonerCommand : AbstractCommand() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "summoner") return

        this.event = event
        this.user = event.user
        this.commandName = event.name
        this.commandOptions = event.options

        execute()
    }

    override fun execute() {
        val summonerAPI = FF15.getInstance().riot.loLAPI.summonerAPI
        val region = Region.getRegionByName(commandOptions[0].asString)
        val summonerName = commandOptions[1].asString

        if (region == null) {
            event.reply("Invalid region").setEphemeral(true).queue()
            return
        }

        val summoner = summonerAPI.getSummonerByName(region.leagueShard, summonerName)
        val embedBuilder = EmbedBuilder()
            .setTitle(summoner.name)
            .addField("Level", summoner.summonerLevel.toString(), true)
            .setThumbnail("https://ddragon.leagueoflegends.com/cdn/11.16.1/img/profileicon/${summoner.profileIconId}.png")
            .setFooter(region.fullName, null)

        event.replyEmbeds(embedBuilder.build()).queue()
    }
}