package net.mofucraft.headcat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadCat extends JavaPlugin {

    static HeadCat instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){

        if(cmd.getName().equalsIgnoreCase("cat")){

            CatManager cm = new CatManager();
            Player p = (Player) sender;

            switch(args[0]){

                case "mount":

                    if(p.hasPermission("mofucraft.member.cat." + args[0])) {

                        if (cm.getRidingCat(p) == null) {

                            cm.catMount(p);

                        } else {

                            p.sendMessage(ChatColor.RED + "すでにネコが乗っています！");

                        }

                    }

                    break;

                case "dismount":

                    if(p.hasPermission("mofucraft.member.cat." + args[0])) {

                        if (cm.getRidingCat(p) != null) {

                            cm.catDismount(p);

                        } else {

                            p.sendMessage(ChatColor.RED + "乗っているネコはいません！");

                        }

                    }

                    break;
            }

            return true;

        }

        return false;
    }
}
