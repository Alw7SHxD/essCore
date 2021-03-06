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
public class ComCrafting implements CommandExecutor, messages {
    private Core core;

    public ComCrafting(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if(!EssAPI.hasPermission(commandSender, "esscore.workbench")) return true;

        ((Player) commandSender).openWorkbench(((Player) commandSender).getLocation(), true);
        commandSender.sendMessage(EssAPI.color(m_crafting));
        return true;
    }
}
