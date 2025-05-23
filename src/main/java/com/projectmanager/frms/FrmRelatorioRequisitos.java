package com.projectmanager.frms;

import com.projectmanager.models.Requisitos;
import com.projectmanager.mysql.MySqlRequisitos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class FrmRelatorioRequisitos extends javax.swing.JFrame {

    List<Requisitos> listaRequisitos = null;
    
    DefaultTableModel modelTblRelatorio = null;
     
    public FrmRelatorioRequisitos() throws SQLException {
        
        initComponents();     
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRelatorio = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRelatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "IdReq", "Nome ", "Descrição", "IdProjeto", "Modulo", "Funcionalidades", "Data Criação", "IdAutor", "Data Alteração", "IdAutor Alteração", "Versao", "Prioridade", "Complexidade", "Tempo esf. est.", "Estado", "Fase"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRelatorio);
        if (tblRelatorio.getColumnModel().getColumnCount() > 0) {
            tblRelatorio.getColumnModel().getColumn(0).setMaxWidth(50);
            tblRelatorio.getColumnModel().getColumn(3).setMaxWidth(60);
            tblRelatorio.getColumnModel().getColumn(7).setMaxWidth(50);
        }

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel1.setText("Relatórios de requisitos");

        jLabel3.setText("Pesquisa:");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1551, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(641, 641, 641)
                        .addComponent(jLabel1)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(btnVoltar))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        
        super.dispose();
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
   
        modelTblRelatorio =(DefaultTableModel) tblRelatorio.getModel();
        
        modelTblRelatorio.setNumRows(0);
        
        listaRequisitos = new ArrayList<>();
        
        try {
            
            listaRequisitos = MySqlRequisitos.pesquisaRequisitos(txtBusca.getText());
        } 
        catch (SQLException ex) {
            
            Logger.getLogger(FrmRelatorioRequisitos.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                 
        if(!listaRequisitos.isEmpty())        
        {
           
            listaRequisitos.forEach(requisito -> {
            
            modelTblRelatorio.addRow(new Object[]
                {                  
                    requisito.getIdReq(),
                    requisito.getNomeReq(),
                    requisito.getDescricao(),       
                    requisito.getIdProj(),
                    requisito.getModulo(),
                    requisito.getFuncionalidades(),
                    requisito.getDtCriacao(),
                    requisito.getIdAutor(),
                    requisito.getDtUltAlteracao(),
                    requisito.getAutorUltimaMod(),
                    requisito.getVersao(),
                    requisito.getPrioridade(),
                    requisito.getComplexidade(),
                    requisito.getTmpEsforcoEstimado(),
                    requisito.getEstado(),
                    requisito.getFase()
                                                                                         
                });
            });
            
            tblRelatorio.setRowSelectionInterval(0, 0);
        }     
        
    }//GEN-LAST:event_btnPesquisarActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRelatorioRequisitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrmRelatorioRequisitos().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FrmRelatorioRequisitos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRelatorio;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
