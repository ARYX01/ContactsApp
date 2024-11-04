package com.turing.rubrica;

import com.formdev.flatlaf.FlatDarkLaf;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.turing.rubrica.entity.Persona;
import java.util.List;

/**
 * @description Desktop app
 * @author ARYX
 */
public class RubricaApp extends JFrame {
    
    private final ServiceLogic service = new ServiceLogic();

    public RubricaApp() {
        initComponents();
        fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jScrollPaneRubrica = new javax.swing.JScrollPane();
        jTableRubrica = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelActions = new javax.swing.JPanel();
        jButtonActionNew = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(32767, 0));
        jButtonActionEdit = new javax.swing.JButton();
        jButtonActionDelete = new javax.swing.JButton();
        jPanelEditPersona = new javax.swing.JPanel();
        jPanelPersonaForm = new javax.swing.JPanel();
        jLabelEditTitle = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelSurname = new javax.swing.JLabel();
        jTextFieldSurname = new javax.swing.JTextField();
        jLabelAddress = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabelPhoneNo = new javax.swing.JLabel();
        jTextFieldPhoneNo = new javax.swing.JTextField();
        jLabelAge = new javax.swing.JLabel();
        jFormattedTextFieldAge = new javax.swing.JFormattedTextField();
        jTextFieldId = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelActions1 = new javax.swing.JPanel();
        jButtonActionSave = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(32767, 0));
        jButtonActionDiscard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rubrica");
        setMinimumSize(new java.awt.Dimension(550, 650));
        setPreferredSize(null);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanelMain.setFocusable(false);
        jPanelMain.setMinimumSize(new java.awt.Dimension(500, 600));
        jPanelMain.setLayout(new javax.swing.BoxLayout(jPanelMain, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPaneRubrica.setMinimumSize(new java.awt.Dimension(500, 300));

        jTableRubrica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cognome", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRubrica.setMinimumSize(new java.awt.Dimension(300, 80));
        jTableRubrica.setName(""); // NOI18N
        jTableRubrica.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableRubrica.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableRubrica.setShowGrid(true);
        jTableRubrica.getTableHeader().setReorderingAllowed(false);
        jScrollPaneRubrica.setViewportView(jTableRubrica);

        jPanelMain.add(jScrollPaneRubrica);

        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 1));
        jSeparator1.setRequestFocusEnabled(false);
        jPanelMain.add(jSeparator1);

        jPanelActions.setMaximumSize(new java.awt.Dimension(32767, 25));
        jPanelActions.setMinimumSize(new java.awt.Dimension(300, 25));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5);
        flowLayout1.setAlignOnBaseline(true);
        jPanelActions.setLayout(flowLayout1);

        jButtonActionNew.setText("Nuovo");
        jButtonActionNew.setMaximumSize(new java.awt.Dimension(85, 27));
        jButtonActionNew.setMinimumSize(new java.awt.Dimension(85, 27));
        jButtonActionNew.setPreferredSize(new java.awt.Dimension(85, 27));
        jButtonActionNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionNewActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonActionNew);
        jPanelActions.add(filler1);

        jButtonActionEdit.setText("Modifica");
        jButtonActionEdit.setMaximumSize(new java.awt.Dimension(85, 27));
        jButtonActionEdit.setMinimumSize(new java.awt.Dimension(85, 27));
        jButtonActionEdit.setPreferredSize(new java.awt.Dimension(85, 27));
        jButtonActionEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionEditActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonActionEdit);

        jButtonActionDelete.setText("Elimina");
        jButtonActionDelete.setMaximumSize(new java.awt.Dimension(85, 27));
        jButtonActionDelete.setMinimumSize(new java.awt.Dimension(85, 27));
        jButtonActionDelete.setPreferredSize(new java.awt.Dimension(85, 27));
        jButtonActionDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionDeleteActionPerformed(evt);
            }
        });
        jPanelActions.add(jButtonActionDelete);

        jPanelMain.add(jPanelActions);

        getContentPane().add(jPanelMain, "cardMain");

        jPanelEditPersona.setFocusable(false);
        jPanelEditPersona.setMinimumSize(new java.awt.Dimension(500, 600));
        jPanelEditPersona.setLayout(new javax.swing.BoxLayout(jPanelEditPersona, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelPersonaForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jPanelPersonaForm.setLayout(new java.awt.GridLayout(13, 0));

        jLabelEditTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEditTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEditTitle.setText("Nuovo contatto");
        jPanelPersonaForm.add(jLabelEditTitle);

        jLabelName.setLabelFor(jTextFieldName);
        jLabelName.setText("Nome");
        jLabelName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 2, 2));
        jPanelPersonaForm.add(jLabelName);

        jTextFieldName.setToolTipText("Inserisci nome contatto");
        jTextFieldName.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jTextFieldName.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanelPersonaForm.add(jTextFieldName);

        jLabelSurname.setLabelFor(jTextFieldSurname);
        jLabelSurname.setText("Cognome");
        jLabelSurname.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelSurname.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 2, 2));
        jLabelSurname.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanelPersonaForm.add(jLabelSurname);

        jTextFieldSurname.setToolTipText("Inserisci cognome contatto");
        jTextFieldSurname.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanelPersonaForm.add(jTextFieldSurname);

        jLabelAddress.setLabelFor(jTextFieldAddress);
        jLabelAddress.setText("Indirizzo");
        jLabelAddress.setToolTipText("");
        jLabelAddress.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 2, 2));
        jPanelPersonaForm.add(jLabelAddress);

        jTextFieldAddress.setToolTipText("Inserisci indirizzo contatto");
        jTextFieldAddress.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanelPersonaForm.add(jTextFieldAddress);

        jLabelPhoneNo.setLabelFor(jTextFieldPhoneNo);
        jLabelPhoneNo.setText("Telefono");
        jLabelPhoneNo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelPhoneNo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 2, 2));
        jPanelPersonaForm.add(jLabelPhoneNo);

        jTextFieldPhoneNo.setToolTipText("Inserisci telefono contatto");
        jTextFieldPhoneNo.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanelPersonaForm.add(jTextFieldPhoneNo);

        jLabelAge.setText("Età");
        jLabelAge.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelAge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 2, 2));
        jPanelPersonaForm.add(jLabelAge);

        jFormattedTextFieldAge.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldAge.setToolTipText("Inserisci età contatto");
        jPanelPersonaForm.add(jFormattedTextFieldAge);

        jTextFieldId.setEditable(false);
        jTextFieldId.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jTextFieldId.setForeground(new java.awt.Color(30, 30, 30));
        jTextFieldId.setBorder(null);
        jTextFieldId.setEnabled(false);
        jTextFieldId.setFocusable(false);
        jPanelPersonaForm.add(jTextFieldId);

        jPanelEditPersona.add(jPanelPersonaForm);

        jSeparator2.setMaximumSize(new java.awt.Dimension(32767, 10));
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 1));
        jSeparator2.setRequestFocusEnabled(false);
        jPanelEditPersona.add(jSeparator2);

        jPanelActions1.setMaximumSize(new java.awt.Dimension(32767, 25));
        jPanelActions1.setMinimumSize(new java.awt.Dimension(300, 25));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5);
        flowLayout2.setAlignOnBaseline(true);
        jPanelActions1.setLayout(flowLayout2);

        jButtonActionSave.setText("Salva");
        jButtonActionSave.setMaximumSize(new java.awt.Dimension(85, 27));
        jButtonActionSave.setMinimumSize(new java.awt.Dimension(85, 27));
        jButtonActionSave.setPreferredSize(new java.awt.Dimension(85, 27));
        jButtonActionSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionSaveActionPerformed(evt);
            }
        });
        jPanelActions1.add(jButtonActionSave);
        jPanelActions1.add(filler2);

        jButtonActionDiscard.setText("Annulla");
        jButtonActionDiscard.setMaximumSize(new java.awt.Dimension(85, 27));
        jButtonActionDiscard.setMinimumSize(new java.awt.Dimension(85, 27));
        jButtonActionDiscard.setPreferredSize(new java.awt.Dimension(85, 27));
        jButtonActionDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionDiscardActionPerformed(evt);
            }
        });
        jPanelActions1.add(jButtonActionDiscard);

        jPanelEditPersona.add(jPanelActions1);

        getContentPane().add(jPanelEditPersona, "cardEdit");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActionNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionNewActionPerformed
        jLabelEditTitle.setText("Nuovo contatto");
        changePanel("edit");
    }//GEN-LAST:event_jButtonActionNewActionPerformed

    private void jButtonActionDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionDiscardActionPerformed
        setEditInputs(null,"","","","",null);
        changePanel("main");
    }//GEN-LAST:event_jButtonActionDiscardActionPerformed

    private void jButtonActionSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionSaveActionPerformed
        // Get fields from form inputs
        String name = jTextFieldName.getText();
        String surname = jTextFieldSurname.getText();
        String address = jTextFieldAddress.getText();
        String phoneNo = jTextFieldPhoneNo.getText();
        Integer tmpAge = null;
        if(jFormattedTextFieldAge.getValue() != null)
            tmpAge = ((Number)jFormattedTextFieldAge.getValue()).intValue();
        System.out.println("- SAVING CONTACT -");
        System.out.println("name: "+name);
        System.out.println("surname: "+surname);
        System.out.println("address: "+address);
        System.out.println("phoneNo: "+phoneNo);
        System.out.println("age: "+tmpAge);
        
        String id = jTextFieldId.getText();
        // Edit contact
        if(!id.isBlank()){
            System.out.println("id: "+id);
            service.editContact(Integer.parseInt(id), name, surname, address, phoneNo, tmpAge);
            fillTable();
        }
        // New contact
        else{
            service.addContact(name, surname, address, phoneNo, tmpAge);
            // Add contact to jTableRubrica
            DefaultTableModel model = (DefaultTableModel) jTableRubrica.getModel();
            Object[] rowPersona = new Object[]{name, surname, phoneNo};
            model.addRow(rowPersona);
        }
        
        // Close edit mode and go back to main list
        setEditInputs(null, "","","","",null);
        changePanel("main");
    }//GEN-LAST:event_jButtonActionSaveActionPerformed

    private void jButtonActionDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionDeleteActionPerformed
        // Get selected row
        int row = jTableRubrica.getSelectedRow();
        if(row == -1){
            // Open error dialog
            JOptionPane.showMessageDialog(jPanelMain, "Per eliminare è necessario prima selezionare una persona.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Persona selected = service.getContacts().get(row);

        // Open confirm dialog
        String[] options = {"Si","No"};int res = JOptionPane.showOptionDialog(jPanelMain, "Desideri eliminare la persona "+selected.getNome()+" ?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //JOptionPane.showConfirmDialog(jPanelMain, "Desideri eliminare la persona "+selected.getNome()+" ?", "Conferma", JOptionPane.YES_NO_OPTION);

        // if confirm is YES delete from contacts
        if(res==0){
            service.deleteContact(selected.getId());
            DefaultTableModel model = (DefaultTableModel) jTableRubrica.getModel();
            model.removeRow(row);
        }
    }//GEN-LAST:event_jButtonActionDeleteActionPerformed

    private void jButtonActionEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionEditActionPerformed
        // Get selected row
        int row = jTableRubrica.getSelectedRow();
        if(row == -1){
            // Open error dialog
            JOptionPane.showMessageDialog(jPanelMain, "Per modificare è necessario prima selezionare una persona.", "Attenzione", JOptionPane.WARNING_MESSAGE);
            return;
        }
        jLabelEditTitle.setText("Modifica contatto");
        
        // Open edit mode and fill inputs
        changePanel("edit");
        Persona selected = service.getContacts().get(row);
        setEditInputs(selected.getId(), selected.getNome(), selected.getCognome(), selected.getIndirizzo(), selected.getTelefono(), selected.getEta());
    }//GEN-LAST:event_jButtonActionEditActionPerformed

    
    public static void main(String args[]) {
        /* Set the app Theme look and feel */
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(RubricaApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the app window */
        java.awt.EventQueue.invokeLater(() -> {
            new RubricaApp().setVisible(true);
        });
    }
    
    public void changePanel(String panel){
        java.awt.Container c = getContentPane();
        java.awt.CardLayout cl = (java.awt.CardLayout)c.getLayout();
        if(panel.equalsIgnoreCase("main")) 
            cl.first(c);
        else
            cl.last(c);
    }
   
    
    private void fillTable(){
        DefaultTableModel model = (DefaultTableModel) jTableRubrica.getModel();
        model.setRowCount(0);
      
        //FOR TESTING PURPOSES
        List<Persona> contacts = service.getContacts();
        contacts.forEach(contact -> {
            Object[] rowPersona = new Object[]{contact.getNome(), contact.getCognome(), contact.getTelefono()};
            model.addRow(rowPersona);
        });
        
        
    }
    
    private void setEditInputs(Integer id, String name, String surname, String address, String phoneNo, Integer age){
        if(id==null)
            jTextFieldId.setText("");
        else
            jTextFieldId.setText(id.toString());
        jTextFieldName.setText(name);
        jTextFieldSurname.setText(surname);
        jTextFieldAddress.setText(address);
        jTextFieldPhoneNo.setText(phoneNo);
        jFormattedTextFieldAge.setValue(age);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButtonActionDelete;
    private javax.swing.JButton jButtonActionDiscard;
    private javax.swing.JButton jButtonActionEdit;
    private javax.swing.JButton jButtonActionNew;
    private javax.swing.JButton jButtonActionSave;
    private javax.swing.JFormattedTextField jFormattedTextFieldAge;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelEditTitle;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPhoneNo;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelActions1;
    private javax.swing.JPanel jPanelEditPersona;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelPersonaForm;
    private javax.swing.JScrollPane jScrollPaneRubrica;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableRubrica;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPhoneNo;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables
}
