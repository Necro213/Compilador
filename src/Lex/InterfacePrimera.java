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
import java.io.Reader;
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
import java_cup.*;

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
public class InterfacePrimera extends javax.swing.JFrame {
    private String r = "";
    private DefaultStyledDocument doc;
    private DefaultTableModel dtm;
    private static ArrayList<String> listaErrores;
    private static ArrayList<String> lista3 = new ArrayList<>();
    static ArrayList<String> listaLexemas;
    static String DireccionPath = "";
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos niklaus ","nik");
    int col = 100;
    boolean estado = true;
    /**
     * Creates new form Interface
     */
    public InterfacePrimera() {
        //this.setUndecorated(true);
        this.setBackground(new Color(0,0,254));
        listaLexemas = new ArrayList<>();
        listaErrores = new ArrayList<String>();
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet red = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        final AttributeSet Black = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        final AttributeSet blue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.blue);
        final AttributeSet green = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.green);
        final AttributeSet yellow = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.yellow);
        final AttributeSet orange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.orange);
        final AttributeSet pink = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.pink);

        
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

                        if (text.substring(wordL, wordR).matches("(\\W)*(hacer|inicio_programa|fin)")) {
                            setCharacterAttributes(wordL, wordR - wordL, red, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(si|si_no|fin_si|fin_sino|mientras|fin_mientras|"
                                + "repite|fin_repite|segun|fin_segun)")) {
                            setCharacterAttributes(wordL, wordR - wordL, blue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(caso)")) {
                            setCharacterAttributes(wordL, wordR - wordL, green, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(logico|entero|decimal|cadena|verdadero|falso)")) {
                            setCharacterAttributes(wordL, wordR - wordL, orange, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Coleccion|Atributo|Relacion|JSON|BaseDatos|"
                                + "EmbebidoParcial|EmbebidoCompleto)")) {
                            setCharacterAttributes(wordL, wordR - wordL, yellow, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(begin|end)")) {
                            setCharacterAttributes(wordL, wordR - wordL, pink, false);
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
                String text = tp.getText(0, getLength());
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
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                        while(estado == true){
                            
                            if(col <= 250){
                                col++;
                                setBackground(new Color(0,0,254,col));
                                System.out.println(col);
                                break;
                            }else{
                                estado = false;
                            }
                        }
                        while(estado == false){
                            
                            if(col >= 100){
                                col--;
                                setBackground(new Color(0,0,254,col));
                                System.out.println(col);
                                break;
                            }else{
                                estado = true;
                            }
                     
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(InterfacePrimera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
       // hilo.start();
        TextLineNumber lineNumber = new TextLineNumber(tp, 4);
        PanelTexto.add(lineNumber,BorderLayout.WEST);
        tp.requestFocus();
        
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

        Panel_Central = new javax.swing.JScrollPane();
        PanelTexto = new javax.swing.JPanel();
        tp = new javax.swing.JTextPane(doc);
        Panel_Izquierdo = new javax.swing.JPanel();
        Compilar = new javax.swing.JButton();
        Panel_Norte = new javax.swing.JPanel();
        Panel_Sur = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Errores = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Panel_Derecho = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        BarraMenus = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        Nuevo = new javax.swing.JMenuItem();
        Abrir = new javax.swing.JMenuItem();
        Guardar = new javax.swing.JMenuItem();
        GuardadComo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelTexto.setLayout(new java.awt.BorderLayout());

        tp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tpKeyPressed(evt);
            }
        });
        PanelTexto.add(tp, java.awt.BorderLayout.CENTER);

        Panel_Central.setViewportView(PanelTexto);

        getContentPane().add(Panel_Central, java.awt.BorderLayout.CENTER);

        Compilar.setText("Compilar");
        Compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompilarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_IzquierdoLayout = new javax.swing.GroupLayout(Panel_Izquierdo);
        Panel_Izquierdo.setLayout(Panel_IzquierdoLayout);
        Panel_IzquierdoLayout.setHorizontalGroup(
            Panel_IzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
            .addGroup(Panel_IzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Panel_IzquierdoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Compilar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        Panel_IzquierdoLayout.setVerticalGroup(
            Panel_IzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
            .addGroup(Panel_IzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Panel_IzquierdoLayout.createSequentialGroup()
                    .addGap(0, 96, Short.MAX_VALUE)
                    .addComponent(Compilar)
                    .addGap(0, 96, Short.MAX_VALUE)))
        );

        getContentPane().add(Panel_Izquierdo, java.awt.BorderLayout.WEST);

        Panel_Norte.setPreferredSize(new java.awt.Dimension(589, 50));

        javax.swing.GroupLayout Panel_NorteLayout = new javax.swing.GroupLayout(Panel_Norte);
        Panel_Norte.setLayout(Panel_NorteLayout);
        Panel_NorteLayout.setHorizontalGroup(
            Panel_NorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );
        Panel_NorteLayout.setVerticalGroup(
            Panel_NorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(Panel_Norte, java.awt.BorderLayout.NORTH);

        Errores.setColumns(20);
        Errores.setRows(5);
        jScrollPane1.setViewportView(Errores);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");
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
                .addGap(59, 59, 59)
                .addComponent(jButton1)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_SurLayout = new javax.swing.GroupLayout(Panel_Sur);
        Panel_Sur.setLayout(Panel_SurLayout);
        Panel_SurLayout.setHorizontalGroup(
            Panel_SurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SurLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_SurLayout.setVerticalGroup(
            Panel_SurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_SurLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(Panel_Sur, java.awt.BorderLayout.PAGE_END);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Palabra", "Componente"
            }
        ));
        jScrollPane2.setViewportView(Tabla);

        javax.swing.GroupLayout Panel_DerechoLayout = new javax.swing.GroupLayout(Panel_Derecho);
        Panel_Derecho.setLayout(Panel_DerechoLayout);
        Panel_DerechoLayout.setHorizontalGroup(
            Panel_DerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_DerechoLayout.setVerticalGroup(
            Panel_DerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        getContentPane().add(Panel_Derecho, java.awt.BorderLayout.LINE_END);

        Archivo.setText("Archivo");

        Nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        Nuevo.setText("Nuevo");
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        Archivo.add(Nuevo);

        Abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        Archivo.add(Abrir);

        Guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        Archivo.add(Guardar);

        GuardadComo.setText("Guardad Como");
        GuardadComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardadComoActionPerformed(evt);
            }
        });
        Archivo.add(GuardadComo);

        BarraMenus.add(Archivo);

        jMenu2.setText("Edit");
        BarraMenus.add(jMenu2);

        setJMenuBar(BarraMenus);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompilarActionPerformed
        try {
            LimpiarTabla();
            //probarlexerfile();
            run();
            
            if(listaErrores.isEmpty()){
                for(String error: listaErrores){
                    Errores.append("\n"+error);
                }
            }else{
                Errores.append("\n------------Compilado Correctamente----------------");
            }
            listaErrores.clear();
            
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrimera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfacePrimera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CompilarActionPerformed
   

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
        tp.setText("");
        LimpiarTabla();
    }//GEN-LAST:event_NuevoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LimpiarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public static void setError(String error) {
        listaErrores.add(error);
        System.out.println(error);
    }
    
    public static void tres(String a){
        lista3.add(a);
    }
    
    
   /*public void probarlexerfile() throws FileNotFoundException, IOException{
   
       Errores.setText("-------------Inicio de Compilacion-----------------");
       DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        File fichero = new File("fichero.txt");
        PrintWriter writer;
        try{
            writer = new PrintWriter(fichero);
            writer.print(tp.getText());
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("Fichero no encontrador");
            return;
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lex.Lexer lexer = new Lex.Lexer(reader);
        while(true){
            Token token = lexer.yylex();
            
            if(token == null){
                return;
            }
            
            switch(token){
                case ID: case NUM_ENTERO: case NUM_DECIMAL:
                    modelo.addRow(new Object[]{token,lexer.Lexeme}); 
                    break;
                case SIGNO_DE_ASIGNACION:
                    modelo.addRow(new Object[]{token,"="});
                    break;
                case SUMA:
                    modelo.addRow(new Object[]{"OPERADOR_ARITMETICO","+"});
                    break;
                case RESTA:
                    modelo.addRow(new Object[]{"OPERADOR_ARITMETICO","-"});
                    break;
                case MULTIPLICACION:
                    modelo.addRow(new Object[]{"OPERADOR_ARITMETICO","*"});
                    break;
                case DIVISION:
                    modelo.addRow(new Object[]{"OPERADOR_ARITMETICO","/"});
                    break;
                case MAYOR_QUE:
                    modelo.addRow(new Object[]{"OPERADOR_RELACIONAL",">"});
                    break;
                case MENOR_QUE:
                    modelo.addRow(new Object[]{"OPERADOR_RELACIONAL","<"});
                    break;
                case MAYOR_IGUAL:
                    modelo.addRow(new Object[]{"OPERADOR_RELACIONAL",">="});
                    break;
                case MENOR_IGUAL:
                    modelo.addRow(new Object[]{"OPERADOR_RELACIONAL","<="});
                    break;
                case AND:
                    modelo.addRow(new Object[]{"OPERADOR_LOGICO","&&"});
                    break;
                case OR:
                    modelo.addRow(new Object[]{"OPERADOR_LOGICO","||"});
                    break;
                case IGUAL_A:
                    modelo.addRow(new Object[]{"OPERADOR_LOGICO","=="});
                    break;
                case DIFERENTE_DE:
                    modelo.addRow(new Object[]{"OPERADOR_LOGICO","!="});
                    break;
                case PUNTO:
                    modelo.addRow(new Object[]{"SIGNO_PUNTUACION","."});
                    break;
                case COMA:
                    modelo.addRow(new Object[]{"SIGNO_PUNTUACION",","});
                    break;
                case PUNTO_Y_COMA:
                    modelo.addRow(new Object[]{"SIGNO_PUNTUACION",";"});
                    break;
                case DOS_PUNTOS:
                    modelo.addRow(new Object[]{"SIGNO_PUNTUACION",":"});
                    break;
                case PARENTESIS_ABRIR:
                    modelo.addRow(new Object[]{"SIGNO_AGRUPACION","("});
                    break;
                case PARENTESIS_CERRAR:
                    modelo.addRow(new Object[]{"SIGNO_AGRUPACION",")"});
                    break;
                case CORCHETE_ABRIR:
                    modelo.addRow(new Object[]{"SIGNO_AGRUPACION","["});
                    break;
                case CORCHETE_CERRAR:
                    modelo.addRow(new Object[]{"SIGNO_AGRUPACION","]"});
                    break;
                case LLAVE_ABRIR:
                    modelo.addRow(new Object[]{"SIGNO_AGRUPACION","{"});
                    break;
                case LLAVE_CERRAR:
                    modelo.addRow(new Object[]{"SIGNO_AGRUPACION","}"});
                    break;
                case IMPORTAR: case FIN_IMPORTAR: case SI: case FIN_SI: case SINO: case FIN_SINO:
                case REPITE: case FIN_REPITE: case VERDADERO: case FALSO: case MIENTRAS: case FIN_MIENTRAS:
                case SEGUN: case FIN_SEGUN: case LOGICO: case CADENA: case CASO: case HACER: case COLECCION:
                case ATRIBUTO: case RELACION: case JSON: case INICIO_PROGRAMA: case FIN: case BASEDATOS:
                case EMBEBIDOPARCIAL: case ENTERO: case EMBEBIDOCOMPLETO: case DECIMAL:
                    modelo.addRow(new Object[]{"PALABRA_RESERVADA",token});
                    break;
                case PA:
                    //modelo.addRow(new Object[]{"\nPALABRA_NO_valida",lexer.yytext()});
                    Errores.append("\nCodigo de Error 02:Error Lexico en la Linea: "+lexer.Line()+
                            " La Palabra: ["+lexer.yytext()+"] No es Valida Para el Lenguaje");
                    break;
                case SIM:
                    modelo.addRow(new Object[]{"SIMBOLO",lexer.yytext()});
                    break;
                case CAD:
                    modelo.addRow(new Object[]{"CADENA_TEXTO",lexer.yytext()});
                    break;
                default: 
                    //modelo.addRow(new Object[]{"Error Simbolo desconocido",lexer.Lexeme});
                    Errores.append("\nCodigo de Error 01:\nError Lexico en la Linea: "+lexer.Line()+
                            " el Simbolo ["+lexer.yytext()+"] no se Reconoce");
                    break;
            }
        }
        
        
    }
   */
    public void guardarArchivo(){
    if(DireccionPath.equals("")){
        if(tp.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "No hay codigo que guardar","Aviso",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                JFileChooser guardarA = new JFileChooser();
                guardarA.showSaveDialog(this);
                File guardar = guardarA.getSelectedFile();
                
                if(guardar != null){
                    DireccionPath = guardar + ".jcc";
                    FileWriter  save=new FileWriter(guardar+".jcc");
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
            javax.swing.JOptionPane.showMessageDialog(this, "No hay codigo que guardar","Aviso",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                JFileChooser guardarA = new JFileChooser();
                guardarA.showSaveDialog(this);
                File guardar = guardarA.getSelectedFile();
                
                if(guardar != null){
                    DireccionPath = guardar + ".jcc";
                    FileWriter  save=new FileWriter(guardar+".jcc");
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
        LimpiarTabla();
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
    
    public void LimpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) Tabla.getModel();
        System.out.println(dtm.getRowCount());
        int cont =dtm.getRowCount()-1;
        while(cont >= 0){
            dtm.removeRow(cont);
            cont--;
        }
    }

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
            java.util.logging.Logger.getLogger(InterfacePrimera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfacePrimera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfacePrimera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfacePrimera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfacePrimera().setVisible(true);
            }
        });
    }
    
  
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenu Archivo;
    private javax.swing.JMenuBar BarraMenus;
    private javax.swing.JButton Compilar;
    private javax.swing.JTextArea Errores;
    private javax.swing.JMenuItem GuardadComo;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JMenuItem Nuevo;
    private javax.swing.JPanel PanelTexto;
    private javax.swing.JScrollPane Panel_Central;
    private javax.swing.JPanel Panel_Derecho;
    private javax.swing.JPanel Panel_Izquierdo;
    private javax.swing.JPanel Panel_Norte;
    private javax.swing.JPanel Panel_Sur;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane tp;
    // End of variables declaration//GEN-END:variables
}