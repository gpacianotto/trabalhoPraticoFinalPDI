/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processadorimagem;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author YiazmaT
 */
public class FileFilterPGM extends FileFilter{

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(".pgm") ||f.getName().endsWith(".ppm");
    }

    @Override
    public String getDescription() {
        return ".pgm";
    }
    
}
