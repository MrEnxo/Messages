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
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.io.File
import java.io.BufferedReader
import java.util.stream.Collectors
import java.io.FileWriter
import org.bukkit.configuration.file.YamlConfiguration
import java.util.List

class MainCommand(private val instance: TransmitMessages) :
    Command("TransmitMessages", "Command for Messages API ", "/tm", List.of("tm", "transitm", "tmessages")) {
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        if (args[1].equals("reload", ignoreCase = true)) {
            instance.loadColors()
            Messaging.sendText(sender, Messaging.text("<#548EEB><bold>TM</bold>"))
            return true
        }
        return false
    }
}