package pong;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PongGame {
	public static boolean music = true;
	static SourceDataLine soundLine = null;
	static int BUFFER_SIZE = 64 * 1024; // 64 KB

	public static void main(String[] args) {

		MenuFrame frame = new MenuFrame();

		try {

			File soundFile = new File("/Users/***/eclipse-workspace/sysout/src/pong/music.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			soundLine = (SourceDataLine) AudioSystem.getLine(info);
			soundLine.open(audioFormat);
			soundLine.start();
			int nBytesRead = 0;
			byte[] sampledData = new byte[BUFFER_SIZE];
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
				if (nBytesRead >= 0) {
					// Writes audio data to the mixer via this source data line.
					soundLine.write(sampledData, 0, nBytesRead);

					if (music == false)
						soundLine.stop();
				}

			}
		}

		catch (UnsupportedAudioFileException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} finally {
			soundLine.drain();
			soundLine.close();
		}

	}

}
