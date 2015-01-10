package me.srhang.identicon;

import com.google.common.base.Preconditions;
import com.google.common.math.DoubleMath;
import me.srhang.identicon.generator.IBaseGenartor;
import me.srhang.identicon.generator.impl.DefaultGenerator;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;

/**
 * Author: Bryant Hang
 * Date: 15/1/10
 * Time: 下午2:42
 */
public class Identicon {
    private IBaseGenartor genartor;

    public Identicon() {
        this.genartor = new DefaultGenerator();
    }

    public Identicon(IBaseGenartor genartor) {
        this.genartor = genartor;
    }

    public BufferedImage create(String hash, int size) {
        Preconditions.checkArgument(size > 0 && StringUtils.isNotBlank(hash));

        boolean[][] array = genartor.getBooleanValueArray(hash);


        int ratio = DoubleMath.roundToInt(size / 5.0, RoundingMode.HALF_UP);

        BufferedImage identicon = new BufferedImage(ratio * 5, ratio * 5, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = identicon.getGraphics();

        graphics.setColor(genartor.getBackgroundColor()); // 背景色
        graphics.fillRect(0, 0, identicon.getWidth(), identicon.getHeight());

        graphics.setColor(genartor.getForegroundColor()); // 图案前景色
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (array[i][j]) {
                    graphics.fillRect(j * ratio, i * ratio, ratio, ratio);
                }
            }
        }

        return identicon;
    }
}
