package hao.lele.xia.networkio;

import org.springframework.util.StreamUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/1/4 17:07
 */
public class NetWorkIO {
    public static void main(String[] args) throws IOException {

    }

    public static void read1() throws IOException {
        URL url = new URL("http://192.168.120.17:9003/?/scenevehicles/dn@node17/10/96-23c35a64-19c31");
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        int len;
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
        }
        OutputStream outputStream1 = new FileOutputStream(new File("D:/tmp/321.jpg"));
        outputStream1.write(outputStream.toByteArray());
    }

    public static void read2() throws IOException {
        URL url = new URL("http://192.168.120.17:9003/?/scenevehicles/dn@node17/10/96-23c35a64-19c31");
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes2 = StreamUtils.copyToByteArray(inputStream);
        OutputStream outputStream2 = new FileOutputStream(new File("D:/tmp/456.jpg"));
        outputStream2.write(bytes2);
    }
}
