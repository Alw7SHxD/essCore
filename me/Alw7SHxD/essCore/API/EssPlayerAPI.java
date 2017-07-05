package me.Alw7SHxD.essCore.API;

import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.configuration.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;
import java.util.UUID;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * essCore is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class EssPlayerAPI {
    private Player player;
    private UUID uuid;
    private PlayerData playerData;
    public boolean l = false;

    public EssPlayerAPI(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.playerData = new PlayerData(uuid + ".yml", Core.getPlugin(Core.class));
        create();
    }

    private void create() {
        if (!isSet("player.unique_id")) {
            set("player.unique_id", this.uuid.toString());
            this.l = true;
        }
        if (getString("player.username") != player.getName()) set("player.username", this.player.getName());
        if (!isSet("player.first_join.date"))
            set("player.first_join.date", new Date(this.player.getFirstPlayed() * 1000).toString());
        if (!isSet("player.first_join.int.Long")) set("player.first_join.int.Long", this.player.getFirstPlayed());
    }

    protected boolean isSet(String path) {
        return playerData.getYaml().isSet(path);
    }

    private boolean getBoolean(String path) {
        return playerData.getYaml().getBoolean(path);
    }

    private String getString(String path) {
        return playerData.getYaml().getString(path);
    }

    private double getDouble(String path) {
        return playerData.getYaml().getDouble(path);
    }

    private float getFloat(String path) {
        return (float) getDouble(path);
    }

    private void set(String path, Object value) {
        playerData.getYaml().set(path, value);
        playerData.saveYaml();
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    protected PlayerData getPlayerData() {
        return playerData;
    }

    boolean toggle(String path) {
        playerData.modifyYaml();
        if (getBoolean(path)) {
            set(path, false);
            return false;
        } else {
            set(path, true);
            return true;
        }
    }

    public boolean getFlight() {
        playerData.modifyYaml();
        return getBoolean("flight");
    }

    public void setFlight(boolean value) {
        set("flight", value);
    }


    public boolean getMuted() {
        playerData.modifyYaml();
        return getBoolean("muted");
    }

    public void setMuted(boolean value) {
        set("muted", value);
    }

    public boolean getFrozen() {
        playerData.modifyYaml();
        return getBoolean("frozen");
    }

    public void setFrozen(boolean value) {
        set("frozen", value);
    }

    public boolean getVanished() {
        playerData.modifyYaml();
        return getBoolean("vanished");
    }

    public void setVanished(boolean value) {
        set("vanished", value);
    }

    public String getNickname() {
        playerData.modifyYaml();
        return getString("player.nickname") + "&r";
    }

    public boolean getNick() {
        return isSet("player.nickname");
    }

    public void setNickname(String value) {
        set("player.nickname", value);
    }

    protected boolean setHome(String s, Location location) {
        s = s.replace(".", "-");
        if (isSet("homes." + s))
            return false;

        set("homes." + s + ".location.world", location.getWorld().getName());
        set("homes." + s + ".location.X", location.getX());
        set("homes." + s + ".location.Y", location.getY());
        set("homes." + s + ".location.Z", location.getZ());
        set("homes." + s + ".location.Yaw", location.getYaw());
        set("homes." + s + ".location.Pitch", location.getPitch());
        return true;
    }

    protected Location getHome(String s) {
        if (!isSet("homes." + s))
            return null;

        String x1 = "homes.";
        String x2 = ".location.";
        return new Location(Bukkit.getWorld(getString(x1 + s + x2 + "world")), getDouble(x1 + s + x2 + "X"), getDouble(x1 + s + x2 + "Y"), getDouble(x1 + s + x2 + "Z"), getFloat(x1 + s + x2 + "Yaw"), getFloat(x1 + s + x2 + "Pitch"));
    }

    protected boolean delHome(String s) {
        if (!isSet("homes." + s))
            return false;

        set("homes." + s, null);
        return true;
    }
}
