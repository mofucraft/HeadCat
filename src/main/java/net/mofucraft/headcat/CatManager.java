package net.mofucraft.headcat;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class CatManager {

    public Cat catSpawn(Player p){

        //Location loc = p.getLocation();
        World w = p.getWorld();
        Location locZero = new Location(w, 0, -100, 0);
        Cat c = (Cat) w.spawnEntity(locZero, EntityType.CAT, true);
        return c;

    }

    public Cat getNearestCat(Player p){

        List<Entity> near = p.getNearbyEntities(3D, 3D, 3D);

        for(Entity entity : near) {
            if(entity instanceof Cat) {
                return (Cat) entity;
            }
        }

        return null;
    }


    public void catMount(Player p){

        Entity e = (Entity) p;
        Cat c = catSpawn(p);
        e.addPassenger(c);
        p.sendMessage(ChatColor.YELLOW + "あたまにネコを連れてきました！＾＾");

    }

    public void catDismount(Player p){

        Cat c = getRidingCat(p);
        p.removePassenger(c);
        p.sendMessage(ChatColor.YELLOW + "あたまのネコは帰りました！＾＾");

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(HeadCat.instance, new Runnable() {
            @Override
            public void run() {
                catDisappear(c);
            }
        }, 60L);

    }

    public void catDisappear(Cat c){
        Location loc = c.getLocation();
        World w = c.getWorld();
        w.spawnParticle(Particle.FLASH, loc,32, 1.2F, 0F, 1.2F);
        c.remove();
    }

    public Cat getRidingCat(Player p){

        List passengers = p.getPassengers();
        Cat c = null;

        for(int i = 0; i < passengers.size(); i++){

            if(passengers.get(i) instanceof Cat) {

                c = (Cat) passengers.get(i);

            }

        }

        return c;
    }

}
