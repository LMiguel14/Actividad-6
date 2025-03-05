import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddFriend {
    public void addFriend(String newName, long newNumber, JTextField nameField, JTextField numberField) {
        try {
            // Validar que el nombre contenga solo letras
            if (!newName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Error: El nombre debe contener solo letras.");
                return;
            }

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

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                String nameNumberString = newName + "!" + newNumber;
                raf.writeBytes(nameNumberString + System.lineSeparator());
                JOptionPane.showMessageDialog(null, "Amigo agregado.");

                // Limpiar campos
                nameField.setText("");
                numberField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "El nombre o el número ya existe.");
            }

            raf.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
