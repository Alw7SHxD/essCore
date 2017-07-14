package me.Alw7SHxD.essCore.util;

import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import me.Alw7SHxD.essCore.Core;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class EssEconomy implements Economy {
    private Core core;

    public EssEconomy(Core core) {
        this.core = core;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return core.getName();
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double v) {
        return String.valueOf(v);
    }

    @Override
    public String currencyNamePlural() {
        return core.getConfig().getString("currency-format.plural") != null ? core.getConfig().getString("currency-format.plural") : "Dollars";
    }

    @Override
    public String currencyNameSingular() {
        return core.getConfig().getString("currency-format.singular") != null ? core.getConfig().getString("currency-format.singular") : "Dollar";
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return playerAPI.hasAccount();
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return playerAPI.hasAccount();
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return playerAPI.hasAccount();
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return playerAPI.hasAccount();
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public double getBalance(String s) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return playerAPI.getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return playerAPI.getBalance();
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public double getBalance(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return playerAPI.getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return playerAPI.getBalance();
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return playerAPI.hasBalance(v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return playerAPI.hasBalance(v);
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return playerAPI.hasBalance(v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return playerAPI.hasBalance(v);
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    public void setPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        playerAPI.setBalance(v);
    }

    public void setPlayer(OfflinePlayer offlinePlayer, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        playerAPI.setBalance(v);
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    public void setPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        playerAPI.setBalance(v);
    }

    public void setPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        playerAPI.setBalance(v);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(offlinePlayer);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }
}