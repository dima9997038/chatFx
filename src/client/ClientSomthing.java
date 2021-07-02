package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dima on 15.11.2018.
 */
public class ClientSomthing {
    private Socket socket;
    private BufferedReader in; // ����� ������ �� ������
    private BufferedWriter out; // ����� ������ � �����
    private BufferedReader inputUser; // ����� ������ � �������
    private String addr; // ip ����� �������
    private int port; // ���� ����������
    private String nickname; // ��� �������
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    private String message;


    public ClientSomthing(String addr, int port, String nickname, String message) {
        this.addr = addr;
        this.port = port;
        this.nickname=nickname;
        this.message=message;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            // ������ ������ �� ������ / ������ � �����, � ������ � �������
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname(); // ����� ������� ���������� ������� ���

            out.write("Hello " + nickname + "\n");
            out.flush();
            new ReadMsg().start(); // ���� �������� ��������� �� ������ � ����������� �����
            new WriteMsg().run(); // ���� ������� ��������� � ����� ���������� � ������� � ����������� �����
        } catch (IOException e) {
            // ����� ������ ���� ������ ��� �����
            // ������, ����� ������ ������������ ������:
            ClientSomthing.this.downService();
        }
        // � ��������� ������ ����� ����� ������
        // � ������ run() ����.
    }

    /**
     * ������� ������ ���,
     * � ������� ��� � ����������� �� ������
     */

    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname = inputUser.readLine();
            out.write("Hello " + nickname + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    /**
     * �������� ������
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }

    // ���� ������ ��������� � �������
    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine(); // ���� ��������� � �������
                    if (str.equals("stop")) {
                        ClientSomthing.this.downService(); // ��������
                        break; // ������� �� ����� ���� ������ "stop"
                    }
                    System.out.println(str); // ����� ��������� � ������� �� �������
//                    Controller controller=new Controller();
//                    controller.writeMsgText();
                    Controller.writeMsgText(str);
                }
            } catch (IOException e) {
                ClientSomthing.this.downService();
            }
        }
    }

    // ���� ������������ ��������� ���������� � ������� �� ������
    public class WriteMsg  {


        public void run() {
          //  while (true)
            {
                String userWord;
                try {
                    time = new Date(); // ������� ����
                    dt1 = new SimpleDateFormat("HH:mm:ss"); // ����� ������ ����� �� ������
                    dtime = dt1.format(time); // �����
                   // userWord = inputUser.readLine(); // ��������� � �������
                    userWord=message;
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        ClientSomthing.this.downService(); // ��������
                      //  break; // ������� �� ����� ���� ������ "stop"
                    } else {
                        out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n"); // ���������� �� ������
                    }
                    out.flush(); // ������
                } catch (IOException e) {
                    ClientSomthing.this.downService(); // � ������ ���������� ���� ��������

                }

            }
        }
    }

}
