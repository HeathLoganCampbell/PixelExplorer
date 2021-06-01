package dev.sprock.pixelexplorer.server.world;

import dev.sprock.pixelexplorer.shared.world.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class WorldManager
{
    private HashMap<Integer, World> worlds = new HashMap<>();

    public void registerWorld(World world)
    {
        this.worlds.put(world.getId(), world);
    }

    public void unregisterWorld(World world)
    {
        this.worlds.remove(world.getId());
    }

    public World getWorld(int worldId)
    {
        return worlds.get(worldId);
    }

    public Collection<World> getWorlds()
    {
        return this.worlds.values();
    }
}
