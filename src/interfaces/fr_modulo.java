package interfaces;


//import clases.Archivo;

import clases.AnalisisLexico;
import clases.ExampleString;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.jw.menage.ui.components.TextLineNumber;


/**
 *
 * @author Pablo
 */
public class fr_modulo extends javax.swing.JFrame {
    private String r = "";
    private DefaultStyledDocument doc;
    private static ArrayList<String> lista3 = new ArrayList<>();
    static ArrayList<String> listaLexemas;
    static String DireccionPath = "";
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos niklaus ","nik");
    
    /*
        Data Principal
    */
    
    AnalisisLexico analizadorLexico;
    /**/
    public fr_modulo() {   
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet red = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        final AttributeSet Black = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        final AttributeSet blue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.blue);
        final AttributeSet green = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.green);
        final AttributeSet yellow = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.yellow);
        final AttributeSet orange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.orange);
        final AttributeSet pink = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.pink);
        final AttributeSet gray = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.gray);
        
        doc = new DefaultStyledDocument() {

            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = tp_CodeEditor.getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {

                        if (text.substring(wordL, wordR).matches("(\\W)*(hacer|inicio_programa|fin|ENFIERRAR|$)")) {
                            setCharacterAttributes(wordL, wordR - wordL, red, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(task|servo|light|solenoid|display|sensor|while|"
                                + "for|if|none|params|maincode|int|float|text)")) {
                            setCharacterAttributes(wordL, wordR - wordL, blue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(caso)")) {
                            setCharacterAttributes(wordL, wordR - wordL, green, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(==)")) {
                            setCharacterAttributes(wordL, wordR - wordL, orange, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Coleccion|Atributo|Relacion|JSON|BaseDatos|"
                                + "EmbebidoParcial|EmbebidoCompleto)")) {
                            setCharacterAttributes(wordL, wordR - wordL, yellow, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(begin|end)")) {
                            setCharacterAttributes(wordL, wordR - wordL, pink, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Name|Author|(_([A-Za-z])+))")) {
                            setCharacterAttributes(wordL, wordR - wordL, gray, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, Black, false);
                        }

                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                String text = tp_CodeEditor.getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*(function|private)")) {
                    setCharacterAttributes(before, after - before, red, false);
                } else if (text.substring(before, after).matches("(\\W)*(for|while)")) {
                    setCharacterAttributes(before, after - before, blue, false);
                } else if (text.substring(before, after).matches("(\\W)*(if|else)")) {
                    setCharacterAttributes(before, after - before, green, false);
                } else if (text.substring(before, after).matches("(\\W)*(int|string)")) {
                    setCharacterAttributes(before, after - before, orange, false);
                } else if (text.substring(before, after).matches("(\\W)*(>|<)")) {
                    setCharacterAttributes(before, after - before, yellow, false);
                } else if (text.substring(before, after).matches("(\\W)*(begin|end)")) {
                    setCharacterAttributes(before, after - before, pink, false);
                } else {
                    setCharacterAttributes(before, after - before, Black, false);
                }

            }
        };
        
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        pack();

        TextLineNumber lineNumber = new TextLineNumber(tp_CodeEditor, 4);
        pn_CodeEditor.add(lineNumber,BorderLayout.WEST);
        tp_CodeEditor.requestFocus();
    }
    
    //-----------------------------------------------Metodos Necesarios para el color------------------------------------------------------
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }
       private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
//--------------------------------------------------------------FIN--------------------------------------------------------------------


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        pn_Tools = new javax.swing.JPanel();
        lb_NewFile = new javax.swing.JLabel();
        lb_NewProject = new javax.swing.JLabel();
        lb_Compile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tp_ProjectFiles = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tp_ProjectCodeFiles = new javax.swing.JTabbedPane();
        Panel_Central = new javax.swing.JScrollPane();
        pn_CodeEditor = new javax.swing.JPanel();
        tp_CodeEditor = new javax.swing.JTextPane(doc);
        tp_Output = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_output = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setLayout(new java.awt.BorderLayout());

        pn_Tools.setMaximumSize(new java.awt.Dimension(1357, 44));
        pn_Tools.setMinimumSize(new java.awt.Dimension(1357, 44));
        pn_Tools.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 5));

        lb_NewFile.setBackground(new java.awt.Color(255, 255, 0));
        lb_NewFile.setForeground(new java.awt.Color(255, 255, 0));
        lb_NewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_NewFile.png"))); // NOI18N
        lb_NewFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lb_NewFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_NewFileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_NewFileMouseExited(evt);
            }
        });
        pn_Tools.add(lb_NewFile);

        lb_NewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_NewProject.png"))); // NOI18N
        lb_NewProject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lb_NewProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_NewProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_NewProjectMouseExited(evt);
            }
        });
        pn_Tools.add(lb_NewProject);

        lb_Compile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_RunProject.png"))); // NOI18N
        lb_Compile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lb_Compile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_CompileMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_CompileMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_CompileMouseEntered(evt);
            }
        });
        pn_Tools.add(lb_Compile);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_Save.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pn_Tools.add(jLabel1);

        jDesktopPane1.add(pn_Tools, java.awt.BorderLayout.PAGE_START);

        tp_ProjectFiles.setBackground(new java.awt.Color(204, 204, 204));
        tp_ProjectFiles.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tp_ProjectFiles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jFormattedTextField1.setText("jFormattedTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
        );

        tp_ProjectFiles.addTab("Projects", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Ejecutar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(239, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        tp_ProjectFiles.addTab("Services", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tp_ProjectFiles.addTab("Files", jPanel3);

        jDesktopPane1.add(tp_ProjectFiles, java.awt.BorderLayout.WEST);

        tp_ProjectCodeFiles.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tp_ProjectCodeFiles.setPreferredSize(new java.awt.Dimension(500, 50));

        pn_CodeEditor.setLayout(new java.awt.BorderLayout());

        tp_CodeEditor.setFont(new java.awt.Font("Courier 10 Pitch", 0, 13)); // NOI18N
        tp_CodeEditor.setText("task _hello params none{\n\tObjeto _a==50$\n\tObjeto b==60$\n\t¡\n}");
        tp_CodeEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tp_CodeEditorKeyPressed(evt);
            }
        });
        pn_CodeEditor.add(tp_CodeEditor, java.awt.BorderLayout.CENTER);

        Panel_Central.setViewportView(pn_CodeEditor);

        tp_ProjectCodeFiles.addTab("tab1", Panel_Central);

        jDesktopPane1.add(tp_ProjectCodeFiles, java.awt.BorderLayout.CENTER);

        tp_Output.setPreferredSize(new java.awt.Dimension(1000, 327));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1000, 299));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        tp_Output.addTab("Tokens", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        ta_output.setColumns(20);
        ta_output.setRows(5);
        jScrollPane1.setViewportView(ta_output);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        tp_Output.addTab("Output", jPanel5);

        jDesktopPane1.add(tp_Output, java.awt.BorderLayout.SOUTH);

        jMenu2.setText("File");

        jMenuItem1.setText("New Project");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("New File");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Open Project");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Open Recent Project");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_Undo.png"))); // NOI18N
        jMenuItem5.setText("Undo");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_Redo.png"))); // NOI18N
        jMenuItem6.setText("Redo");
        jMenu3.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_Cut.png"))); // NOI18N
        jMenuItem7.setText("Cut");
        jMenu3.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_Copy.png"))); // NOI18N
        jMenuItem8.setText("Copy");
        jMenu3.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_Paste.png"))); // NOI18N
        jMenuItem9.setText("Paste");
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        	
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tp_CodeEditorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tp_CodeEditorKeyPressed

    }//GEN-LAST:event_tp_CodeEditorKeyPressed

    private void lb_NewFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NewFileMouseEntered
        lb_NewFile.setBorder(BorderFactory.createLineBorder(Color.red));
    }//GEN-LAST:event_lb_NewFileMouseEntered

    private void lb_NewFileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NewFileMouseExited
        lb_NewFile.setBorder(BorderFactory.createLineBorder(Color.blue));
    }//GEN-LAST:event_lb_NewFileMouseExited

    private void lb_NewProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NewProjectMouseEntered
        lb_NewProject.setBorder(BorderFactory.createLineBorder(Color.red));
    }//GEN-LAST:event_lb_NewProjectMouseEntered

    private void lb_NewProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NewProjectMouseExited
        lb_NewProject.setBorder(BorderFactory.createLineBorder(Color.blue));
    }//GEN-LAST:event_lb_NewProjectMouseExited

    private void lb_CompileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_CompileMouseEntered
        lb_Compile.setBorder(BorderFactory.createLineBorder(Color.red));
    }//GEN-LAST:event_lb_CompileMouseEntered

    private void lb_CompileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_CompileMouseExited
        lb_Compile.setBorder(BorderFactory.createLineBorder(Color.blue));
    }//GEN-LAST:event_lb_CompileMouseExited

    private void lb_CompileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_CompileMouseClicked
        /*Archivo.createFile(tp_CodeEditor.getText(), "script.sh");
        Runtime aplicacion = Runtime.getRuntime();
        try{
            aplicacion.exec("chmod +x script.sh");
            aplicacion.exec("chmod +x script.sh");
            aplicacion.exec("./script.sh");}
        catch(Exception e){System.out.println(e);}*/
        analizadorLexico=new AnalisisLexico(tp_CodeEditor.getText());
        analizadorLexico.generateTokens();
        ta_output.setText(analizadorLexico.createErrorList());
    }//GEN-LAST:event_lb_CompileMouseClicked

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
            java.util.logging.Logger.getLogger(fr_modulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fr_modulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fr_modulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fr_modulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fr_modulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Panel_Central;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_Compile;
    private javax.swing.JLabel lb_NewFile;
    private javax.swing.JLabel lb_NewProject;
    private javax.swing.JPanel pn_CodeEditor;
    private javax.swing.JPanel pn_Tools;
    private javax.swing.JTextArea ta_output;
    private javax.swing.JTextPane tp_CodeEditor;
    private javax.swing.JTabbedPane tp_Output;
    private javax.swing.JTabbedPane tp_ProjectCodeFiles;
    private javax.swing.JTabbedPane tp_ProjectFiles;
    // End of variables declaration//GEN-END:variables

}
