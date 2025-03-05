import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class UpdateFriend {
    public void updateFriend(String newName, long newNumber) {
        try {
            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {
                    String nameNumberString = raf.readLine();
                    int index = nameNumberString.indexOf('!');
                    String name = nameNumberString.substring(0, index);

                    if (name.equals(newName)) {
                        nameNumberString = newName + "!" + newNumber;
                    }

                    tmpraf.writeBytes(nameNumberString + System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine() + System.lineSeparator());
                }

                raf.setLength(tmpraf.length());
                tmpraf.close();
                raf.close();
                tmpFile.delete();

                JOptionPane.showMessageDialog(null, "Amigo actualizado.");
            } else {
                raf.close();
                JOptionPane.showMessageDialog(null, "El nombre de entrada no existe.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
        }
    }
}
