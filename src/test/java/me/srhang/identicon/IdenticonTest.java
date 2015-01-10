package me.srhang.identicon;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author: Bryant Hang
 * Date: 15/1/9
 * Time: 上午10:05
 */
public class IdenticonTest {
    @Test
    public void test() throws IOException {
        Identicon identicon = new Identicon();

        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString("test", Charsets.UTF_8);
        String md5 = hasher.hash().toString();

        BufferedImage image = identicon.create(md5, 200);

        ImageIO.write(image, "PNG", new File("test.png"));
    }
}
