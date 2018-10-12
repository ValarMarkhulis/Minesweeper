package MinesweeperUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JButton button0;
    private JButton button7;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JButton button26;
    private JButton button27;
    private JButton button28;
    private JButton button29;
    private JButton button30;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button35;
    private JButton button36;
    private JButton button37;
    private JButton button38;
    private JButton button39;
    private JButton button40;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;
    private JButton button45;
    private JButton button46;
    private JButton button47;
    private JButton button48;
    private JButton button49;
    private JButton button50;
    private JButton button51;
    private JButton button52;
    private JButton button53;
    private JButton button54;
    private JButton button55;
    private JButton button56;
    private JButton button57;
    private JButton button58;
    private JButton button59;
    private JButton button60;
    private JButton button61;
    private JButton button62;
    private JButton button63;
    private JButton button64;
    private JButton button65;
    private JButton button66;
    private JButton button67;
    private JButton button68;
    private JButton button69;
    private JButton button70;
    private JButton button71;
    private JButton button72;
    private JButton button73;
    private JButton button74;
    private JButton button75;
    private JButton button76;
    private JButton button77;
    private JButton button78;
    private JButton button79;
    private JButton button80;
    private JButton button81;
    private JButton button82;
    private JButton button83;
    private JButton button84;
    private JButton button85;
    private JButton button86;
    private JButton button87;
    private JButton button88;
    private JButton button89;
    private JButton button90;
    private JButton button91;
    private JButton button92;
    private JButton button93;
    private JButton button94;
    private JButton button95;
    private JButton button96;
    private JButton button97;
    private JButton button98;
    private JButton button99;
    private JButton button100;
    private JButton button101;
    private JButton button102;
    private JButton button103;
    private JButton button104;
    private JButton button105;
    private JButton button106;
    private JButton button107;
    private JButton button108;
    private JButton button109;
    private JButton button110;
    private JButton button111;
    private JButton button112;
    private JButton button113;
    private JButton button114;
    private JButton button115;
    private JButton button116;
    private JButton button117;
    private JButton button118;
    private JButton button119;
    private JButton button120;
    private JButton button121;
    private JButton button122;
    private JButton button123;
    private JButton button124;
    private JButton button125;
    private JButton button126;
    private JButton button127;
    private JButton button128;
    private JButton button129;
    private JButton button130;
    private JButton button131;
    private JButton button132;
    private JButton button133;
    private JButton button134;
    private JButton button135;
    private JButton button136;
    private JButton button137;
    private JButton button138;
    private JButton button139;
    private JButton button140;
    private JButton button141;
    private JButton button142;
    private JButton button143;
    private JButton button144;
    private JButton button145;
    private JButton button146;
    private JButton button147;
    private JButton button148;
    private JButton button149;
    private JButton button150;
    private JButton button151;
    private JButton button152;
    private JButton button153;
    private JButton button154;
    private JButton button155;
    private JButton button156;
    private JButton button157;
    private JButton button158;
    private JButton button159;
    public javax.swing.JPanel JPanel;

    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    */

    public GUI(){
        Component[] components = JPanel.getComponents();
        for(Component component : components){
            if(component instanceof JButton){
                JButton button = (JButton) component;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //System.out.println("Actionperformed: "+e.getSource().toString());
                        System.out.println((JComponent ) e.getSource()+" clicked");
                    }
                });
            }
        }
    }
}
