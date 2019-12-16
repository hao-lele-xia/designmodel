package hao.lele.xia.nio;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/16 11:03
 */
@Service
public class NioFileService {

    private static final String rootName = "D:/temp/nio/";

    public static String write(byte[] data) throws IOException {
        Path path = Paths.get(rootName);
        if(Files.notExists(path)){
            Files.createDirectories(path);
        }
        String filePath = rootName + UUID.randomUUID().toString();
        Path file = Paths.get(filePath);
        FileChannel fc = new RandomAccessFile(file.toFile(),"rw").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.clear();
        buffer.put(data);
        buffer.flip();
        while(buffer.hasRemaining()){
            fc.write(buffer);
        }
        fc.close();
        return filePath;
    }

    public static byte[] read(String path) throws IOException {
        Path file = Paths.get(path);
        if (Files.exists(file)) {
            FileChannel fc = new RandomAccessFile(file.toFile(),"r").getChannel();
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if(buffer.hasRemaining()){
                buffer.get(result,0,buffer.remaining());
            }
            fc.close();
            return result;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String data = "abc123";
        String path = write(data.getBytes());
        System.out.println(path);
        System.out.println(new String(read(path)));
    }
}
