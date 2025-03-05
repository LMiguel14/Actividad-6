import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DisplayFriends {
    public void displayFriends(JTextArea displayArea) {
        try {
            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            displayArea.setText("");

            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                displayArea.append("Nombre del amigo: " + name + "\nNúmero de contacto: " + number + "\n\n");
            }

            raf.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
        }
    }
}
