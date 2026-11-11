package cmatematica;

import java.awt.*;
import javax.swing.*;

public class CalculadoraGUI extends JFrame {

    private final CMatematica calc = new CMatematica();

    // Pestaña calculadora
    private final JTextField txtN1 = new JTextField(10);
    private final JTextField txtN2 = new JTextField(10);
    private final JLabel lblResultado = new JLabel("Resultado: ");

    // Pestaña conversor
    private final JTextField txtTemp = new JTextField(10);
    private final JLabel lblTemp = new JLabel("Resultado: ");

    public CalculadoraGUI() {
        setTitle("Calculadora Matemática");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Calculadora", crearPanelCalculadora());
        pestanas.addTab("Conversor de temperatura", crearPanelConversor());
        add(pestanas);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel crearPanelCalculadora() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel entradas = new JPanel(new GridLayout(2, 2, 8, 8));
        entradas.add(new JLabel("Número 1:"));
        entradas.add(txtN1);
        entradas.add(new JLabel("Número 2:"));
        entradas.add(txtN2);

        JPanel botones = new JPanel(new GridLayout(2, 3, 8, 8));
        JButton btnSumar = new JButton("+ Sumar");
        JButton btnRestar = new JButton("- Restar");
        JButton btnMult = new JButton("× Multiplicar");
        JButton btnDiv = new JButton("÷ Dividir");
        JButton btnRaiz = new JButton("√ Raíz (Núm. 1)");
        JButton btnExp = new JButton("xʸ Exponente");
        botones.add(btnSumar);
        botones.add(btnRestar);
        botones.add(btnMult);
        botones.add(btnDiv);
        botones.add(btnRaiz);
        botones.add(btnExp);

        JButton btnLimpiar = new JButton("Limpiar");
        lblResultado.setFont(new Font("SansSerif", Font.BOLD, 16));

        JPanel abajo = new JPanel(new GridLayout(2, 1, 8, 8));
        abajo.add(lblResultado);
        abajo.add(btnLimpiar);

        panel.add(entradas, BorderLayout.NORTH);
        panel.add(botones, BorderLayout.CENTER);
        panel.add(abajo, BorderLayout.SOUTH);

        btnSumar.addActionListener(e -> operar("sumar"));
        btnRestar.addActionListener(e -> operar("restar"));
        btnMult.addActionListener(e -> operar("multiplicar"));
        btnDiv.addActionListener(e -> operar("dividir"));
        btnRaiz.addActionListener(e -> operar("raiz"));
        btnExp.addActionListener(e -> operar("exponente"));
        btnLimpiar.addActionListener(e -> {
            txtN1.setText("");
            txtN2.setText("");
            lblResultado.setText("Resultado: ");
        });

        return panel;
    }

    private JPanel crearPanelConversor() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel entrada = new JPanel(new GridLayout(1, 2, 8, 8));
        entrada.add(new JLabel("Temperatura:"));
        entrada.add(txtTemp);

        JPanel botones = new JPanel(new GridLayout(1, 2, 8, 8));
        JButton btnFC = new JButton("°F → °C");
        JButton btnCF = new JButton("°C → °F");
        botones.add(btnFC);
        botones.add(btnCF);

        lblTemp.setFont(new Font("SansSerif", Font.BOLD, 16));

        panel.add(entrada, BorderLayout.NORTH);
        panel.add(botones, BorderLayout.CENTER);
        panel.add(lblTemp, BorderLayout.SOUTH);

        btnFC.addActionListener(e -> convertir(true));
        btnCF.addActionListener(e -> convertir(false));

        return panel;
    }

    private void operar(String operacion) {
        try {
            double a = leerNumero(txtN1, "Número 1");
            // La raíz solo necesita el primer número
            double b = operacion.equals("raiz") ? 0 : leerNumero(txtN2, "Número 2");
            calc.darNumeros(a, b);

            double resultado;
            switch (operacion) {
                case "sumar":       resultado = calc.sumar(); break;
                case "restar":      resultado = calc.restar(); break;
                case "multiplicar": resultado = calc.multiplicar(); break;
                case "dividir":     resultado = calc.dividir(); break;
                case "raiz":        resultado = calc.raizCuadrada(); break;
                default:            resultado = calc.exponente(); break;
            }
            lblResultado.setForeground(Color.BLACK);
            lblResultado.setText("Resultado: " + resultado);
        } catch (ArithmeticException | IllegalArgumentException ex) {
            mostrarError(lblResultado, ex.getMessage());
        }
    }

    private void convertir(boolean deFahrenheitACelsius) {
        try {
            double valor = leerNumero(txtTemp, "Temperatura");
            if (deFahrenheitACelsius) {
                lblTemp.setText(String.format("Resultado: %.2f °C", CMatematica.fahrenheitACelsius(valor)));
            } else {
                lblTemp.setText(String.format("Resultado: %.2f °F", CMatematica.celsiusAFahrenheit(valor)));
            }
            lblTemp.setForeground(Color.BLACK);
        } catch (IllegalArgumentException ex) {
            mostrarError(lblTemp, ex.getMessage());
        }
    }

    // Convierte el texto a número o lanza un error claro
    private double leerNumero(JTextField campo, String nombre) {
        try {
            return Double.parseDouble(campo.getText().trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Error: escribe un número válido en " + nombre + ".");
        }
    }

    private void mostrarError(JLabel etiqueta, String mensaje) {
        etiqueta.setForeground(Color.RED);
        etiqueta.setText(mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}
