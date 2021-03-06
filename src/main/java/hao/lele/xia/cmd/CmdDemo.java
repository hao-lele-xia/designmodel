package hao.lele.xia.cmd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/18 9:57
 */
public class CmdDemo {

    public void execCommand(String cmd){
        try{
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd,null,null);
            InputStream stderr =  proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stderr, Charset.defaultCharset());
            BufferedReader br = new BufferedReader(isr);
            String line="";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
