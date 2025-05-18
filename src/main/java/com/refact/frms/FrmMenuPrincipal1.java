package com.refact.frms;

import com.projectmanager.frms.FrmCadProjetos;
import com.projectmanager.frms.FrmCadUsuarios;
import com.projectmanager.frms.FrmGerProjeto;
import com.projectmanager.frms.FrmGerUsuario;
import com.projectmanager.frms.FrmLogin;
import com.projectmanager.frms.FrmRelatorioProjetos;
import com.projectmanager.frms.FrmRelatorioRequisitos;
import com.projectmanager.models.NewMain;
import com.projectmanager.models.Projetos;
import com.projectmanager.mysql.MySqlProjetos;
import static com.projectmanager.mysql.MySqlUsuarios.obterNomeCompleto;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class FrmMenuPrincipal1 extends javax.swing.JFrame {

    public static String nomeComplUser;
    
    public static String nomeProjeto;
    
    public static int idProjeto;
    
    public static FrmGerProjeto gerProj = null; 
    
    public static List<Projetos> listaProjetos = null;
    
    public DefaultTableModel modelTblProjetos = null;
    
    public FrmMenuPrincipal1() throws SQLException {
            
        initComponents();
        
        nomeComplUser = obterNomeCompleto(FrmLogin.IdUser);
        
        lblNomeUser.setText(nomeComplUser);                
        
        mostrarTblProjetos();      
        
    }
    
    public void mostrarTblProjetos() throws SQLException{
    
        modelTblProjetos = (DefaultTableModel) tblProjetos.getModel();
        
        modelTblProjetos.setNumRows(0);
                
        listaProjetos = MySqlProjetos.listProjByUser(FrmLogin.IdUser);       
        
        if(!listaProjetos.isEmpty())        
        {
           
            listaProjetos.forEach(proj -> {
            
            modelTblProjetos.addRow(new Object[]
                {                  
                    proj.getId(),
                    proj.getNomeProj(),
                    proj.getStatus()                            
                });
            });
            
            tblProjetos.setRowSelectionInterval(0, 0);
        }    
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProjetos = new javax.swing.JTable();
        btnCadProjeto = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblNomeUser = new javax.swing.JLabel();
        btnVisProj = new javax.swing.JButton();
        btnExcluirProj = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuUserCad = new javax.swing.JMenuItem();
        menuUserGerenciar = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menuRelatorio = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblProjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProjetos);
        if (tblProjetos.getColumnModel().getColumnCount() > 0) {
            tblProjetos.getColumnModel().getColumn(0).setMinWidth(20);
            tblProjetos.getColumnModel().getColumn(0).setMaxWidth(40);
            tblProjetos.getColumnModel().getColumn(1).setMinWidth(300);
        }

        btnCadProjeto.setText("Cadastrar projeto");
        btnCadProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadProjetoActionPerformed(evt);
            }
        });

        jLabel3.setText("Projetos do usuário:");

        lblNomeUser.setText("...");

        btnVisProj.setText("Visualizar projeto");
        btnVisProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisProjActionPerformed(evt);
            }
        });

        btnExcluirProj.setText("Excluir projeto");
        btnExcluirProj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProjActionPerformed(evt);
            }
        });

        jMenu2.setText("Usuários");

        menuUserCad.setText("Cadastrar");
        menuUserCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUserCadActionPerformed(evt);
            }
        });
        jMenu2.add(menuUserCad);

        menuUserGerenciar.setText("Gerenciar");
        menuUserGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUserGerenciarActionPerformed(evt);
            }
        });
        jMenu2.add(menuUserGerenciar);

        jMenuItem8.setText("Alterar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        menuRelatorio.setText("Relatório");
        menuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Projetos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuRelatorio.add(jMenuItem1);

        jMenuItem3.setText("Requisitos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuRelatorio.add(jMenuItem3);

        jMenuBar1.add(menuRelatorio);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVisProj, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirProj, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNomeUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadProjeto)
                    .addComponent(btnVisProj)
                    .addComponent(btnExcluirProj))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void menuUserCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUserCadActionPerformed
        
        FrmCadUsuarios cadUser = new FrmCadUsuarios(); 
        
        cadUser.setVisible(true); 
        
        
    }//GEN-LAST:event_menuUserCadActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
            
        NewMain.login.setVisible(true);
        
        super.dispose();
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void menuUserGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUserGerenciarActionPerformed
         
        FrmGerUsuario grncUser = null; 
        
        try {
            grncUser = new FrmGerUsuario();
            grncUser.setVisible(true); 
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
        }        
       
    }//GEN-LAST:event_menuUserGerenciarActionPerformed

    private void btnCadProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadProjetoActionPerformed
       
        FrmCadProjetos cadProj = null;
        
        cadProj = new FrmCadProjetos();
        
        cadProj.setVisible(true);
        
        FrmLogin.menuPrincipal.setVisible(false);
        
    }//GEN-LAST:event_btnCadProjetoActionPerformed

    private void btnVisProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisProjActionPerformed
       
        FrmMenuPrincipal1.nomeProjeto = (String)(tblProjetos.getValueAt((tblProjetos.getSelectedRow()), 1));
        
        FrmMenuPrincipal1.idProjeto = (int)(tblProjetos.getValueAt((tblProjetos.getSelectedRow()), 0));
        
        System.out.println("Nome do projeto: " + FrmMenuPrincipal1.nomeProjeto);
              
        try {
                    
            gerProj = new FrmGerProjeto();
            
            gerProj.setVisible(true);
            
            FrmLogin.menuPrincipal.setVisible(false);
            
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnVisProjActionPerformed

    private void menuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioActionPerformed

            
    }//GEN-LAST:event_menuRelatorioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      
        FrmRelatorioProjetos relatorioProj = null;
        
        try {
            
            relatorioProj = new FrmRelatorioProjetos();
            
        }
        catch (SQLException ex) {
            
            Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        relatorioProj.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       
        FrmRelatorioRequisitos relatorioReq = null;
        
        try {
            
            relatorioReq = new FrmRelatorioRequisitos();
            
        } 
        catch (SQLException ex) {
            
            Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        relatorioReq.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnExcluirProjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProjActionPerformed
                            
        try {
            
            MySqlProjetos.excluirProjeto((int)(tblProjetos.getValueAt((tblProjetos.getSelectedRow()), 0)));
            
        } 
        
        catch (SQLException ex) {
            
            Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
        }     
       
        try {
            
            mostrarTblProjetos();
            
        } 
        catch (SQLException ex) {
            
            Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }//GEN-LAST:event_btnExcluirProjActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrmMenuPrincipal1().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FrmMenuPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadProjeto;
    private javax.swing.JButton btnExcluirProj;
    private javax.swing.JButton btnVisProj;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblNomeUser;
    private javax.swing.JMenu menuRelatorio;
    private javax.swing.JMenuItem menuUserCad;
    private javax.swing.JMenuItem menuUserGerenciar;
    private javax.swing.JTable tblProjetos;
    // End of variables declaration//GEN-END:variables
}
