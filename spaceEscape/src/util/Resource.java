package util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import oneiros.file.FileManager;

public class Resource {

    public static BufferedImage getImage(String file) {
        try {
            return FileManager.getImage(Resource.class.getResource("/images/" + file));
        } catch (IOException ex) {
        	throw new RuntimeException(ex);
        }
    }
}