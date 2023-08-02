//-------------------------------------------------------------------------------------
package xpu.sw.tools.sdk.gui;
//-------------------------------------------------------------------------------------
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.reflect.*;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

import xpu.sw.tools.sdk.*;
import xpu.sw.tools.sdk.common.context.*;
import xpu.sw.tools.sdk.common.project.*;

import xpu.sw.tools.sdk.gui.components.*;
import xpu.sw.tools.sdk.gui.services.*;

//-------------------------------------------------------------------------------------
/**
 *
 * @author marius
 */
public class Gui extends javax.swing.JFrame {
    private Project activeProject;
    private Context context;
    private Logger log;

    private Components components;
    private Services services;

//-------------------------------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jSplitPane5 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        statusBarPanel = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();

        jMenuItem1.setText("jMenuItem1");
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        jToolBar1.setBorder(null);
        jToolBar1.setAlignmentX(0.0F);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setEnabled(false);
        jToolBar1.setFocusable(false);
        jToolBar1.setMinimumSize(null);
        jToolBar1.setPreferredSize(null);
        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jSplitPane1.setDividerLocation(420);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setMaximumSize(null);
        jSplitPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane1PropertyChange(evt);
            }
        });

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setDividerSize(4);
        jSplitPane2.setMaximumSize(null);
        jSplitPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane2PropertyChange(evt);
            }
        });

        jSplitPane3.setDividerLocation(250);
        jSplitPane3.setDividerSize(4);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setResizeWeight(1.0);
        jSplitPane3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane3PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jSplitPane3.setRightComponent(jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jSplitPane3.setLeftComponent(jPanel6);

        jSplitPane2.setLeftComponent(jSplitPane3);

        jSplitPane5.setDividerLocation(700);
        jSplitPane5.setDividerSize(4);
        jSplitPane5.setResizeWeight(1.0);
        jSplitPane5.setOpaque(false);
        jSplitPane5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane5PropertyChange(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane5.setLeftComponent(jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane5.setRightComponent(jPanel2);

        jSplitPane2.setRightComponent(jSplitPane5);

        jSplitPane1.setTopComponent(jSplitPane2);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane1.setRightComponent(jPanel4);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        statusBarPanel.add(jProgressBar2);

        getContentPane().add(statusBarPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        saveLocations();
    }//GEN-LAST:event_formComponentResized

    private void jSplitPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane1PropertyChange
        // TODO add your handling code here:
        saveLocations();
    }//GEN-LAST:event_jSplitPane1PropertyChange

    private void jSplitPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane2PropertyChange
        // TODO add your handling code here:
        saveLocations();
    }//GEN-LAST:event_jSplitPane2PropertyChange

    private void jSplitPane3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane3PropertyChange
        // TODO add your handling code here:
        saveLocations();
    }//GEN-LAST:event_jSplitPane3PropertyChange

    private void jSplitPane5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane5PropertyChange
        // TODO add your handling code here:
        saveLocations();
    }//GEN-LAST:event_jSplitPane5PropertyChange

//-------------------------------------------------------------------------------------
    public Gui(Context _context) {
        
        context = _context;
        log = _context.getLog();

        Gui _gui = this;

        services = new Services(_gui, context);

        int _x = context.getSdkConfig().getInt("gui.size.x", -1);
        int _y = context.getSdkConfig().getInt("gui.size.y", -1);
        log.debug("Loading x=" + _x + ", _y=" + _y);
        if((_x != -1) && (_y != -1)){
//            setMinimumSize(new Dimension(_x, _y));
            setPreferredSize(new Dimension(_x, _y));
        }

        setup();
        initComponents();
        components = new Components(_gui, context);

        double _jSplitPane1Location = context.getSdkConfig().getDouble("gui.splitPane1", Double.NaN);
        if(_jSplitPane1Location != Double.NaN){
            jSplitPane1.setDividerLocation(_jSplitPane1Location);
        }
        double _jSplitPane2Location = context.getSdkConfig().getDouble("gui.splitPane2", Double.NaN);
        if(_jSplitPane2Location != Double.NaN){
            jSplitPane2.setDividerLocation(_jSplitPane2Location);
        }

        double _jSplitPane3Location = context.getSdkConfig().getDouble("gui.splitPane3", Double.NaN);
        if(_jSplitPane3Location != Double.NaN){
            jSplitPane3.setDividerLocation(_jSplitPane3Location);
        }

        double _jSplitPane5Location = context.getSdkConfig().getDouble("gui.splitPane5", Double.NaN);
        if(_jSplitPane5Location != Double.NaN){
            jSplitPane5.setDividerLocation(_jSplitPane5Location);
        }

        setLocationRelativeTo(null);                
        
        /* Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
            }
        });
        */
//        services.init();
        components.afterInit();
        context.setState(Context.CONTEXT_STATE_RUNNING);
        setPreferredSize(new Dimension(_x, _y));
        setVisible(true);
    }

//-------------------------------------------------------------------------------------
    private void setup(){
        //loading an image from a file
        final Toolkit _defaultToolkit = Toolkit.getDefaultToolkit();
        ImageIcon ii = getServices().getUtils().getIconFromResources("logo.png");
        //final URL _imageResource = this.getClass().getClassLoader().getResource("logo.png");
        //final Image _image = _defaultToolkit.getImage(_imageResource);

        //set icon for windows os (and other systems which do support this method)
        setIconImage(ii.getImage());
        setTitle("XPU SDK " + context.getVersion());

        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "XPU SDK");
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
        UIManager.getDefaults().put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));


        Insets _insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        _insets.top = -1;
        UIManager.put("TabbedPane.contentBorderInsets", _insets);
        setLayout(new BorderLayout(0, 0));
        setResizable(true);
        getServices().getUtils().loadLF(this);
    }

//-------------------------------------------------------------------------------------
    public Components getMyComponents(){
        return components;
    }

//-------------------------------------------------------------------------------------
    public Services getServices(){
        return services;
    }

//-------------------------------------------------------------------------------------
    public JPanel getHierarchy(){
        return jPanel6;
    }

//-------------------------------------------------------------------------------------
    public JPanel getFlow(){
        return jPanel5;
    }

//-------------------------------------------------------------------------------------
    public JPanel getEditor(){
        return jPanel1;
    }

//-------------------------------------------------------------------------------------
    public JPanel getDebugger(){
        return jPanel2;
    }

//-------------------------------------------------------------------------------------
    public JPanel getMessager(){
        return jPanel3;
    }

//-------------------------------------------------------------------------------------
    public JToolBar getToolbar(){
        return jToolBar1;
    }

//-------------------------------------------------------------------------------------
    public JPanel getTerminal(){
        return jPanel4;
    }

//-------------------------------------------------------------------------------------
    public JSplitPane getDebugSplitter(){
        return jSplitPane5;
    }

//-------------------------------------------------------------------------------------
    private void saveLocations(){
        if(context.getState() != Context.CONTEXT_STATE_INIT){
            org.apache.commons.configuration2.Configuration _config = context.getSdkConfig();
            Dimension _currentDimension = getSize();
            _config.setProperty("gui.size.x", (int)_currentDimension.getWidth());
            _config.setProperty("gui.size.y", (int)_currentDimension.getHeight());
//            log.debug("gui.size.x=" + _currentDimension.getWidth() + ", gui.size.y=" + _currentDimension.getHeight());

            double _jSplitPane1Location = ((double)jSplitPane2.getHeight()) / (jSplitPane2.getHeight() + jPanel4.getHeight());
//            log.debug("_jSplitPane1Location="+_jSplitPane1Location);
            _config.setProperty("gui.splitPane1", _jSplitPane1Location);
            double _jSplitPane2Location = ((double)jSplitPane3.getWidth()) / (jSplitPane3.getWidth() + jSplitPane5.getWidth());
//            log.debug("_jSplitPane2Location="+_jSplitPane2Location);
            _config.setProperty("gui.splitPane2", _jSplitPane2Location);
            double _jSplitPane3Location = ((double)jPanel6.getHeight()) / (jPanel5.getHeight() + jPanel6.getHeight());
//            log.debug("_jSplitPane3Location="+_jSplitPane3Location);
            _config.setProperty("gui.splitPane3", _jSplitPane3Location);
            if(context.getDebugStatus() == Context.DEBUG_STATUS_ON){
                double _jSplitPane5Location = ((double)jPanel1.getWidth()) / (jPanel1.getWidth() + jPanel2.getWidth());
    //            log.debug("_jSplitPane5Location="+_jSplitPane5Location);
                _config.setProperty("gui.splitPane5", _jSplitPane5Location);
            }
        }
    }


//-------------------------------------------------------------------------------------

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel statusBarPanel;
    // End of variables declaration//GEN-END:variables

//-------------------------------------------------------------------------------------
    public void setActiveProject(Project _activeProject){
        if(activeProject != _activeProject){
            activeProject = _activeProject;
            log.debug("Select active project: " + _activeProject.toString());
            context.getSdkConfig().setProperty("pathToActiveProject", _activeProject.getPathToConfigFile());
            getMyComponents().getEditor().getActiveEditor().setActiveProject(_activeProject);
            getMyComponents().getDebugger().setActiveProject(_activeProject);
        }
    }

//-------------------------------------------------------------------------------------
    public void refresh() {
        getMyComponents().getMenu().refresh();
        getMyComponents().getToolbar().refresh();
        repaint();
    }

//-------------------------------------------------------------------------------------
    public void save() {
        getServices().save();
    }


//-------------------------------------------------------------------------------------
}
//-------------------------------------------------------------------------------------
