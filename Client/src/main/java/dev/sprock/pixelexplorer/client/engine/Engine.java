package dev.sprock.pixelexplorer.client.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import lombok.Getter;
import lombok.Setter;

public class Engine<G extends Game> extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private boolean running;
	private Thread thread;
	private JFrame frame;


	@Setter
	private double framesPerSecond = 24;
	private int width;
	private int height;
	private int scale;
	
	private Screen<G> screen;
	private G game;
	private InputListener inputListener;
	
	private BufferedImage image;
	private int[] pixels;

	// desired fps
	private final static int    TICKS_PER_SECOND = 20;
	// maximum number of frames to be skipped
	private final static int    MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int    TICK_PERIOD = 1000 / TICKS_PER_SECOND;

	public Engine(int width, int height, int scale)
	{
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		Dimension dimesion = new Dimension(this.width * this.scale, this.height * this.scale );
		this.setPreferredSize(dimesion);
		this.setMaximumSize(dimesion);
		this.setMinimumSize(dimesion);
		
		inputListener = new InputListener();

		addKeyListener(inputListener);
		addFocusListener(inputListener);
		
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
	
		this.frame = new JFrame("Engine");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public int getScreenWidth()
	{
		return this.width;
	}
	
	public int getScreenHeight()
	{
		return this.height;
	}
	
	public int getScreenScale()
	{
		return this.scale;
	}
	
	public void setTitle(String title)
	{
		this.frame.setTitle(title);
	}
	
	public synchronized void start()
	{
		if(running) return;
		if(game == null) return;
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		this.running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		game.tick(inputListener);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		this.screen.render(this.game);

		for (int i = 0; i < this.width * this.height; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(this.image, 0, 0, this.width * this.scale, this.height * this.scale, null);
		g.dispose();
		bs.show();
		
	}
	
	public void setScreen(Screen<G> screen)
	{
		this.screen = screen;
	}
	
	public void setGame(G game) {
		this.game = game;
	}
	
	@Override
	public void run() 
	{
		long beginTime;     // the time when the cycle begun
		long timeDiff;      // the time it took for the cycle to execute
		int sleepTime;      // ms to sleep (<0 if we're behind)
		int framesSkipped;  // number of frames being skipped

		sleepTime = 0;

		requestFocus();

		while (running)
		{
			beginTime = System.currentTimeMillis();
			framesSkipped = 0;  // resetting the frames skipped
			// update game state
			this.tick();
			// render state to the screen
			// draws the canvas on the panel
			this.render();
			// calculate how long did the cycle take
			timeDiff = System.currentTimeMillis() - beginTime;
			// calculate sleep time
			sleepTime = (int)(TICK_PERIOD - timeDiff);

			if (sleepTime > 0) {
				// if sleepTime > 0 we're OK
				try
				{
					// send the thread to sleep for a short period
					// very useful for battery saving
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e)
				{
				}
			}

			while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
				// we need to catch up
				// update without rendering
				this.tick();
				// add frame period to check if in next frame
				sleepTime += TICK_PERIOD;
				framesSkipped++;
			}
		}
	}
}
