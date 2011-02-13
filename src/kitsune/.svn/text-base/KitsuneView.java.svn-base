package kitsune;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.awt.Graphics;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.imageio.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Math;

import java.util.Iterator;



/**
 * The application's main frame.
 */
public class KitsuneView extends FrameView {

    public KitsuneView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        //this timer will update the inspector pane runtime line when the sim is running
        inspectorUpdaterTimer = new Timer(100, new ActionListener() { //we run it every 100 ms
            public void actionPerformed(ActionEvent e) {
                //we get the current time
                Date now = new Date();
                //the runtime is the past runtime added to the time since the runtime was last updated
                simRunTime+=now.getTime()-lastSimCheck;
                //we checked the sim
                lastSimCheck=now.getTime();
                updateInspector();
                inspectorUpdateSimRuntime();
            }
        });

        //this timer will update the simulation visualization pane when the sim is running
        simUpdaterTimer = new Timer(100, new ActionListener() { //we run it every 100 ms
            public void actionPerformed(ActionEvent e) {
                simulationUpdate();
            }
        });

        
        //we instantiate a new environment
        this.loadedEnvironment= new Environment();

        
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = KitsuneApp.getApplication().getMainFrame();
            aboutBox = new KitsuneAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        KitsuneApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        wallButton = new javax.swing.JButton();
        flammabilityButton = new javax.swing.JButton();
        agentButton = new javax.swing.JButton();
        doorButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        stairButton = new javax.swing.JButton();
        labelButton = new javax.swing.JButton();
        menuSeparator = new javax.swing.JSeparator();
        buttonSeparator = new javax.swing.JSeparator();
        buttonSeparator2 = new javax.swing.JSeparator();
        startButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        inspectorTable = new javax.swing.JTable();
        editorPane = new javax.swing.JTabbedPane();
        addFloorButton = new javax.swing.JButton();
        removeFloorButton = new javax.swing.JButton();
        labelRemoveButton = new javax.swing.JButton();
        agentRemoveButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();

        mainPanel.setMaximumSize(new java.awt.Dimension(1000, 700));
        mainPanel.setMinimumSize(new java.awt.Dimension(1000, 700));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(1000, 700));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kitsune.KitsuneApp.class).getContext().getResourceMap(KitsuneView.class);
        wallButton.setIcon(resourceMap.getIcon("wallButton.icon")); // NOI18N
        wallButton.setText(resourceMap.getString("wallButton.text")); // NOI18N
        wallButton.setToolTipText(resourceMap.getString("wallButton.toolTipText")); // NOI18N
        wallButton.setName("wallButton"); // NOI18N
        wallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wallButtonActionPerformed(evt);
            }
        });

        flammabilityButton.setIcon(resourceMap.getIcon("flammabilityButton.icon")); // NOI18N
        flammabilityButton.setToolTipText(resourceMap.getString("flammabilityButton.toolTipText")); // NOI18N
        flammabilityButton.setName("flammabilityButton"); // NOI18N
        flammabilityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flammabilityButtonActionPerformed(evt);
            }
        });

        agentButton.setIcon(resourceMap.getIcon("agentButton.icon")); // NOI18N
        agentButton.setToolTipText(resourceMap.getString("agentButton.toolTipText")); // NOI18N
        agentButton.setName("agentButton"); // NOI18N
        agentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agentButtonActionPerformed(evt);
            }
        });

        doorButton.setIcon(resourceMap.getIcon("doorButton.icon")); // NOI18N
        doorButton.setToolTipText(resourceMap.getString("doorButton.toolTipText")); // NOI18N
        doorButton.setName("doorButton"); // NOI18N
        doorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doorButtonActionPerformed(evt);
            }
        });

        exitButton.setIcon(resourceMap.getIcon("exitButton.icon")); // NOI18N
        exitButton.setToolTipText(resourceMap.getString("exitButton.toolTipText")); // NOI18N
        exitButton.setName("exitButton"); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        stairButton.setIcon(resourceMap.getIcon("stairButton.icon")); // NOI18N
        stairButton.setToolTipText(resourceMap.getString("stairButton.toolTipText")); // NOI18N
        stairButton.setName("stairButton"); // NOI18N
        stairButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stairButtonActionPerformed(evt);
            }
        });

        labelButton.setIcon(resourceMap.getIcon("labelButton.icon")); // NOI18N
        labelButton.setToolTipText(resourceMap.getString("labelButton.toolTipText")); // NOI18N
        labelButton.setName("labelButton"); // NOI18N
        labelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelButtonActionPerformed(evt);
            }
        });

        menuSeparator.setName("menuSeparator"); // NOI18N

        buttonSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);
        buttonSeparator.setName("buttonSeparator"); // NOI18N

        buttonSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        buttonSeparator2.setName("buttonSeparator2"); // NOI18N

        startButton.setIcon(resourceMap.getIcon("startButton.icon")); // NOI18N
        startButton.setText(resourceMap.getString("startButton.text")); // NOI18N
        startButton.setToolTipText(resourceMap.getString("startButton.toolTipText")); // NOI18N
        startButton.setName("startButton"); // NOI18N
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        inspectorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Number of exits", new Integer(0)},
                {"Total number of doors", new Integer(0)},
                {"", null},
                {"Total runtime (seconds)", new Integer(0)},
                {"Total number of agents", new Integer(0)},
                {"Total number of agents that escaped", new Integer(0)},
                {"Total number of agents that are exiting", new Integer(0)},
                {"Total number of agents that are stuck/dead", new Integer(0)},
                {"", null},
                {"Average time for exit", new Integer(0)},
                {"Time of first exit", new Integer(0)},
                {"Time of last exit", new Integer(0)},
                {"Success ratio", null}
            },
            new String [] {
                "Item", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inspectorTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        inspectorTable.setDragEnabled(true);
        inspectorTable.setMinimumSize(new java.awt.Dimension(150, 208));
        inspectorTable.setName("inspectorTable"); // NOI18N
        inspectorTable.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(inspectorTable);
        inspectorTable.getColumnModel().getColumn(0).setResizable(false);
        inspectorTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("inspectorTable.columnModel.title0")); // NOI18N
        inspectorTable.getColumnModel().getColumn(1).setResizable(false);
        inspectorTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("inspectorTable.columnModel.title1")); // NOI18N

        editorPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        editorPane.setName("editorPane"); // NOI18N
        editorPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editorPaneMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editorPaneMouseExited(evt);
            }
        });
        editorPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                editorPaneMouseMoved(evt);
            }
        });

        addFloorButton.setIcon(resourceMap.getIcon("addFloorButton.icon")); // NOI18N
        addFloorButton.setText(resourceMap.getString("addFloorButton.text")); // NOI18N
        addFloorButton.setName("addFloorButton"); // NOI18N
        addFloorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFloorButtonActionPerformed(evt);
            }
        });

        removeFloorButton.setIcon(resourceMap.getIcon("removeFloorButton.icon")); // NOI18N
        removeFloorButton.setName("removeFloorButton"); // NOI18N
        removeFloorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFloorButtonActionPerformed(evt);
            }
        });

        labelRemoveButton.setIcon(resourceMap.getIcon("labelRemoveButton.icon")); // NOI18N
        labelRemoveButton.setText(resourceMap.getString("text")); // NOI18N
        labelRemoveButton.setName(""); // NOI18N
        labelRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelRemoveButtonActionPerformed(evt);
            }
        });

        agentRemoveButton.setIcon(resourceMap.getIcon("agentRemoveButton.icon")); // NOI18N
        agentRemoveButton.setText(resourceMap.getString("agentRemoveButton.text")); // NOI18N
        agentRemoveButton.setName("agentRemoveButton"); // NOI18N
        agentRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agentRemoveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(wallButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flammabilityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(doorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stairButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFloorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeFloorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(editorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                    .addComponent(menuSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wallButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(flammabilityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(doorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stairButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(buttonSeparator2)
                    .addComponent(removeFloorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addFloorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSeparator)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agentRemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                    .addComponent(editorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setIcon(resourceMap.getIcon("newMenuItem.icon")); // NOI18N
        newMenuItem.setMnemonic('N');
        newMenuItem.setText(resourceMap.getString("newMenuItem.text")); // NOI18N
        newMenuItem.setToolTipText(resourceMap.getString("newMenuItem.toolTipText")); // NOI18N
        newMenuItem.setName("newMenuItem"); // NOI18N
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setIcon(resourceMap.getIcon("openMenuItem.icon")); // NOI18N
        openMenuItem.setMnemonic('O');
        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        openMenuItem.setToolTipText(resourceMap.getString("openMenuItem.toolTipText")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setIcon(resourceMap.getIcon("saveMenuItem.icon")); // NOI18N
        saveMenuItem.setMnemonic('S');
        saveMenuItem.setText(resourceMap.getString("saveMenuItem.text")); // NOI18N
        saveMenuItem.setToolTipText(resourceMap.getString("saveMenuItem.toolTipText")); // NOI18N
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        exportMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        exportMenuItem.setIcon(resourceMap.getIcon("exportMenuItem.icon")); // NOI18N
        exportMenuItem.setMnemonic('E');
        exportMenuItem.setText(resourceMap.getString("exportMenuItem.text")); // NOI18N
        exportMenuItem.setToolTipText(resourceMap.getString("exportMenuItem.toolTipText")); // NOI18N
        exportMenuItem.setName("exportMenuItem"); // NOI18N
        exportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exportMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(kitsune.KitsuneApp.class).getContext().getActionMap(KitsuneView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        statusLabel.setText(resourceMap.getString("statusLabel.text")); // NOI18N
        statusLabel.setToolTipText(resourceMap.getString("statusLabel.toolTipText")); // NOI18N
        statusLabel.setName("statusLabel"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 909, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusMessageLabel)
                            .addComponent(statusAnimationLabel)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addComponent(statusLabel)))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void wallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wallButtonActionPerformed
        // the cursor becomes a wall painter
        this.cursor=cursorTypes.WALL;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: wall");
    }//GEN-LAST:event_wallButtonActionPerformed

    private void flammabilityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flammabilityButtonActionPerformed
        // the cursor becomes a fire painter
        this.cursor=cursorTypes.FIRE;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: fire");
    }//GEN-LAST:event_flammabilityButtonActionPerformed

    private void agentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agentButtonActionPerformed
        // the cursor becomes a agent positioner
        this.cursor=cursorTypes.AGENT;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: agent");
    }//GEN-LAST:event_agentButtonActionPerformed

    private void doorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doorButtonActionPerformed
        // the cursor becomes a door painter
        this.cursor=cursorTypes.DOOR;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: door");
    }//GEN-LAST:event_doorButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // the cursor becomes an exit painter
        this.cursor=cursorTypes.EXIT;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: exit");
        //this.inspectorUpdateNumExits(1);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void stairButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stairButtonActionPerformed
        // the cursor becomes a stair painter
        this.cursor=cursorTypes.STAIR;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: stair");
    }//GEN-LAST:event_stairButtonActionPerformed

    private void labelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelButtonActionPerformed
        // the cursor becomes a label painter
        this.cursor=cursorTypes.LABEL;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: label");
    }//GEN-LAST:event_labelButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        this.cursor=cursorTypes.IDLE;
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kitsune.KitsuneApp.class).getContext().getResourceMap(KitsuneView.class);

        //build graph for level 0 using agent 0
        //this solution is not ideal:
        //- it does not build the graph for every level
        //-it needs an agent to be on the level for the graph to be built
        if (this.loadedEnvironment.getNumAgents()>0 && this.loadedEnvironment.getTotalAgentsExited()==0){
            this.setStatusMessage("Building graphs...");
            this.loadedEnvironment.getLevel(0).buildGraph(((Agent)this.loadedEnvironment.getAgents().get(0)).getSquare());
            this.setStatusMessage("Ok!");
        }

        if (loadedEnvironment.getNumLevels()>0){
            if (!this.simulationStarted){
                //we build the graphs for the optimal agents
                Iterator it= this.loadedEnvironment.getAgents().iterator();
                while (it.hasNext()){
                    Agent tempAgent= (Agent) it.next();
                    if (tempAgent.getBehavior().getType().equals("optimal"))
                        ((OptimalBehavior) tempAgent.getBehavior()).calculateGraph();
                }

                //We get the current time
                Date now = new Date();
                this.lastSimCheck=now.getTime(); //the sim started now

                //we start the timer that will update the inspector pane
                this.inspectorUpdaterTimer.start();

                this.setStatusMessage("Simulation started");
                startButton.setIcon(new ImageIcon(this.loadIcon("resources/icons/control_pause_blue.png")));
                startButton.setToolTipText("Pause the simulation");

                //the simulation has started
                this.simulationStarted=true;
                busyIconTimer.start();

                //we start the visualization panel update
                simUpdaterTimer.start();

            } else {

                //We get the current time
                Date now = new Date();
                //the runtime is the past runtime added to the time since the runtime was last updated
                this.simRunTime+=now.getTime()-this.lastSimCheck;

                //we don't need the timer anymore
                this.inspectorUpdaterTimer.stop();

                this.setStatusMessage("Simulation paused");
                startButton.setIcon(new ImageIcon(this.loadIcon("resources/icons/control_play_blue.png")));
                startButton.setToolTipText("Start the simulation");

                //the simulation has been paused
                this.simulationStarted=false;
                busyIconTimer.stop();
                statusAnimationLabel.setIcon(idleIcon);

                //we start the visualization panel update
                simUpdaterTimer.stop();

            }
    }
    }//GEN-LAST:event_startButtonActionPerformed

    private void exportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMenuItemActionPerformed
        try {
            BufferedWriter outputStream = null;
            outputStream = new BufferedWriter(new FileWriter("Kitsune log.txt"));

            outputStream.write("This log was written on ");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            outputStream.write(dateFormat.format(date));
            outputStream.newLine();
            outputStream.newLine();
     
            for (int i=0; i<inspectorTable.getModel().getRowCount();i++){

                if (inspectorTable.getModel().getValueAt(i,0)!="") {
                outputStream.write(inspectorTable.getModel().getValueAt(i,0)+":");
                outputStream.write(" "+inspectorTable.getModel().getValueAt(i,1));
                } else {
                    outputStream.newLine();
                }
                outputStream.newLine();
            }

            this.setStatusMessage("Simulation log exported.");
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(KitsuneView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_exportMenuItemActionPerformed

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        //we alert the user that changes will be lost
        newAlert alert;
        alert= new newAlert(null, true, this);
        alert.setVisible(true);
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed

        if (!this.simulationStarted){
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();


        if (returnVal==JFileChooser.APPROVE_OPTION){
            this.createNewEnvironment();
            FileInputStream fis = null;
            ObjectInputStream in = null;
            try {
                fis = new FileInputStream(file.getAbsolutePath());
                in = new ObjectInputStream(fis);
                this.loadedEnvironment = (Environment)in.readObject();
                in.close();
                this.setStatusMessage("Project opened file: "+file.getName());
                

                for (int i=0;i<loadedEnvironment.getNumLevels();i++){
                    buildingFloor pane = new buildingFloor(loadedEnvironment,i); //TODO check
                    editorPane.insertTab("Floor "+(i+1), null, pane, null, i);
                }

                editorPane.setSelectedIndex(0);

            } catch (IOException ex){
            } catch(ClassNotFoundException ex){
                System.out.println("Class not found! "+ex.toString());
            }
        }
        this.editorPane.getSelectedComponent().repaint();
        this.updateUI();
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        File file = fc.getSelectedFile();
        
        if (returnVal==JFileChooser.APPROVE_OPTION){
            FileOutputStream fos = null;
            ObjectOutputStream out = null;
            try {
                fos = new FileOutputStream(file.getAbsolutePath());
                out = new ObjectOutputStream(fos);
                out.writeObject(loadedEnvironment);
                out.close();
                this.setStatusMessage("Current project saved as "+file.getName());
            } catch (IOException ex){
            }
            
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void addFloorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFloorButtonActionPerformed
        if (loadedEnvironment.getNumLevels()<20  && !this.simulationStarted){ //cap at 20 floors
            loadedEnvironment.addLevel();
            buildingFloor pane = new buildingFloor(loadedEnvironment,loadedEnvironment.getNumLevels()-1); //TODO check
            editorPane.insertTab("Floor "+loadedEnvironment.getNumLevels(), null, pane, null, loadedEnvironment.getNumLevels()-1);
            this.setStatusMessage("Added floor "+loadedEnvironment.getNumLevels());
            editorPane.setSelectedIndex(loadedEnvironment.getNumLevels()-1);
            this.editorPane.getSelectedComponent().repaint();
        }
    }//GEN-LAST:event_addFloorButtonActionPerformed

    private void removeFloorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFloorButtonActionPerformed
    
        if (loadedEnvironment.getNumLevels()>0 && !this.simulationStarted){
            loadedEnvironment.removeLevel(loadedEnvironment.getNumLevels()-1);
            editorPane.removeTabAt(loadedEnvironment.getNumLevels());
            this.setStatusMessage("Removed floor");
        }
    }//GEN-LAST:event_removeFloorButtonActionPerformed

    private void editorPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorPaneMouseClicked
        //get coordinates of mouse clicked
        int mousex=evt.getX();
        int mousey=evt.getY();

        //get the current tab
        int currentTab= this.editorPane.getSelectedIndex();

        if (loadedEnvironment.getNumLevels()>0 && !this.simulationStarted){ //if there are floors in the editor and the sim is not running
                //get the selected nodes
                int node1=((buildingFloor)this.editorPane.getSelectedComponent()).getHNode1();
                int node2=((buildingFloor)this.editorPane.getSelectedComponent()).getHNode2();
                
                if (this.cursor==cursorTypes.DOOR){ //we (de)activate the selected door if the cursor is a door
                    loadedEnvironment.getLevel(currentTab).selectDoor(node1, node2);
                } else if(this.cursor==cursorTypes.WALL){ //we (de)activate the selected wall if the cursor is a wall
                    loadedEnvironment.getLevel(currentTab).selectWall(node1, node2);
                } else if(this.cursor==cursorTypes.EXIT){ //we (de)activate the selected exit if the cursor is a door
                    loadedEnvironment.getLevel(currentTab).selectExit(node1, node2);
                } else if(this.cursor==cursorTypes.FIRE){ //we change the fire intensity of the currently selected tile if the cursor is fire

                    int square=(int)Math.floor((mousey-25)/32)+(int)((mousex-25)/32)*20;
                    int i=square%20;
                    int j=(int)Math.floor(square/20);
                    loadedEnvironment.getLevel(currentTab).getFire().increaseFireIntensity(i,j);
                } else if(this.cursor==cursorTypes.AGENT){
                    String str= new String();

                    behaviorPicker bp;
                    bp = new behaviorPicker(null, true, this);
                    bp.setVisible(true);

                    str=bp.getString(); //we get the behavior string
                    if (str!=null && !str.isEmpty())
                        loadedEnvironment.addAgent(mousex, mousey, currentTab,str);
                    
                } else if(this.cursor==cursorTypes.LABEL){
                    String str= new String();

                    insertLabel popup;
                    popup= new insertLabel(null,true);
                    popup.setVisible(true);
                    
                    str=popup.getString(); //we get the string that was input in the popup
                    if (str!=null && !str.isEmpty())
                        loadedEnvironment.getLevel(currentTab).insertLabel(mousex, mousey, str);
                } else if(this.cursor==cursorTypes.AGENTREMOVER){
                    loadedEnvironment.removeAgent(mousex, mousey, currentTab);
                }

            this.updateUI(); //we update the UI

        }

    }//GEN-LAST:event_editorPaneMouseClicked

    private void editorPaneMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorPaneMouseMoved
        int mousex=evt.getX();
        int mousey=evt.getY();

        int currentTab= this.editorPane.getSelectedIndex();

        if (this.cursor==cursorTypes.WALL || this.cursor==cursorTypes.DOOR || this.cursor==cursorTypes.EXIT){
            if (loadedEnvironment.getNumLevels()>0){
                int node1=0;
                int node2=0;

                if ((mousey-25)%32<15){
                    //add line to current floor - horizontal
                    node1=(int)Math.floor((mousex-25)/32)+(int)Math.floor((mousey-25)/32)*20+1;
                    node2=(int)Math.floor((mousex-25)/32)+(int)Math.floor((mousey-25)/32)*20;
                } else if((mousex-25)%32<15) {
                    //add line to current floor - vertical
                    node1=(int)Math.floor((mousex-25)/32)+(int)Math.floor((mousey-25)/32)*20+20;
                    node2=(int)Math.floor((mousex-25)/32)+(int)Math.floor((mousey-25)/32)*20;
                }
                if (node1%20!=0 && node2%20!=19) //temporary hack to avoid edges overflowing on the next line
                    ((buildingFloor)this.editorPane.getSelectedComponent()).highlight(node1, node2);
                this.editorPane.getSelectedComponent().repaint();
            }
        }        
    }//GEN-LAST:event_editorPaneMouseMoved

    private void editorPaneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorPaneMouseExited
    if (loadedEnvironment.getNumLevels()>0){
        ((buildingFloor)this.editorPane.getSelectedComponent()).highlight(0, 0);
        this.editorPane.getSelectedComponent().repaint();
    }
    }//GEN-LAST:event_editorPaneMouseExited

    private void labelRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelRemoveButtonActionPerformed
        //get the current tab
        int currentTab= this.editorPane.getSelectedIndex();

        labelRemove popup;
        popup= new labelRemove(null,true,this.loadedEnvironment.getLevel(currentTab).getLabels());
        popup.setVisible(true);
        int index= popup.getIndex();
        
        if (index!=-1)
            loadedEnvironment.getLevel(currentTab).removeLabel(index);
        this.updateUI();
    }//GEN-LAST:event_labelRemoveButtonActionPerformed

    private void agentRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agentRemoveButtonActionPerformed
        // the cursor becomes a wall painter
        this.cursor=cursorTypes.AGENTREMOVER;
        //we update the label for debug
        this.setStatusMessage("Cursor selected: agent remover");
    }//GEN-LAST:event_agentRemoveButtonActionPerformed

    private Image loadIcon(String path){ //loads an icon from within the jar with the given path
            Image icon=null;
            try{
                InputStream image= this.getClass().getResourceAsStream(path);
                icon= ImageIO.read(image);
            } catch (IOException e) {
            }
            return icon;
    }

    public void setStatusMessage(String newStatus){
        statusLabel.setText(newStatus);
    }

    public void inspectorUpdateNumExits(int offset){
        //this.inspectorUpdate(0,loadedEnvironment.getNumExits()+offset);
        //loadedEnvironment.addExit();
        this.setStatusMessage("Added an exit");
    }

    public void inspectorUpdateNumAgents(int offset){
        //this.inspectorUpdate(0,loadedEnvironment.getNumAgents()+offset);
        this.setStatusMessage("Added an agent");
    }

    public void inspectorUpdateSimRuntime(){
            this.inspectorUpdate(3,(int)simRunTime/1000);
        }

    public void simulationUpdate(){
        this.loadedEnvironment.setSimRunTime(this.simRunTime);

        int currentTab= this.editorPane.getSelectedIndex();

        //update the fire
        loadedEnvironment.getLevel(currentTab).getFire().fireStep();
        
        //update the agents
        Iterator it = loadedEnvironment.getAgents().iterator();
        while(it.hasNext()){
            Agent a = (Agent)it.next();
            a.step();
        }
        
        this.editorPane.getSelectedComponent().repaint(); //and we repaint the editor


    }

    public void inspectorUpdate(int row, int value){
        inspectorTable.getModel().setValueAt(value, row, 1);
    }

    public void createNewEnvironment(){
        while (loadedEnvironment.getNumLevels()!=0){
            editorPane.removeTabAt(loadedEnvironment.getNumLevels()-1);
            loadedEnvironment.removeLevel(loadedEnvironment.getNumLevels()-1);
        }

        System.out.println(loadedEnvironment.getNumLevels());

        loadedEnvironment = new Environment();
        this.simulationStarted=false;
        this.simRunTime=0;
        this.updateInspector();
        this.setStatusMessage("New environment created");

        //TODO: complete
    }

    public Graphics getPane(){
       Graphics g= this.editorPane.getGraphics();

       return g;
    }

    public void updateUI(){ //this function redraws the whole UI- used when a new environment is created or loaded
        //repaint canvas
        this.editorPane.getSelectedComponent().repaint();

        this.updateInspector();
    }

    public void updateInspector(){
        //refill inspector
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getNumExits(), 0, 1); //update the number of doors
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getNumDoors(), 1, 1); //update the number of exits

        inspectorTable.getModel().setValueAt(this.simRunTime, 3, 1); //update the running time of simulation
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getNumAgents(), 4, 1); //total number of agents
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getTotalAgentsExited(), 5, 1); //get total number of agents who exited
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getTotalAgentsExiting(), 6, 1); //get total number of agents exiting
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getTotalAgentsStuck(), 7, 1); //get total agents stuck

        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getAverageExitTime()/1000, 9, 1);
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getFirstAgentExit()/1000, 10, 1);
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getLastAgentExit()/1000, 11, 1);
        inspectorTable.getModel().setValueAt(this.loadedEnvironment.getSuccessPercentage()+" %", 12, 1);
    }

    public Environment getEnvironment(){
        return this.loadedEnvironment;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFloorButton;
    private javax.swing.JButton agentButton;
    private javax.swing.JButton agentRemoveButton;
    private javax.swing.JSeparator buttonSeparator;
    private javax.swing.JSeparator buttonSeparator2;
    private javax.swing.JButton doorButton;
    private javax.swing.JTabbedPane editorPane;
    private javax.swing.JButton exitButton;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JButton flammabilityButton;
    private javax.swing.JTable inspectorTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton labelButton;
    private javax.swing.JButton labelRemoveButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JSeparator menuSeparator;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton removeFloorButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton stairButton;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton wallButton;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private final Timer inspectorUpdaterTimer;
    private final Timer simUpdaterTimer;

    //the possible cursor types
    private enum cursorTypes { IDLE, WALL, FIRE, AGENT, DOOR, EXIT, STAIR, LABEL, AGENTREMOVER };

    //the current cursor
    private cursorTypes cursor= cursorTypes.WALL;

    //0 if the simulation is paused, 1 otherwise.
    private JDialog aboutBox;

    private Environment loadedEnvironment;

    private boolean simulationStarted=false;
    public long simRunTime=0;
    private long lastSimCheck;
}
