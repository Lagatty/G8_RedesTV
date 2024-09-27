package com.example.tv.view;

import com.example.tv.controller.PaqueteCanalesController;
import com.example.tv.controller.SectorController;
import com.example.tv.controller.SuscriptorController;
import com.example.tv.model.PaqueteCanales;
import com.example.tv.model.Sector;
import com.example.tv.model.Suscriptor;
import com.example.tv.util.SuscriptorException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.*;

public class MainWindow extends JFrame {
    private SuscriptorController suscriptorController;
    private SectorController sectorController;
    private PaqueteCanalesController paqueteController;

    public MainWindow() {
        suscriptorController = new SuscriptorController();
        sectorController = new SectorController();
        paqueteController = new PaqueteCanalesController();
        
        setTitle("Sistema de TV por Cable");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // Logo de la universidad
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Título
        JLabel titleLabel = new JLabel("Sistema de Gestión de TV por Cable");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // GIF de TV
        ImageIcon tvIcon = new ImageIcon(getClass().getResource("/tv.gif"));
        JLabel tvLabel = new JLabel(tvIcon);
        topPanel.add(tvLabel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addButton(buttonPanel, "Listar Suscriptores", this::listarSuscriptores);
        addButton(buttonPanel, "Agregar Suscriptor", this::agregarSuscriptor);
        addButton(buttonPanel, "Modificar Suscriptor", this::modificarSuscriptor);
        addButton(buttonPanel, "Eliminar Suscriptor", this::eliminarSuscriptor);
        addButton(buttonPanel, "Listar Sectores", this::listarSectores);
        addButton(buttonPanel, "Agregar Sector", this::agregarSector);
        addButton(buttonPanel, "Identificar Sectores Débiles", this::identificarSectoresDebiles);
        addButton(buttonPanel, "Agregar Paquete de Canales", this::agregarPaquete);
        addButton(buttonPanel, "Asignar Paquete a Sector", this::asignarPaqueteASector);
        addButton(buttonPanel, "Ver Estadísticas", this::verEstadisticas);
        addButton(buttonPanel, "Filtrar Suscriptores", this::filtrarSuscriptores);
        addButton(buttonPanel, "Generar Reporte", this::generarReporte);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Panel inferior
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Información de autores
        JLabel authorsLabel = new JLabel("Francisco Gática y Felipe Bechan");
        bottomPanel.add(authorsLabel, BorderLayout.WEST);

        // Enlace a GitHub
        JLabel githubLabel = new JLabel("<html><u>Repo de Git <3</u></html>");
        githubLabel.setForeground(Color.BLUE);
        githubLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        githubLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Lagatty/G8_RedesTV"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        bottomPanel.add(githubLabel, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }

    private void listarSuscriptores() {
        List<Suscriptor> suscriptores = suscriptorController.listarSuscriptores();
        mostrarSuscriptores(suscriptores, "Lista de Suscriptores");
    }

    private void agregarSuscriptor() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del suscriptor:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String edadStr = JOptionPane.showInputDialog(this, "Ingrese la edad del suscriptor:");
        if (edadStr == null || edadStr.trim().isEmpty()) return;
        int edad = Integer.parseInt(edadStr);

        List<Sector> sectores = sectorController.listarSectores();
        Sector sector = (Sector) JOptionPane.showInputDialog(this, "Seleccione el sector:",
                "Sector", JOptionPane.QUESTION_MESSAGE, null, sectores.toArray(), sectores.get(0));
        if (sector == null) return;

        Suscriptor nuevoSuscriptor = new Suscriptor(0, nombre, edad, sector);
        try {
            suscriptorController.agregarSuscriptor(nuevoSuscriptor);
            JOptionPane.showMessageDialog(this, "Suscriptor agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SuscriptorException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar suscriptor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarSuscriptor() {
        List<Suscriptor> suscriptores = suscriptorController.listarSuscriptores();
        Suscriptor suscriptor = (Suscriptor) JOptionPane.showInputDialog(this, "Seleccione el suscriptor a modificar:",
                "Modificar Suscriptor", JOptionPane.QUESTION_MESSAGE, null, suscriptores.toArray(), suscriptores.get(0));
        if (suscriptor == null) return;

        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre:", suscriptor.getNombre());
        if (nombre == null) return;
        suscriptor.setNombre(nombre);

        String edadStr = JOptionPane.showInputDialog(this, "Ingrese la nueva edad:", suscriptor.getEdad());
        if (edadStr == null) return;
        suscriptor.setEdad(Integer.parseInt(edadStr));

        List<Sector> sectores = sectorController.listarSectores();
        Sector sector = (Sector) JOptionPane.showInputDialog(this, "Seleccione el nuevo sector:",
                "Sector", JOptionPane.QUESTION_MESSAGE, null, sectores.toArray(), suscriptor.getSector());
        if (sector == null) return;
        suscriptor.setSector(sector);

        suscriptorController.modificarSuscriptor(suscriptor);
        JOptionPane.showMessageDialog(this, "Suscriptor modificado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarSuscriptor() {
        List<Suscriptor> suscriptores = suscriptorController.listarSuscriptores();
        Suscriptor suscriptor = (Suscriptor) JOptionPane.showInputDialog(this, "Seleccione el suscriptor a eliminar:",
                "Eliminar Suscriptor", JOptionPane.QUESTION_MESSAGE, null, suscriptores.toArray(), suscriptores.get(0));
        if (suscriptor == null) return;

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este suscriptor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            suscriptorController.eliminarSuscriptor(suscriptor.getId());
            JOptionPane.showMessageDialog(this, "Suscriptor eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listarSectores() {
        List<Sector> sectores = sectorController.listarSectores();
        StringBuilder sb = new StringBuilder();
        for (Sector s : sectores) {
            sb.append(s.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Lista de Sectores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarSector() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo sector:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            sectorController.agregarSector(nombre);
            JOptionPane.showMessageDialog(this, "Sector agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void identificarSectoresDebiles() {
        List<Sector> sectoresDebiles = sectorController.identificarSectoresDebiles();
        if (sectoresDebiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron sectores débiles.", "Sectores Débiles", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("Sectores débiles:\n");
            for (Sector s : sectoresDebiles) {
                sb.append(s.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Sectores Débiles", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarPaquete() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del paquete:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String precioBaseStr = JOptionPane.showInputDialog(this, "Ingrese el precio base del paquete:");
        if (precioBaseStr == null || precioBaseStr.trim().isEmpty()) return;
        double precioBase = Double.parseDouble(precioBaseStr);

        PaqueteCanales nuevoPaquete = new PaqueteCanales(0, nombre, precioBase);

        while (true) {
            String canal = JOptionPane.showInputDialog(this, "Ingrese un canal (o deje vacío para terminar):");
            if (canal == null || canal.trim().isEmpty()) break;
            nuevoPaquete.agregarCanal(canal);
        }

        paqueteController.agregarPaquete(nuevoPaquete);
        JOptionPane.showMessageDialog(this, "Paquete de canales agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void asignarPaqueteASector() {
        List<PaqueteCanales> paquetes = paqueteController.listarPaquetes();
        PaqueteCanales paquete = (PaqueteCanales) JOptionPane.showInputDialog(this, "Seleccione el paquete:",
                "Asignar Paquete", JOptionPane.QUESTION_MESSAGE, null, paquetes.toArray(), paquetes.get(0));
        if (paquete == null) return;

        List<Sector> sectores = sectorController.listarSectores();
        Sector sector = (Sector) JOptionPane.showInputDialog(this, "Seleccione el sector:",
                "Asignar Paquete", JOptionPane.QUESTION_MESSAGE, null, sectores.toArray(), sectores.get(0));
        if (sector == null) return;

        paqueteController.asignarPaqueteASector(paquete.getId(), sector.getId());
        JOptionPane.showMessageDialog(this, "Paquete asignado al sector con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verEstadisticas() {
        List<Sector> sectores = sectorController.listarSectores();
        StringBuilder sb = new StringBuilder("Estadísticas por sector:\n\n");

        for (Sector sector : sectores) {
            int cantidadSuscriptores = sectorController.contarSuscriptoresPorSector(sector.getId());
            List<PaqueteCanales> paquetes = paqueteController.listarPaquetesPorSector(sector.getId());

            sb.append("Sector: ").append(sector.getNombre()).append("\n");
            sb.append("Cantidad de suscriptores: ").append(cantidadSuscriptores).append("\n");
            sb.append("Paquetes disponibles: ").append(paquetes.size()).append("\n");
            for (PaqueteCanales paquete : paquetes) {
                sb.append(" - ").append(paquete.getNombre()).append("\n");
            }
            sb.append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void filtrarSuscriptores() {
        String edadMinStr = JOptionPane.showInputDialog(this, "Ingrese la edad mínima:");
        String edadMaxStr = JOptionPane.showInputDialog(this, "Ingrese la edad máxima:");
        String sector = JOptionPane.showInputDialog(this, "Ingrese el sector (o deje en blanco para todos):");
        
        int edadMin = Integer.parseInt(edadMinStr);
        int edadMax = Integer.parseInt(edadMaxStr);
        sector = sector.isEmpty() ? null : sector;
        
        List<Suscriptor> suscriptoresFiltrados = suscriptorController.filtrarSuscriptores(edadMin, edadMax, sector);
        mostrarSuscriptores(suscriptoresFiltrados, "Suscriptores Filtrados");
    }

    private void generarReporte() {
        String fileName = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo para el reporte:");
        try {
            suscriptorController.generarReporte(fileName);
            JOptionPane.showMessageDialog(this, "Reporte generado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarSuscriptores(List<Suscriptor> suscriptores, String titulo) {
        StringBuilder sb = new StringBuilder();
        for (Suscriptor s : suscriptores) {
            sb.append(s.toString()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}