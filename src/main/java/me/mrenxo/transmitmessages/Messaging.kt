package me.mrenxo.transmitmessages

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags
import java.util.function.BiFunction
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
import java.util.HashMap
import org.bukkit.entity.Player
import me.mrenxo.transmitmessages.TransmitMessages.Companion.adventure
import me.mrenxo.transmitmessages.TransmitMessages.Companion.format
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import me.mrenxo.transmitmessages.MainCommand
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.command.CommandSender
import java.io.File
import java.io.BufferedReader
import java.util.stream.Collectors
import java.io.FileWriter
import org.bukkit.configuration.file.YamlConfiguration
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.Duration

object Messaging {

    @JvmStatic
    fun sendText(player: Player, string: String) {
        sendText(player, format.formater.deserialize(string))
    }

    @JvmStatic
    fun sendActionBar(player: Player, string: String) {
        adventure.player(player).sendActionBar(format.formater.deserialize(string))
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: String) {
        var title = Title.title(format.formater.deserialize(mainTitle),Component.empty());

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: String, FadeIn: Long, FadeOut: Long) {
        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(1000), Duration.ofMillis(FadeOut))
        var title = Title.title(format.formater.deserialize(mainTitle),Component.empty(), times);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: String, FadeIn: Long, FadeOut: Long, duration: Long) {
        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(duration), Duration.ofMillis(FadeOut))
        var title = Title.title(format.formater.deserialize(mainTitle),Component.empty(), times);

        adventure.player(player).showTitle(title)
    }


    @JvmStatic
    fun sendTitle(player: Player, mainTitle: String, subTitle: String) {
        var title = Title.title(format.formater.deserialize(mainTitle),format.formater.deserialize(subTitle));

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: String, subTitle: String, FadeIn: Long, FadeOut: Long) {
        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(1000), Duration.ofMillis(FadeOut))
        var title = Title.title(format.formater.deserialize(mainTitle),format.formater.deserialize(subTitle), times);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: String,subTitle: String, FadeIn: Long, FadeOut: Long, duration: Long) {
        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(duration), Duration.ofMillis(FadeOut))
        var title = Title.title(format.formater.deserialize(mainTitle),format.formater.deserialize(subTitle), times);

        adventure.player(player).showTitle(title)
    }


    @JvmStatic
    fun sendText(player: Player, string: Component?) {
        if (string == null) return

        TransmitMessages.adventure.player(player).sendMessage(string)
    }

    @JvmStatic
    fun sendText(player: CommandSender, string: Component?) {
        if (string == null) return
        if (player is Player) {
            adventure.player(player).sendMessage(string)
        } else{
            adventure.console().sendMessage(string)
        }
    }
    @JvmStatic
    fun sendText(player: CommandSender, string: String) {
        sendText(player, text(string))
    }

    @JvmStatic
    fun sendActionBar(player: Player, string: Component?) {
        if (string == null) return

        adventure.player(player).sendActionBar(string)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: Component?) {
        if (mainTitle == null) return

        var title = Title.title(mainTitle,Component.empty());

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: Component?, FadeIn: Long, FadeOut: Long) {
        if (mainTitle == null) return

        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(1000), Duration.ofMillis(FadeOut))
        var title = Title.title(mainTitle,Component.empty(), times);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: Component?, FadeIn: Long, FadeOut: Long, duration: Long) {
        if (mainTitle == null) return

        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(duration), Duration.ofMillis(FadeOut))
        var title = Title.title(mainTitle,Component.empty(), times);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: Component?, subTitle: Component?) {
        var subTitle = subTitle
        var mainTitle = mainTitle
        if (mainTitle == null) mainTitle = Component.empty()
        if (subTitle == null) subTitle = Component.empty()

        var title = Title.title(mainTitle,subTitle);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: Component?, subTitle: Component?, FadeIn: Long, FadeOut: Long) {
        var subTitle = subTitle
        var mainTitle = mainTitle
        if (mainTitle == null) mainTitle = Component.empty()
        if (subTitle == null) subTitle = Component.empty()

        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(1000), Duration.ofMillis(FadeOut))
        var title = Title.title(mainTitle,subTitle, times);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun sendTitle(player: Player, mainTitle: Component?,subTitle: Component?, FadeIn: Long, FadeOut: Long, duration: Long) {
        var subTitle = subTitle
        var mainTitle = mainTitle
        if (mainTitle == null) mainTitle = Component.empty()
        if (subTitle == null) subTitle = Component.empty()

        val times = Title.Times.times(Duration.ofMillis(FadeIn), Duration.ofMillis(duration), Duration.ofMillis(FadeOut))
        var title = Title.title(mainTitle,subTitle, times);

        adventure.player(player).showTitle(title)
    }

    @JvmStatic
    fun playSound(player: Player, sound: String, volume: Float, pitch: Float) {
        player.playSound(player.location,sound,volume,pitch);
    }

    @JvmStatic
    fun playSound(player: Player, sound: String, pitch: Float) {
        player.playSound(player.location,sound,1f,pitch);
    }

    @JvmStatic
    fun playSound(player: Player, sound: String) {
        player.playSound(player.location,sound,1f,1f);
    }

    @JvmStatic
    fun text(string: String): Component {
        return format.formater.deserialize(string)
    }
}