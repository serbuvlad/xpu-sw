//-------------------------------------------------------------------------------------
package xpu.sw.tools.sdk.gui.components.toolbar;

//-------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;

import org.apache.commons.configuration2.*;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.*;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.layout.*;
import org.apache.logging.log4j.core.appender.rolling.*;

import xpu.sw.tools.sdk.common.context.*;
import xpu.sw.tools.sdk.common.io.targetmanager.*;

import xpu.sw.tools.sdk.gui.*;
import xpu.sw.tools.sdk.gui.components.common.buttons.*;
//import xpu.sw.tools.sdk.debug.debugger.core.*;

//-------------------------------------------------------------------------------------
public class Toolbar extends javax.swing.JPanel implements TargetStatusListener {
    private Gui gui;
    private Context context;
    private org.apache.logging.log4j.Logger log;

    private org.apache.commons.configuration2.Configuration sdkConfig;
    private JPopupMenu targetMenu;

    private ImageIcon connectingIcon;
    private ImageIcon connectedIcon;
    private ImageIcon failedIcon;
    private TargetManager targetManager;

//-------------------------------------------------------------------------------------
    public Toolbar(Gui _gui, Context _context) {
        gui = _gui;
        context = _context;
        log = _context.getLog();
        sdkConfig = context.getSdkConfig();
    }

//-------------------------------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setAutoscrolls(true);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1200, 35));
        setLayout(new java.awt.BorderLayout());

        jPanel7.setAlignmentX(0.0F);
        jPanel7.setAlignmentY(0.0F);
        java.awt.FlowLayout flowLayout5 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout5.setAlignOnBaseline(true);
        jPanel7.setLayout(flowLayout5);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel7.add(jComboBox1);

        jButton4.setText("Asm");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);

        jButton5.setText("Run");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5);

        jToggleButton1.setText("Debug");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton1);

        add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        java.awt.FlowLayout flowLayout6 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout6.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout6);

        jButton6.setText(">");
        jButton6.setAlignmentY(0.0F);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.setIconTextGap(3);
        jButton6.setMaximumSize(null);
        jButton6.setMinimumSize(null);
        jButton6.setPreferredSize(null);
        jPanel4.add(jButton6);

        add(jPanel4, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if(jToggleButton1.isSelected()){
            gui.getMyComponents().getMenu().getMenuHandlers().debugEnter();
        } else {
            gui.getMyComponents().getMenu().getMenuHandlers().debugExit();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        gui.getMyComponents().getMenu().getMenuHandlers().remoteRun();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        gui.getMyComponents().getMenu().getMenuHandlers().asm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if(context.getState() == Context.CONTEXT_STATE_RUNNING){
            String _level = (String)jComboBox1.getSelectedItem();
            gui.getMyComponents().getMenu().getMenuHandlers().switchToProfile(_level);                
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables


//-------------------------------------------------------------------------------------
    private void init(){


    }

//-------------------------------------------------------------------------------------
    private void refreshTargetMenu(){
        java.util.List<TargetConnection> _targetConnections = targetManager.getTargetConnections();
        for(int i = 0; i < _targetConnections.size(); i++){
            TargetConnection _targetConnection = _targetConnections.get(i);
//            log.debug("refreshTargetMenu:"+i+"..."+_targetConnection);
            boolean _isSelected = _targetConnection.isSelected();
            JCheckBoxMenuItem _menuItem = (JCheckBoxMenuItem)targetMenu.getComponent(i);
            _menuItem.setSelected(_isSelected);

/*            
            if(_isSelected){
                refreshTargetButton(_targetConnection);
            } 
            targetMenu.add(_menuItem);
*/            
        }
    }

//-------------------------------------------------------------------------------------
    public void changeCurrentTargetConnection(TargetConnection _targetConnection){
        targetManager.changeCurrentTargetConnection(_targetConnection);
    }

//-------------------------------------------------------------------------------------
    public void targetStatusChanged(TargetConnection _targetConnection){
        refreshTargetMenu();
        refreshTargetButton(_targetConnection);
    }

//-------------------------------------------------------------------------------------
    public void refreshTargetButton(TargetConnection _targetConnection){
        int _status = _targetConnection.getStatus();
        switch(_status){
            case TargetConnection.STATUS_INIT: {
                jButton6.setIcon(connectingIcon);
//                jButton6.setText("Connecting... " + _targetConnection.getDescriptor());
                jButton6.setText(_targetConnection.getDescriptor());
                break;
            }
            case TargetConnection.STATUS_CONNECTING: {
                jButton6.setIcon(connectingIcon);
//                jButton6.setText("Connecting... " + _targetConnection.getDescriptor());
                jButton6.setText(_targetConnection.getDescriptor());
                break;
            }
            case TargetConnection.STATUS_CONNECTED: {
                jButton6.setIcon(connectedIcon);
//                jButton6.setText("Connected: " + _targetConnection.getDescriptor());
                jButton6.setText(_targetConnection.getDescriptor());
                break;
            }
            case TargetConnection.STATUS_FAILED: {
                jButton6.setIcon(failedIcon);
//                jButton6.setText("Failed:" + _targetConnection.getDescriptor());
                jButton6.setText(_targetConnection.getDescriptor());
                break;
            }
            default: {
                log.error("Unknown TargetConnection status: " + _status);
            }
        }
    }

//-------------------------------------------------------------------------------------
    public void setDebugStatus(boolean _selected) {
        jToggleButton1.setSelected(_selected);
    }

//-------------------------------------------------------------------------------------
    private void showTargetMenu(){
//        System.out.println("showTargetMenu..." + targetMenu.getComponentCount());
        targetMenu.show(jButton6, jButton6.getBounds().x, jButton6.getBounds().y + jButton6.getBounds().height);
//        targetMenu.show(jButton6, 0, 0);
    }

//-------------------------------------------------------------------------------------
    public void afterInit(){
        initComponents();
        JToolBar _jToolbar = gui.getToolbar();
        _jToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        _jToolbar.add(this);

        jComboBox1.addItem(Context.PROFILE_APP_LEVEL);
        jComboBox1.addItem(Context.PROFILE_HIGH_LEVEL);
        jComboBox1.addItem(Context.PROFILE_LOW_LEVEL);

        targetMenu = new JPopupMenu();
        targetManager = gui.getServices().getTargetManager();

        try {
            connectingIcon = gui.getServices().getUtils().getIconFromResources("toolbar/icons/connecting.png");
            connectedIcon = gui.getServices().getUtils().getIconFromResources("toolbar/icons/connected.png");
            failedIcon = gui.getServices().getUtils().getIconFromResources("toolbar/icons/failed.png");
//            new ImageIcon(ImageIO.read(getClass().getResource("/toolbar/icons/connecting.png")));
//            connectedIcon = new ImageIcon(ImageIO.read(getClass().getResource("/toolbar/icons/connected.png")));
//            failedIcon = new ImageIcon(ImageIO.read(getClass().getResource("/toolbar/icons/failed.png")));
        } catch (Exception _e) {
            log.error("Cannot find icons for toolbar: " + _e.getMessage());
        }

        jButton6.setIcon(connectingIcon);
        jButton6.setText(targetManager.getTargetConnection().getDescriptor());
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTargetMenu();
            }
        });

//        targetMenu.removeAll();
        java.util.List<TargetConnection> _targetConnections = targetManager.getTargetConnections();
        for(int i = 0; i < _targetConnections.size(); i++){
            TargetConnection _targetConnection = _targetConnections.get(i);
//            log.debug("refreshTargetMenu:"+i+"..."+_targetConnection);
            boolean _isSelected = _targetConnection.isSelected();
            JCheckBoxMenuItem _menuItem = new JCheckBoxMenuItem(_targetConnection.getDescriptor(), _isSelected);
            _menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changeCurrentTargetConnection(_targetConnection);
                }
            });
            targetMenu.add(_menuItem);
        }
        targetMenu.addSeparator();
        JMenuItem _menuItem = new JMenuItem("Manage targets...");
        _menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.getMyComponents().getMenu().getMenuHandlers().preferences("Targets");
            }
        });
        targetMenu.add(_menuItem);

        refresh();
        setVisible(true);  
        targetManager.addStatusListener(this);
    }

//-------------------------------------------------------------------------------------
    public void refresh() {
        String _profile = context.getProfile();
        jButton4.setVisible(_profile.equals(Context.PROFILE_LOW_LEVEL));
//        jComboBox1.getModel().setSelectedItem(_profile);
        jComboBox1.setSelectedItem(_profile);
        jToggleButton1.setSelected(context.getDebugStatus() == Context.DEBUG_STATUS_ON);
    }

//-------------------------------------------------------------------------------------
}
//-------------------------------------------------------------------------------------
