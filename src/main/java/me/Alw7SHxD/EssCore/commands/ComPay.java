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
public class ComPay implements CommandExecutor {
    private Core core;

    public ComPay(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!core.hookedWithVault) {
            commandSender.sendMessage(EssAPI.color(messages.m_vault_unavailable));
            return true;
        } else if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(messages.m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.pay")) return true;
        if (strings.length != 2) {
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9<Player> <Amount>")));
            return true;
        }

        Player player = (Player) commandSender;
        Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
        if (target == null) return true;

        try {
            Double amount = Double.parseDouble(strings[1]);
            if (target == player) {
                commandSender.sendMessage(EssAPI.color(messages.m_pay_self));
                return true;
            } else if (amount == 0) {
                commandSender.sendMessage(EssAPI.color(messages.m_pay_zero));
                return true;
            } else if (!core.getEssEconomy().has(player, amount)) {
                commandSender.sendMessage(EssAPI.color(messages.m_economy_not_enough_money));
                return true;
            } else if (core.Lists.getPlayerPayTransaction().containsKey(player.getUniqueId()) || core.Lists.getPlayerPayTransactionTime().containsKey(player.getUniqueId())) {
                commandSender.sendMessage(EssAPI.color(messages.m_economy_transaction_pending));
                return true;
            }
            core.Lists.addPlayerPayTransaction(player.getUniqueId(), target.getUniqueId(), 10, amount);
            player.sendMessage(EssAPI.color(String.format(messages.m_pay_confirm, core.getEssEconomy().format(amount), target.getName())));
        } catch (NumberFormatException e) {
            commandSender.sendMessage(EssAPI.color(messages.m_number_format));
        }
        return true;
    }
}
