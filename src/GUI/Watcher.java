package GUI;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import IO.CreateDB;
import Main.INITIAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Watcher implements Runnable{

	@Override
	public void run() {
		try {
			Path path = Paths.get("/Users/gal/Desktop");
			WatchService watchService;
			watchService = path.getFileSystem().newWatchService();

			path.register(watchService,
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);

			while(true) {
				WatchKey watchKey = null;
				try {
					watchKey = watchService.take();

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int counter = 0;
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					if(event.kind().name().equals("ENTRY_CREATE")) {
						
					}
					if(!watchKey.reset()) {
						watchKey.cancel();
						watchService.close();
					}
				} 
			}
		}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



