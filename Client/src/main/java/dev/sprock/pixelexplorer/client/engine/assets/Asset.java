package dev.sprock.pixelexplorer.client.engine.assets;

import dev.sprock.pixelexplorer.client.engine.graphics.Bitmap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Asset
{

    public static Bitmap loadBitmap(String file) throws IOException {
        InputStream stream = Asset.class.getClassLoader().getResourceAsStream(file);
        BufferedImage image = ImageIO.read(stream);
        int width = image.getWidth();
        int height = image.getHeight();
        Bitmap bitmap = new Bitmap(width, height);

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
        for (int i = 0; i < pixels.length; i++)
        {
               bitmap.pixels[i] = (pixels[i]);
        }
        return bitmap;
    }
}
