package dev.sprock.pixelexplorer.client.engine.graphics;

import lombok.Getter;

public class Bitmap
{
	public static final int TRANSPARENT_COLOR = 0xff00ff;
	public static final int RGB_MASK = 0xFFFFFF;

	@Getter
	public final int width;
	@Getter
	public final int height;
	public final int area;
	public final int[] pixels;

	public Bitmap(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.area = this.width * this.height;
		this.pixels = new int[this.area];
	}
	
	public void draw(Bitmap bitmap, int xOffset, int yOffset)
	{
		for (int y = 0; y < bitmap.height; y++) {
			int yPix = y + yOffset;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = 0; x < bitmap.width; x++) {
				int xPix = x + xOffset;
				if (xPix < 0 || xPix >= width) continue;

				int src = bitmap.pixels[x + y * bitmap.width];
				if((src & RGB_MASK) != TRANSPARENT_COLOR)
					pixels[xPix + yPix * width] = src;
			}
		}
	}

	public void drawSegment(Bitmap childBitmap, int innerX, int innerY, int innerWidth, int innerHeight, int xOffset, int yOffset)
	{
		drawSegment(childBitmap, innerX,  innerY,  innerWidth, innerHeight,  xOffset, yOffset, 0xFFFFFF);
	}

	public void drawSegment(Bitmap childBitmap, int innerX, int innerY, int innerWidth, int innerHeight, int xOffset, int yOffset, int color)
	{
		for (int y = innerY; y < innerY + innerHeight; y++) {
			int yPix = y + yOffset - innerY;
			if (yPix < 0 || yPix >= height) continue;

			for (int x = innerX; x < innerX + innerWidth; x++) {
				int xPix = x + xOffset - innerX;
				if (xPix < 0 || xPix >= width) continue;

				int src = childBitmap.pixels[x + y * childBitmap.width];
				if((src & RGB_MASK) != TRANSPARENT_COLOR)
					pixels[xPix + yPix * width] = src & color;
			}
		}
	}
	
	public void setPixel(int x, int y, int value)
	{
		if (y < 0 || y >= height) return;
		if (x < 0 || x >= width) return; 
		this.setPixel(x + y * this.width, value);
	}
	
	public void setPixel(int index, int value)
	{
		if (index < 0 || index >= this.area) return;
		this.pixels[index] = value;
	}

}
