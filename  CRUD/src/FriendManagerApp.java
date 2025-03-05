import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FriendManagerApp extends JFrame {
    private JTextField nameField, numberField;
    private JTextArea displayArea;
    private JButton addButton, displayButton, updateButton, deleteButton;
    private AddFriend addFriend;
    private DisplayFriends displayFriends;
    private UpdateFriend updateFriend;
    private DeleteFriend deleteFriend;

    public FriendManagerApp() {
        addFriend = new AddFriend();
        displayFriends = new DisplayFriends();
        updateFriend = new UpdateFriend();
        deleteFriend = new DeleteFriend();

        setTitle("Friend Manager Application");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);

        JLabel numberLabel = new JLabel("Número:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(numberLabel, gbc);

        numberField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(numberField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                try {
                    long number = Long.parseLong(numberField.getText());
                    addFriend.addFriend(name, number, nameField, numberField);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El número debe ser un valor numérico.");
                }
            }
        });
        buttonPanel.add(addButton);

        displayButton = new JButton("Mostrar");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFriends.displayFriends(displayArea);
            }
        });
        buttonPanel.add(displayButton);

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                try {
                    long number = Long.parseLong(numberField.getText());
                    updateFriend.updateFriend(name, number);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El número debe ser un valor numérico.");
                }
            }
        });
        buttonPanel.add(updateButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                deleteFriend.deleteFriend(name, nameField, numberField);
            }
        });
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FriendManagerApp();
    }
}
