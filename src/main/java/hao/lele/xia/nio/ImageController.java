package hao.lele.xia.nio;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/16 11:25
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImapge(String path) throws IOException {
        return NioFileService.read(path);
    }
}
