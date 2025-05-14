package com.projectmanager.frms;

import com.projectmanager.models.Projetos;
import com.projectmanager.models.Requisitos;
import com.projectmanager.mysql.MySqlProjetos;
import com.projectmanager.mysql.MySqlRequisitos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

public class FrmGerProjeto extends javax.swing.JFrame {
   
    
    FrmCadRequisitos cadRequisito = null;
    
    public static FrmGerRequisitos gerRequisito = null;  
    
    public static List<Requisitos> listaRequisitos = new ArrayList<>();
    
    public static Requisitos requisito = null;
    
    Projetos projeto = new Projetos();
    
    String btn = null;   
   
    public FrmGerProjeto() throws SQLException {            
        
        initComponents();
               
        ButtonGroup bgStatus = new ButtonGroup();
        
        bgStatus.add(btnStatusCompleto);
        bgStatus.add(btnStatusDoing);
        bgStatus.add(btnStatusManutencao);
        bgStatus.add(btnStatusTodo);        
    
        txtProjeto.setText(FrmMenuPrincipal.nomeProjeto);
                
        lblUsuario.setText(FrmMenuPrincipal.nomeComplUser);        
                
        projeto = MySqlProjetos.projByIdUserAndIdProj(FrmLogin.IdUser,FrmMenuPrincipal.idProjeto);
        
        txtDescricao.setText(projeto.getDescriçao());
        
        txtVersao.setText(projeto.getVersao());
        
        btn = projeto.getStatus();
        
        System.out.println("Loaded btn: " + btn );
        
        switch (btn){
            
            case "TO DO":
                btnStatusTodo.setSelected(true);
                break;
            case "MANUTENCAO":
                btnStatusManutencao.setSelected(true);
                break;
            case "DOING":
                btnStatusDoing.setSelected(true);
                break;  
            case "COMPLETO":
                btnStatusCompleto.setSelected(true);
                break;      
            default:
                btnStatusManutencao.setSelected(true);
        }
            
        DefaultTableModel modelTblRequisitos = (DefaultTableModel) tblRequisitos.getModel();
                           
        modelTblRequisitos.setNumRows(0);
        
        listaRequisitos = MySqlRequisitos.requisitosByIdUserAndIdProj(FrmLogin.IdUser,FrmMenuPrincipal.idProjeto);
        
        System.out.println("FrmMenuPrincipal.idProjeto: " + FrmMenuPrincipal.idProjeto );
        
        System.out.println("projeto.getId(): " + projeto.getId() );
        
        
        if(!listaRequisitos.isEmpty())
        {
            listaRequisitos.forEach(req -> {
                modelTblRequisitos.addRow(new Object[]
                    { 
                                req.getIdReq(),
                                req.getNomeReq()
                    });
                });   
            tblRequisitos.setRowSelectionInterval(0, 0);
        }    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgStatus = new javax.swing.ButtonGroup();
        btnStatusManutencao = new javax.swing.JRadioButton();
        btnStatusDoing = new javax.swing.JRadioButton();
        btnStatusCompleto = new javax.swing.JRadioButton();
        btnSalvarProj = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        btnStatusTodo = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRequisitos = new javax.swing.JTable();
        lblUsuario = new javax.swing.JLabel();
        btnCadRequisito = new javax.swing.JButton();
        btnVisRequisito = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        lblVrs = new javax.swing.JLabel();
        txtProjeto = new javax.swing.JTextField();
        txtVersao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bgStatus.add(btnStatusManutencao);
        btnStatusManutencao.setText("Manutenção");
        btnStatusManutencao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusManutencaoActionPerformed(evt);
            }
        });

        bgStatus.add(btnStatusDoing);
        btnStatusDoing.setText("Doing");
        btnStatusDoing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusDoingActionPerformed(evt);
            }
        });

        bgStatus.add(btnStatusCompleto);
        btnStatusCompleto.setText("Completo");
        btnStatusCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusCompletoActionPerformed(evt);
            }
        });

        btnSalvarProj.setText("Salvar");
        btnSalvarProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarProjActionPerformed(evt);
            }
        });

        lblStatus.setText("Status:");

        bgStatus.add(btnStatusTodo);
        btnStatusTodo.setText("To do");
        btnStatusTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusTodoActionPerformed(evt);
            }
        });

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jLabel1.setText("Descrição:");

        jLabel3.setText("Requisitos:");

        jLabel4.setText("Projeto:");

        jLabel5.setText("Usuário:");

        tblRequisitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRequisitos);
        if (tblRequisitos.getColumnModel().getColumnCount() > 0) {
            tblRequisitos.getColumnModel().getColumn(0).setMinWidth(50);
            tblRequisitos.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        lblUsuario.setText("...");

        btnCadRequisito.setText("Cadastrar requisito");
        btnCadRequisito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadRequisitoActionPerformed(evt);
            }
        });

        btnVisRequisito.setText("Visualizar requisito");
        btnVisRequisito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisRequisitoActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblVrs.setText("Versao:");

        txtProjeto.setText("...");

        txtVersao.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblVrs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnStatusManutencao)
                                    .addComponent(btnStatusTodo))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(btnSalvarProj)
                                .addGap(18, 18, 18)
                                .addComponent(btnVoltar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnStatusDoing)
                                    .addComponent(btnStatusCompleto)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel3)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(btnCadRequisito, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisRequisito, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27))
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblUsuario))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadRequisito)
                    .addComponent(btnVisRequisito))
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStatusTodo)
                    .addComponent(btnStatusDoing)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStatusManutencao)
                    .addComponent(btnStatusCompleto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvarProj, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblVrs)
                        .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStatusManutencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusManutencaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatusManutencaoActionPerformed

    private void btnStatusDoingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusDoingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatusDoingActionPerformed

    private void btnStatusCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatusCompletoActionPerformed

    private void btnSalvarProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProjActionPerformed
        
        projeto.setNomeProj(txtProjeto.getText().toUpperCase());
        projeto.setDescriçao(txtDescricao.getText());
        projeto.setProprietario(FrmLogin.IdUser);
        projeto.setVersao(txtVersao.getText());       
                    
        if (btnStatusCompleto.getSelectedObjects() != null)
            btn = "COMPLETO";
        else if (btnStatusDoing.getSelectedObjects() != null)
            btn = "DOING";
        else if (btnStatusManutencao.getSelectedObjects() != null)
            btn = "MANUTENCAO";           
        else if (btnStatusTodo.getSelectedObjects() != null)
            btn = "TO DO";
           
        projeto.setStatus(btn);
        
        MySqlProjetos.atualizarProjeto(projeto);
        
        //FrmMenuPrincipal.gerProj.setVisible(false);
        //super.dispose();
    }//GEN-LAST:event_btnSalvarProjActionPerformed

    private void btnStatusTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusTodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatusTodoActionPerformed

    private void btnCadRequisitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadRequisitoActionPerformed
        
       // FrmGerProjeto.nomeProjeto = (String)(tblProjetos.getValueAt((tblProjetos.getSelectedRow()), 1));
        
        cadRequisito = new FrmCadRequisitos();
        
        cadRequisito.setVisible(true);
        
        FrmMenuPrincipal.gerProj.setVisible(false);
        
    }//GEN-LAST:event_btnCadRequisitoActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        
        try {
            FrmLogin.menuPrincipal = new FrmMenuPrincipal();
        } catch (SQLException ex) {
            Logger.getLogger(FrmGerProjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        FrmLogin.menuPrincipal.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnVisRequisitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisRequisitoActionPerformed
        
        if(!listaRequisitos.isEmpty())
        {
        
            int idR = (int)(tblRequisitos.getValueAt((tblRequisitos.getSelectedRow()), 0));
        
            listaRequisitos.forEach(req -> {
           
                if (idR == req.getIdReq())
                {
                    requisito = req;               
                }    
            });           
        
            gerRequisito = new FrmGerRequisitos();
        
            gerRequisito.setVisible(true);
        
            FrmMenuPrincipal.gerProj.setVisible(false);
        
        }
        
    }//GEN-LAST:event_btnVisRequisitoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGerProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGerProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGerProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGerProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrmGerProjeto().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FrmGerProjeto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgStatus;
    private javax.swing.JButton btnCadRequisito;
    private javax.swing.JButton btnSalvarProj;
    private javax.swing.JRadioButton btnStatusCompleto;
    private javax.swing.JRadioButton btnStatusDoing;
    private javax.swing.JRadioButton btnStatusManutencao;
    private javax.swing.JRadioButton btnStatusTodo;
    private javax.swing.JButton btnVisRequisito;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVrs;
    private javax.swing.JTable tblRequisitos;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtProjeto;
    private javax.swing.JTextField txtVersao;
    // End of variables declaration//GEN-END:variables

}
