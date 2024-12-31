package presentacion.vistas;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import presentacion.IMenu;
import presentacion.PresentadorMenu;

public class Menu extends javax.swing.JFrame implements IMenu{

    public Menu() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        inicioLabel = new javax.swing.JLabel();
        clienteLabel = new javax.swing.JLabel();
        facturaLabel = new javax.swing.JLabel();
        productoLabel = new javax.swing.JLabel();
        habitacionLabel = new javax.swing.JLabel();
        reservaLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        salirButton = new javax.swing.JButton();
        compraLabel = new javax.swing.JLabel();
        ventaLabel = new javax.swing.JLabel();
        perfilLabel = new javax.swing.JLabel();
        usuLabel = new javax.swing.JLabel();
        rolLabel = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        rolField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(23, 27, 36));
        jPanel1.setPreferredSize(new java.awt.Dimension(157, 550));

        inicioLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inicioLabel.setForeground(new java.awt.Color(255, 255, 255));
        inicioLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home-altV.png"))); // NOI18N
        inicioLabel.setText("Inicio");

        clienteLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clienteLabel.setForeground(new java.awt.Color(255, 255, 255));
        clienteLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        clienteLabel.setText("Clientes");

        facturaLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        facturaLabel.setForeground(new java.awt.Color(255, 255, 255));
        facturaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        facturaLabel.setText("Facturación");

        productoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        productoLabel.setForeground(new java.awt.Color(255, 255, 255));
        productoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        productoLabel.setText("Productos");

        habitacionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        habitacionLabel.setForeground(new java.awt.Color(255, 255, 255));
        habitacionLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        habitacionLabel.setText("Habitaciones");

        reservaLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reservaLabel.setForeground(new java.awt.Color(255, 255, 255));
        reservaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        reservaLabel.setText("Reservas");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bienvenido");

        salirButton.setBackground(new java.awt.Color(255, 0, 0));
        salirButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salirButton.setForeground(new java.awt.Color(255, 255, 255));
        salirButton.setText("Salir");
        salirButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        compraLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        compraLabel.setForeground(new java.awt.Color(255, 255, 255));
        compraLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        compraLabel.setText("Compra");

        ventaLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventaLabel.setForeground(new java.awt.Color(255, 255, 255));
        ventaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/users-altV.png"))); // NOI18N
        ventaLabel.setText("Venta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ventaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(facturaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(salirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productoLabel)
                                .addComponent(reservaLabel)
                                .addComponent(habitacionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clienteLabel)
                                .addComponent(inicioLabel)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(compraLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addComponent(inicioLabel)
                .addGap(18, 18, 18)
                .addComponent(clienteLabel)
                .addGap(18, 18, 18)
                .addComponent(productoLabel)
                .addGap(18, 18, 18)
                .addComponent(reservaLabel)
                .addGap(18, 18, 18)
                .addComponent(habitacionLabel)
                .addGap(18, 18, 18)
                .addComponent(compraLabel)
                .addGap(18, 18, 18)
                .addComponent(ventaLabel)
                .addGap(18, 18, 18)
                .addComponent(facturaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(salirButton)
                .addGap(29, 29, 29))
        );

        perfilLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/perfil.png")));

        usuLabel.setBackground(new java.awt.Color(255, 255, 255));
        usuLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuLabel.setText("Usuario:");

        rolLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rolLabel.setText("Rol:");

        usuarioField.setEditable(false);
        usuarioField.setFocusable(false);

        rolField.setEditable(false);
        rolField.setFocusable(false);
        rolField.setText("RECEPCIONISTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(perfilLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usuarioField)
                    .addComponent(rolField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 580, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(perfilLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuarioField)
                            .addComponent(usuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rolField)
                            .addComponent(rolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JLabel compraLabel;
    private javax.swing.JLabel facturaLabel;
    private javax.swing.JLabel habitacionLabel;
    private javax.swing.JLabel inicioLabel;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel perfilLabel;
    private javax.swing.JLabel productoLabel;
    private javax.swing.JLabel reservaLabel;
    private javax.swing.JTextField rolField;
    private javax.swing.JLabel rolLabel;
    private javax.swing.JButton salirButton;
    private javax.swing.JLabel usuLabel;
    private javax.swing.JTextField usuarioField;
    private javax.swing.JLabel ventaLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setPresentador(PresentadorMenu pMenu) {
        salirButton.addActionListener(pMenu);
        clienteLabel.addMouseListener(pMenu);
        facturaLabel.addMouseListener(pMenu);
        habitacionLabel.addMouseListener(pMenu);
        productoLabel.addMouseListener(pMenu);
        reservaLabel.addMouseListener(pMenu);
        compraLabel.addMouseListener(pMenu);
        ventaLabel.addMouseListener(pMenu);
    }

    @Override
    public void iniciar() {
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int alturaPantalla = screenSize.height;
        jPanel1.setPreferredSize(new Dimension(165, alturaPantalla));
        setVisible(true);
    }
    
    @Override
    public void cerrar(){
        this.dispose();
    }

    @Override
    public JLabel getClienteLabel() {
        return clienteLabel;
    }

    @Override
    public JLabel getFacturaLabel() {
        return facturaLabel;
    }

    @Override
    public JLabel getInicioLabel() {
        return inicioLabel;
    }

    @Override
    public JLabel getHabitacionLabel() {
        return habitacionLabel;
    }

    @Override
    public JLabel getProductoLabel() {
        return productoLabel;
    }
    
    @Override
    public JLabel getReservaLabel() {
        return reservaLabel;
    }

    @Override
    public JTextField getUsuarioField() {
        return usuarioField;
    }

    @Override
    public JLabel getCompraLabel() {
        return compraLabel;
    }

    @Override
    public JLabel getVentaLabel() {
        return ventaLabel;
    }
    
}
