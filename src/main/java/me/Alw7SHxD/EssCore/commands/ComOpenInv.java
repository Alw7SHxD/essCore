package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class ComOpenInv implements CommandExecutor, messages {
    private Core core;

    public ComOpenInv(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.openinv")) return true;
        if(strings.length != 1) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<Player>")));
            return true;
        }

        Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
        if(target == null) return true;

        ((Player) commandSender).openInventory(target.getInventory());
        commandSender.sendMessage(EssAPI.color(String.format(m_openinv, target.getName())));
        return true;
    }
}
