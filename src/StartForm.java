import bf.BFImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm implements ActionListener {


    public StartForm() {

        JFrame frame = new JFrame("Start menu");
        frame.setLocation(400, 200);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Content pane.
        JComponent newContentPane = StartPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);

    }


    static String Tiger = "Tiger";
    static String BT7 = "BT7";
    JLabel picture;


    //
    private JPanel StartPanel(){

        JPanel panel = new JPanel();

        BorderLayout layoutTankChoice=new BorderLayout();
        panel.setLayout(layoutTankChoice);

        //Create the radio buttons.
        JRadioButton buttonTiger = new JRadioButton(Tiger);
        //buttonTiger.setMnemonic(KeyEvent.VK_B);
        buttonTiger.setActionCommand(Tiger);
        buttonTiger.setSelected(true);

        JRadioButton buttonBT7 = new JRadioButton(BT7);
        // buttonBT7.setMnemonic(KeyEvent.VK_C);
        buttonBT7.setActionCommand(BT7);

        ButtonGroup group = new ButtonGroup();
        group.add(buttonTiger);
        group.add(buttonBT7);

        buttonTiger.addActionListener(this);
        buttonBT7.addActionListener(this);

        picture = new JLabel(createImageIcon(BFImages.IMG_TIGER_TOP));
        picture.setPreferredSize(new Dimension(10, 10));


        //
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(buttonTiger);
        radioPanel.add(buttonBT7);

        //
        JButton buttonOk=new JButton("OK");
        buttonOk.setActionCommand("OK");
        buttonOk.addActionListener(this);


        //panel.add(radioPanel, BorderLayout.LINE_START);
        panel.add(radioPanel,BorderLayout.WEST);
        panel.add(picture, BorderLayout.CENTER);
        panel.add(buttonOk,BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return panel;
    }


    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("BT7")){
            picture.setIcon(createImageIcon(BFImages.IMG_BT7_TOP));
        }
        else if(e.getActionCommand().equalsIgnoreCase("Tiger")){
            picture.setIcon(createImageIcon(BFImages.IMG_TIGER_TOP));
        }

        if(e.getActionCommand().equalsIgnoreCase("OK")){
            System.out.println("start ok");

            //System.exit(0);
        }
    }
}

