package com.inventario.gui;

import javax.swing.*;
import java.awt.*;
import com.inventario.model.*;

/*
 * JFrame  Es la ventana principal de la aplicación, donde se muestra todu el contenido.
 * */
public class ProductRegistrationFrame extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton saveButton;
    private ProductTableModel tableModel;

    public ProductRegistrationFrame() {
        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        /*JPanel
         * para organizar los campos de entrada y el botón "Guardar" utilizando un layout en forma de tabla (GridLayout).
         * */
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("ID: "));//JLabel se usa para mostrar texto sin capacidad de edición.
        idField = new JTextField();//JTextField: Un campo de texto donde el usuario puede introducir datos.
        panel.add(idField);

        panel.add(new JLabel("Nombre: "));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Precio: "));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Cantidad: "));
        quantityField = new JTextField();
        panel.add(quantityField);

        /*JButton:
            Un botón que el usuario puede hacer clic para activar una acción.
            En este caso, se utiliza un botón "Guardar" que invoca el método saveProduct() cuando es presionado.
        */
        saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> saveProduct());
        panel.add(saveButton);

        add(panel, BorderLayout.NORTH);

        tableModel = new ProductTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void saveProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Product product = new Product(id, name, price, quantity);
            tableModel.addProduct(product);

            // Limpiar campos después de guardar
            idField.setText("");
            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");

        } catch (NumberFormatException e) {
            //JOptionPane
            //Un componente que permite mostrar diálogos emergentes como cuadros de mensaje.
            JOptionPane.showMessageDialog(this, "Error en los datos de entrada. Por favor verifica.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}