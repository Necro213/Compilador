package Lex;

import java.awt.BorderLayout;
import org.jw.menage.ui.components.TextLineNumber;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author necro
 */

// para los comentarios poner una variable de charat(i) y que compare con charat(i+1)
// si el primero es un guion y el segundo tambien no lo tome en el automata que brinque la linea
public class Interface extends javax.swing.JFrame {
    private String r = "";
    private DefaultStyledDocument doc;
    private DefaultTableModel dtm;
    private static ArrayList<String> listaErrores;
    private static ArrayList<String> lista3 = new ArrayList<>();
    private static ArrayList<String> produtions = new ArrayList<String>();
    private static ArrayList<String> lexemes = new ArrayList<String>();
    
    static ArrayList<String> listaLexemas;
    static String DireccionPath = "";
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Fly Plan Compilation ","fpc");

    /**
     * Creates new form Interface
     */
    
    
    public Interface() {
        listaLexemas = new ArrayList<>();
        listaErrores = new ArrayList<String>();
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet red = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        final AttributeSet Black = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        final AttributeSet blue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.blue);
        final AttributeSet orange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(163,102,12));
        final AttributeSet VerAz = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(26,115,86));
        final AttributeSet AzF = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(98,49,255));
        
        doc = new DefaultStyledDocument() {

            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = tp.getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {

                        if (text.substring(wordL, wordR).matches("(\\W)*(new|end)")) {
                            setCharacterAttributes(wordL, wordR - wordL, red, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(|loop|sleep|up|down|left|rigth|forward|"
                                + "backward|" +
                                "rotate|level|speed|stand|start)")) {
                            setCharacterAttributes(wordL, wordR - wordL, blue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(var|sync)")) {
                            setCharacterAttributes(wordL, wordR - wordL, orange, false);
                        }else if (text.substring(wordL, wordR).matches("(\\W)*(plan|fly|on|off)")) {
                            setCharacterAttributes(wordL, wordR - wordL, VerAz, false);
                        }  else{
                            setCharacterAttributes(wordL, wordR - wordL, Black, false);
                        }

                        wordL = wordR;
                    }
                    wordR++;
                }
            }
        };

        this.setUndecorated(true);
        
        
        initComponents();
        tp.setBackground(Color.white);
        TextLineNumber lineNumber = new TextLineNumber(tp, 4);
        lineNumber.setBackground(Color.WHITE);
        PanelTexto.add(lineNumber,BorderLayout.WEST);
        tp.requestFocus();
        //tp.setBackground(Color.DARK_GRAY);
        Errores.setForeground(Color.red);
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

        Panel_Norte = new javax.swing.JPanel();
        Compilar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Panel_Sur = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Errores = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Panel_Derecho = new javax.swing.JPanel();
        Panel_Central = new javax.swing.JScrollPane();
        PanelTexto = new javax.swing.JPanel();
        tp = new javax.swing.JTextPane(doc);
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablatokens = new javax.swing.JTable();
        BarraMenus = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        Nuevo = new javax.swing.JMenuItem();
        Abrir = new javax.swing.JMenuItem();
        Guardar = new javax.swing.JMenuItem();
        GuardadComo = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Compilar2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(175, 50));

        Panel_Norte.setBackground(new java.awt.Color(102, 102, 102));
        Panel_Norte.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Panel_Norte.setMaximumSize(new java.awt.Dimension(32767, 50));
        Panel_Norte.setPreferredSize(new java.awt.Dimension(589, 50));

        Compilar.setBackground(new java.awt.Color(255, 255, 255));
        Compilar.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Code_16px.png"))); // NOI18N
        Compilar.setText("COMPILAR");
        Compilar.setPreferredSize(new java.awt.Dimension(115, 25));
        Compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompilarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/New Copy_16px.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setPreferredSize(new java.awt.Dimension(115, 25));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAbrir.setBackground(new java.awt.Color(255, 255, 255));
        btnAbrir.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/View File_16px.png"))); // NOI18N
        btnAbrir.setText("ABRIR");
        btnAbrir.setPreferredSize(new java.awt.Dimension(115, 25));
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/New Copy_16px.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setPreferredSize(new java.awt.Dimension(115, 25));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/closebtn.png"))); // NOI18N
        jLabel2.setText("Salir");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_NorteLayout = new javax.swing.GroupLayout(Panel_Norte);
        Panel_Norte.setLayout(Panel_NorteLayout);
        Panel_NorteLayout.setHorizontalGroup(
            Panel_NorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_NorteLayout.createSequentialGroup()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Compilar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 492, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(20, 20, 20))
        );
        Panel_NorteLayout.setVerticalGroup(
            Panel_NorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_NorteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_NorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Compilar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(Panel_Norte, java.awt.BorderLayout.NORTH);

        Panel_Sur.setBackground(new java.awt.Color(102, 102, 102));

        Errores.setEditable(false);
        Errores.setColumns(20);
        Errores.setRows(5);
        Errores.setCaretColor(new java.awt.Color(255, 0, 0));
        jScrollPane1.setViewportView(Errores);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SALIDA:");

        javax.swing.GroupLayout Panel_SurLayout = new javax.swing.GroupLayout(Panel_Sur);
        Panel_Sur.setLayout(Panel_SurLayout);
        Panel_SurLayout.setHorizontalGroup(
            Panel_SurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SurLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_SurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                    .addGroup(Panel_SurLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Panel_SurLayout.setVerticalGroup(
            Panel_SurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SurLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(Panel_Sur, java.awt.BorderLayout.PAGE_END);

        Panel_Derecho.setBackground(new java.awt.Color(102, 102, 102));
        Panel_Derecho.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        PanelTexto.setLayout(new java.awt.BorderLayout());

        tp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tp.setText("new fly plan Plan_de_Vuelo\non;\nsync;\n\noff;\nend");
        tp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tpKeyPressed(evt);
            }
        });
        PanelTexto.add(tp, java.awt.BorderLayout.CENTER);

        Panel_Central.setViewportView(PanelTexto);

        javax.swing.GroupLayout Panel_DerechoLayout = new javax.swing.GroupLayout(Panel_Derecho);
        Panel_Derecho.setLayout(Panel_DerechoLayout);
        Panel_DerechoLayout.setHorizontalGroup(
            Panel_DerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Central, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
        );
        Panel_DerechoLayout.setVerticalGroup(
            Panel_DerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Central, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        getContentPane().add(Panel_Derecho, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 467));

        tablatokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema"
            }
        ));
        jScrollPane2.setViewportView(tablatokens);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        BarraMenus.setBackground(new java.awt.Color(102, 102, 102));

        Archivo.setMnemonic('r');
        Archivo.setText("Archivo");

        Nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/New Copy_16px.png"))); // NOI18N
        Nuevo.setText("Nuevo");
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        Archivo.add(Nuevo);

        Abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        Abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/View File_16px.png"))); // NOI18N
        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        Archivo.add(Abrir);

        Guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Save_16px_1.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        Archivo.add(Guardar);

        GuardadComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        GuardadComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Save as_16px.png"))); // NOI18N
        GuardadComo.setText("Guardad Como");
        GuardadComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardadComoActionPerformed(evt);
            }
        });
        Archivo.add(GuardadComo);

        Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Exit Sign_16px.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        Archivo.add(Salir);

        BarraMenus.add(Archivo);

        jMenu2.setMnemonic('O');
        jMenu2.setText("Opciones");

        Compilar2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        Compilar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Code_16px.png"))); // NOI18N
        Compilar2.setText("Compilar");
        Compilar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Compilar2ActionPerformed(evt);
            }
        });
        jMenu2.add(Compilar2);

        jMenuItem1.setText("Gramatica");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        BarraMenus.add(jMenu2);

        setJMenuBar(BarraMenus);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   

    private void tpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpKeyPressed
        
     
    }//GEN-LAST:event_tpKeyPressed

    private void GuardadComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardadComoActionPerformed
       GuardarComo();
    }//GEN-LAST:event_GuardadComoActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        guardarArchivo();
    }//GEN-LAST:event_GuardarActionPerformed

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        abrirArchivo();
    }//GEN-LAST:event_AbrirActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
       Errores.setText("");
        DireccionPath = "";
        tp.setText("inicio_programa ProgramaNuevo\n" +
        "\n" +
        "Ejecutar\n" +
        "BaseDatos(\"\");\n" +
        "Fin_Ejecutar\n" +
        "fin");
    }//GEN-LAST:event_NuevoActionPerformed

    private void Compilar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Compilar2ActionPerformed
        if(listaErrores.isEmpty()){
            for(String error: listaErrores){
                Errores.append("\n"+error);
            }
        }else{
            Errores.append("\n------------Compilado Correctamente----------------");
        }
    }//GEN-LAST:event_Compilar2ActionPerformed

    private void CompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompilarActionPerformed
         try {
            run();
            
            if(listaErrores.isEmpty()){
                for(String error: listaErrores){
                    Errores.append("\n"+error);
                }
            }else{
                Errores.append("\n------------Compilado Correctamente----------------");
            }
            listaErrores.clear();
             lexemes.forEach(x->{System.out.println(x);});
             tablaSimbols();
            
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrimera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfacePrimera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CompilarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        Errores.setText("");
        DireccionPath = "";
        tp.setText("new fly plan Plan_de_Vuelo\n" +
                    "on;\n" +
                    "sync;\n" +
                    "\n" +
                    "off;\n" +
                    "end");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        abrirArchivo();
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarArchivo();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       Gramatica gramar =  new Gramatica(this, rootPaneCheckingEnabled, produtions);
       gramar.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void run() throws IOException, Exception {
        //////GUARDANDO CÓDIGO
        String codigo = tp.getText();
        File archivoDeCodigo = new File("fichero.txt");
        FileWriter escribe = new FileWriter(archivoDeCodigo, false);
        escribe.write(codigo);
        escribe.close();
        ///////COMPILANDO CÓDIGO
        String codigoArray[] = {"fichero.txt"};
        Date hora = new Date();
        long tiempo = hora.getTime();
        
        Cup.main(codigoArray);
        long tiempo2 = new Date().getTime();
        //setTiempoDeEjecucion(tiempo2 - tiempo);
        mostrarResultados();
        guardarArchivos();
    }
    
    private void mostrarResultados() {
        Errores.setText("Compilado en " + " milisegundos.\n");
        if (listaErrores.isEmpty()) {
            Errores.setText("Compilado con éxito!!\n");
           
        } else {
            for (String error : listaErrores) {
                System.err.println(error);
                Errores.setText(Errores.getText() + error + "\n");
            }
        }
        for (String lexema : listaLexemas) {
            System.out.println(lexema);
          
        }
    }
    
    private void mostrarProducciones() {
        Errores.setText("Compilado en " + " milisegundos.\n");
        if (listaErrores.isEmpty()) {
            Errores.setText("Compilado con éxito!!\n");
           
        } else {
            for (String error : listaErrores) {
                System.err.println(error);
                Errores.setText(Errores.getText() + error + "\n");
            }
        }
        for (String lexema : listaLexemas) {
            System.out.println(lexema);
          
        }
    }
    
    private void guardarArchivos() {
        try {
            File archivo = new File("TablaDinamica.txt");
            try (FileWriter escribir = new FileWriter(archivo, false)) {

            }
        } catch (Exception e) {
        }
        String TSEstatica
                = " integer " + " INTEGER "
                + " byte " + " BYTE "
                + " word " + " WORD "
                + " true " + " TRUE "
                + " false " + " FALSE "
                + " longint " + " LONGINT "
                + " shortint " + " SHORTINT "
                + " real " + " REAL "
                + " single " + " SINGLE "
                + " double " + " DOUBLE "
                + " string " + " STRING "
                + " char " + " STRING "
                + " Showmessage " + " MOSTRARMENSAJE "
                + " boolean " + " BOOLEAN "
                + " var " + " VARIABLE "
                + " const " + " CONSTANTE "
                + " ; " + " PUNTOYCOMA "
                + " := " + " ASIGNACION "
                + " <= " + " MAYORIGUALQUE "
                + " >= " + " MENORIGUALQUE "
                + " < " + " MENORQUE "
                + " > " + " MAYORQUE "
                + " = " + " IGUAL "
                + " <> " + " DISTINTODE "
                + " + " + " COMA "
                + " and " + " Y "
                + " or " + " O "
                + " not " + " NEGACION "
                + " if " + " IF "
                + " repeat " + " REPEAT "
                + " until " + " UNTIL "
                + " then " + " THEN "
                + " begin " + " BEGIN "
                + " end " + " END "
                + " else " + " ELSE "
                + " for " + " FOR "
                + " to " + " TO "
                + " do " + " DO "
                + " while " + " WHILE "
                + " Procedure " + " PROCEDURE "
                + " function " + " FUNCTION "
                + "  " + " PARENTESISIZQ "
                + "  " + " PARENTESISDER "
                + " : " + " DOSPUNTOS "
                + " + " + " SUMA "
                + " * " + " MULTIPLICACION "
                + " - " + " RESTA "
                + " / " + " DIVISION "
                + " mod " + " MODULO ";

        try {
            File archivo2 = new File("TablaEstatica.txt");
            try (FileWriter escribir2 = new FileWriter(archivo2, false)) {
                escribir2.write(TSEstatica + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
        }
        try {
            File archivo3 = new File("salida.txt");
            try (FileWriter escribir2 = new FileWriter(archivo3, false)) {
                escribir2.write(Errores.getText());
            }
        } catch (Exception e) {
        }
    }
    
    private void tablaSimbols(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Token");
        dtm.addColumn("Lexema");
        
        lexemes.forEach(x->{
            String[] lex = x.split(",");
            
                    if(lex[1].matches("(\\W)*(ON|OFF|LOOP|SLEEP|FLY|END|UP|DOWN|LEFT|RIGHT|FORWARD|BACKWARD|" +
                                "ROTATE|LEVEL|PLAN|NEW|SPEED|STAND|VAR|START|CAD|SYNC)")){
                        dtm.addRow(new String[]{lex[0],"PALABRA RESERVADA"});
                    }else{
                        dtm.addRow(new String[]{lex[0],lex[1]});
                    }
                    
        });
        
        tablatokens.setModel(dtm);
    }
    
    public static void setError(String error) {
        listaErrores.add(error);
    }
    
    public static void addProduction(String prod){
        produtions.add(prod);
    }
    
    public static void addLexemes(String lex){
        lexemes.add(lex);
      
    }
    
    public static void tres(String a){
        lista3.add(a);
    }
 
    
       
    public void guardarArchivo(){
    if(DireccionPath.equals("")){
        if(tp.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "No hay código que guardar","Aviso",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                JFileChooser guardarA = new JFileChooser();
                guardarA.showSaveDialog(this);
                File guardar = guardarA.getSelectedFile();
                
                if(guardar != null){
                    DireccionPath = guardar + ".fpc";
                    FileWriter  save=new FileWriter(guardar+".fpc");
                    save.write(tp.getText());
                    save.close();
                }
            }catch(IOException ex){
                System.out.println(ex);
            }
        }
        
        
        
    }
    else{
    File fichero = new File(DireccionPath);
        PrintWriter writer;
        try{
            writer = new PrintWriter(fichero);
            writer.print(tp.getText());
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
 
    }
    
    public void GuardarComo(){
        if(tp.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "No hay código que guardar","Aviso",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                JFileChooser guardarA = new JFileChooser();
                guardarA.showSaveDialog(this);
                File guardar = guardarA.getSelectedFile();
                
                if(guardar != null){
                    DireccionPath = guardar + ".fpc";
                    FileWriter  save=new FileWriter(guardar+".fpc");
                    save.write(tp.getText());
                    save.close();
                }
            }catch(IOException ex){
                System.out.println(ex);
            }
        }
    }
    
    public void abrirArchivo(){
        Errores.setText(""); 
        JFileChooser AbrirA = new JFileChooser();
        AbrirA.setFileFilter(filter);
        int opcion = AbrirA.showOpenDialog(this);
        if(opcion==JFileChooser.APPROVE_OPTION){
            tp.setText("");
            tp.setEditable(true);
            DireccionPath = AbrirA.getSelectedFile().getPath();
            
            File archivo = new File(DireccionPath);
            try{
                BufferedReader leer = new BufferedReader(new FileReader(archivo));
                String linea = leer.readLine();
                String total = "";
                while(linea != null){
                    total = total + linea + "\n";
                    linea = leer.readLine();
                }
                tp.setText(total);
                
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    }
 


    
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
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
    
  
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenu Archivo;
    private javax.swing.JMenuBar BarraMenus;
    private javax.swing.JButton Compilar;
    private javax.swing.JMenuItem Compilar2;
    private javax.swing.JTextArea Errores;
    private javax.swing.JMenuItem GuardadComo;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JMenuItem Nuevo;
    private javax.swing.JPanel PanelTexto;
    private javax.swing.JScrollPane Panel_Central;
    private javax.swing.JPanel Panel_Derecho;
    private javax.swing.JPanel Panel_Norte;
    private javax.swing.JPanel Panel_Sur;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablatokens;
    private javax.swing.JTextPane tp;
    // End of variables declaration//GEN-END:variables
}