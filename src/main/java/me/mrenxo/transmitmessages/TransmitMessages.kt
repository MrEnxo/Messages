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
import net.kyori.adventure.Adventure
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import java.util.stream.Collectors
import org.bukkit.configuration.file.YamlConfiguration
import java.io.*
import java.lang.Exception
import java.nio.charset.StandardCharsets

class TransmitMessages : JavaPlugin() {

    override fun onEnable() {

        adventure = BukkitAudiences.create(this);

        format = Formatter()
        loadColors()
        try {
            val bukkitCommandMap = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            bukkitCommandMap.isAccessible = true
            val commandMap = bukkitCommandMap[Bukkit.getServer()] as CommandMap
            commandMap.register("Transmit", MainCommand(this))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadColors() {
        dataFolder.mkdir()
        val Colors = File(dataFolder.absolutePath + "/colors.yml")
        if (!Colors.exists()) {
            try {
                Colors.createNewFile()
                val resourceFile = getResource("colors.yml")
                val resourceString = BufferedReader(InputStreamReader(resourceFile, StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.joining("\n"))
                val writer: Writer = FileWriter(Colors, false)
                writer.write(resourceString)
                writer.flush()
                writer.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val ColorsYAML = YamlConfiguration.loadConfiguration(Colors)
        val colorsMap = ColorsYAML.getMapList("Colors") as Map<String, String>
        Formatter.Companion.ColorCodes = colorsMap as HashMap<String, String>
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


    companion object {
        lateinit var format: Formatter
        lateinit var adventure: BukkitAudiences
    }
}