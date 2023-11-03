package dev.glowning

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
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
    }
}