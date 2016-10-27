/**
 * Created by semeykin on 26.10.2016.
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();

            //HomeTask
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(51);
            arrayList.add(92);
            arrayList.add(122);
            arrayList.add(522);
            arrayList.add(722);
            arrayList.add(823);

            ObjectOutputStream oos = new ObjectOutputStream(sout);

            System.out.println("Sending this arrayList to the server...");

            //Сериализовать
            oos.writeObject(arrayList);
            oos.flush();

            line = in.readUTF(); // ждем пока сервер пришлет строку текста.
            System.out.println("The server was very polite. It sent me this : " + line);
            oos.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
