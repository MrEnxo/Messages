package me.mrenxo.transmitmessages

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags
import java.util.function.BiFunction
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
import java.util.HashMap
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.Player
import me.mrenxo.transmitmessages.TransmitMessages
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import me.mrenxo.transmitmessages.MainCommand
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.Context
import net.kyori.adventure.text.minimessage.tag.Tag
import java.io.File
import java.io.BufferedReader
import java.util.stream.Collectors
import java.io.FileWriter
import org.bukkit.configuration.file.YamlConfiguration

class Formatter {

    var formater: MiniMessage = MiniMessage.builder().tags(
        TagResolver.builder()
            .resolver(StandardTags.color())
            .resolver(StandardTags.decorations())
            .resolver(TagResolver.resolver("ccolor") { args: ArgumentQueue, ctx: Context -> createColors(args, ctx) })
            .resolver(TagResolver.resolver("dash") { args: ArgumentQueue, ctx: Context -> createDash(args, ctx) })
            .build()
    ).build()

    constructor()

    companion object {
        var ColorCodes = HashMap<String, String>()
        private fun createColors(args: ArgumentQueue, ctx: Context): Tag {
            val clr = args.popOr("The <ccolor> tag requires exactly one argument, the Color").value()
            return Tag.styling(TextColor.fromCSSHexString(ColorCodes.getOrDefault(clr, ""))!!)
        }

        private fun createDash(args: ArgumentQueue, ctx: Context): Tag {
            return Tag.inserting(Component.text("                            ").decorate(TextDecoration.STRIKETHROUGH))
        }
    }
}