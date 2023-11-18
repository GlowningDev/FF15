package dev.glowning.utils

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard

enum class Region(val region: String, val regionShard: RegionShard, val leagueShard: LeagueShard, val fullName: String) {
    EUW("euw", RegionShard.EUROPE, LeagueShard.EUW1, "Europe West"),
    EUNE("eune", RegionShard.EUROPE, LeagueShard.EUN1, "Europe Nordic & East"),
    TR("tr", RegionShard.EUROPE, LeagueShard.TR1, "Turkey"),
    RU("ru", RegionShard.EUROPE, LeagueShard.RU, "Russia"),
    NA("na", RegionShard.AMERICAS, LeagueShard.NA1, "North America"),
    BR("br", RegionShard.AMERICAS, LeagueShard.BR1, "Brazil"),
    LAN("lan", RegionShard.AMERICAS, LeagueShard.LA1, "Latin America North"),
    LAS("las", RegionShard.AMERICAS, LeagueShard.LA2, "Latin America South"),
    OCE("oce", RegionShard.AMERICAS, LeagueShard.OC1, "Oceania"),
    JP("jp", RegionShard.ASIA, LeagueShard.JP1, "Japan"),
    KR("kr", RegionShard.ASIA, LeagueShard.KR, "Korea");

    companion object {
        fun getRegionByName(name: String): Region? {
            return entries.find { it.region == name }
        }
    }
}