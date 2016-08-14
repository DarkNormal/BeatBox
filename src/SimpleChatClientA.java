import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Mark on 08/08/2016.
 */
public class SimpleChatClientA {
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    public void go(){
    try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        panel.add(incoming);
        panel.add(outgoing);
        panel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        frame.setSize(400,500);
        frame.setVisible(true);

    }

    private void setUpNetworking() {
        try {
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                writer.write(outgoing.getText());
                writer.flush();
            }catch(Exception e){
                e.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try{
                while((message = reader.readLine()) != null ){
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleChatClientA chatClientA = new SimpleChatClientA();
        chatClientA.go();
    }
}
