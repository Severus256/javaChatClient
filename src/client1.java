
import java.awt.Color;
import java.awt.Label;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aga
 */
public class client1 extends javax.swing.JFrame {

    private Socket s;
    private final int PORT = 3033;
    private final String HOST = "localhost";
    private BufferedReader in;
    private BufferedWriter out;
    private String userName;
    private boolean flagConn = false;    // для отслеживания состояния коннекта к серверу
    private Thread th;

    // метод получения ввода пользователя
    private String getOutMsg() {
        if (!"".equals(txtUserInput.getText())) {
            return txtUserInput.getText();
        } else {
            return null;
        }
    }

    private void sendMsg(String msg) {
        try {
            String buf = txtName.getText() + ": " + msg;
            out.write(buf);
            out.write("\n");
            out.flush();
            txtUserInput.setText("");
        } catch (IOException exs) {

        }
    }

    /*===============================================================
     метод с главным циклом прослушивания и ожидания коннектов
     */
    public void tryToConnect() throws IOException {
        userName = txtName.getText();
        if (!userName.equals(null) && !userName.equals("")) {
            s = new Socket(HOST, PORT);

            if (s.isConnected()) {
                flagConn = true;
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                th = new Thread(new Reciever());
                out.write(userName+" enjoyed to the chat !");
                out.write("\n");
                out.flush();
                th.start();
                /*+++++++++++++++++++++++++++*/
                lblStatus.setEnabled(true);
                lblStatus.setText("Online");
                //   txtIO.setRows(100);
                lblStatus.setForeground(Color.green);
                btnSend.setEnabled(true);
                txtUserInput.setEnabled(true);
                txtIO.setEnabled(true);
                btnDisconnect.setVisible(true);
                btnConnect.setVisible(false);
                txtName.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Could not connect to the chat-server!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You should type your nickname");
        }
    }

    public client1() throws UnknownHostException, IOException {
        flagConn = false;

        // new Thread(new Reciever()).start();
        initComponents();

// По умолчанию пока не было удачного коннекта все компоненты недоступны
        lblStatus.setEnabled(false);
        btnSend.setEnabled(false);
        txtUserInput.setEnabled(false);
        txtUserInput.setText("type message");
        txtUserInput.setForeground(Color.gray);
        txtIO.setEnabled(false);
        btnDisconnect.setVisible(false);
        txtIO.setEditable(false);
        txtIO.setBackground(Color.white);
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblName = new javax.swing.JLabel();
        txtUserInput = new java.awt.TextField();
        btnSend = new java.awt.Button();
        txtName = new java.awt.TextField();
        btnConnect = new java.awt.Button();
        lblStatus = new javax.swing.JLabel();
        btnDisconnect = new java.awt.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIO = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLblName.setForeground(new java.awt.Color(102, 102, 255));
        jLblName.setText("Name:");

        txtUserInput.setForeground(new java.awt.Color(204, 204, 204));
        txtUserInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserInputMouseClicked(evt);
            }
        });
        txtUserInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserInputFocusGained(evt);
            }
        });
        txtUserInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserInputKeyPressed(evt);
            }
        });

        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSend.setLabel("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtName.setForeground(new java.awt.Color(204, 204, 204));
        txtName.setText("name");
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        btnConnect.setLabel("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        lblStatus.setForeground(new java.awt.Color(255, 0, 0));
        lblStatus.setText("Offline");

        btnDisconnect.setLabel("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        txtIO.setColumns(20);
        txtIO.setRows(5);
        jScrollPane2.setViewportView(txtIO);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblName)
                    .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        if (!txtName.getText().equals("name")) {
            try {

                tryToConnect();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error ocured");
            }
        } else {
            JOptionPane.showMessageDialog(this, "You should type your nickname");
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNameMouseClicked
        txtName.setForeground(Color.black);
        txtName.setText("");

    }//GEN-LAST:event_txtNameMouseClicked

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtName.setForeground(Color.black);
            txtName.setText("");
            if (!txtName.getText().equals("")) {
                try {
                    tryToConnect();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error ocure");
                }
            } else {
                JOptionPane.showMessageDialog(this, "You should type your nickname");
            }
        } else {
            txtName.setForeground(Color.black);
            txtName.setText("");
        }
    }//GEN-LAST:event_txtNameKeyPressed

    private void txtUserInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserInputMouseClicked
        txtUserInput.setForeground(Color.black);
        txtUserInput.setText("");
    }//GEN-LAST:event_txtUserInputMouseClicked

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if (flagConn) {
            if (!txtUserInput.getText().equals("")) {
                sendMsg(getOutMsg());
               
            }
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        try {
            sendMsg(userName+" now leaving the chat!");
            this.s.close();
        } catch (IOException ex) {

        }
        if (s.isClosed()) {

            flagConn = false;
            lblStatus.setEnabled(false); 
            btnSend.setEnabled(false);
            txtUserInput.setEnabled(false);
            txtIO.setEnabled(false);
            btnDisconnect.setVisible(false);
            txtIO.setEditable(false);
            txtIO.setBackground(Color.white);
            btnConnect.setVisible(true);
            btnConnect.setEnabled(true);
            txtName.setEditable(true);

        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void txtUserInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserInputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (flagConn) {
                if (!txtUserInput.getText().equals("")) {
                    sendMsg(getOutMsg());
                }
            }

        }
    }//GEN-LAST:event_txtUserInputKeyPressed

    private void txtUserInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserInputFocusGained
        if (txtUserInput.getForeground() == Color.GRAY) {
            txtUserInput.setText("");
            txtUserInput.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtUserInputFocusGained

 /*   private void checkMsg(String forCheking) {
        
    }*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new client1().setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(client1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
    
    
// run() вызывается после создания нити в конструкторе клиента
    
    
    
    
    private class Reciever implements Runnable {

        public void run() {

            while (!s.isClosed()) {    // если коннект есть
                String fromServer = null;
                try {
                    fromServer = in.readLine();
                    txtIO.append(fromServer);
                    txtIO.append("\n");
             //    txtIO.invalidate();
                    //    txtIO.read(in, s);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(client1.this, "Connection lost!");
                    flagConn = false;
                    lblStatus.setText("Offline");
                    lblStatus.setEnabled(false);
                }

            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnConnect;
    private java.awt.Button btnDisconnect;
    private java.awt.Button btnSend;
    private javax.swing.JLabel jLblName;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea txtIO;
    private java.awt.TextField txtName;
    private java.awt.TextField txtUserInput;
    // End of variables declaration//GEN-END:variables
}
