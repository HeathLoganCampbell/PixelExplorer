package dev.sprock.pixelexplorer.client.engine.utils;

import dev.sprock.pixelexplorer.client.engine.Game;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;

public interface Renderable<G extends Game, S extends Screen<G>>
{
	public void render(S screen, G game);
}
