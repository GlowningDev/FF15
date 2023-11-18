package dev.glowning

import dev.glowning.commands.SummonerCommand
import dev.glowning.commands.utils.CommandBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import no.stelar7.api.r4j.basic.APICredentials
import no.stelar7.api.r4j.impl.R4J

fun main(args: Array<String>) {
    FF15.main(args[0], args[1], args[2])
}

class FF15 private constructor() {
    lateinit var discord: JDA private set
    lateinit var riot: R4J private set

    companion object {
        private var instance: FF15? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FF15().also { instance = it }
            }

        fun main(discordToken: String, lolApiKey: String, tftApiKey: String) {
            val ff15 = getInstance()
            ff15.init(discordToken, lolApiKey, tftApiKey)
        }
    }

    private fun init(discordToken: String, lolApiKey: String, tftApiKey: String) {
        discord = JDABuilder.createDefault(discordToken).build()
        riot = R4J(APICredentials(lolApiKey, null, tftApiKey, null, null))

        val commands = listOf(
            SummonerCommand()
        )

        commands.forEach { command ->
            val annotation = command::class.annotations.find { it is CommandBuilder } as CommandBuilder
            discord.addEventListener(command)
            discord.upsertCommand(annotation.name, annotation.description)
                .addOptions(
                    annotation.optionsName.mapIndexed { index, name ->
                        OptionData(annotation.optionsType[index], name, annotation.optionsDescription[index], annotation.optionsRequired[index])
                    }
                )
                .queue()
            println("Registered command ${annotation.name}")
        }
    }
}