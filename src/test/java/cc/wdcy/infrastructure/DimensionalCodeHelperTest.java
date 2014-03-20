/*
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
 */
package cc.wdcy.infrastructure;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

/**
 * @author Shengzhao Li
 */
public class DimensionalCodeHelperTest {

    @Test
    public void encode() throws Exception {

        File file = file();

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix encode = qrCodeWriter.encode("http://honyee.biz  宏义", BarcodeFormat.QR_CODE, 200, 200, hints);

        assertNotNull(encode);
        MatrixToImageWriter.writeToFile(encode, "png", file);

    }

    @Test
    public void decode() throws Exception {

        File file = file();

        QRCodeReader qrCodeReader = new QRCodeReader();

        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        LuminanceSource source = new BufferedImageLuminanceSource(ImageIO.read(file));
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap image = new BinaryBitmap(binarizer);

        Result result = qrCodeReader.decode(image, hints);
        String text = result.getText();
        System.out.println(text);
    }

    private File file() throws IOException, URISyntaxException {
        Resource resource = new ClassPathResource("dim-code.png");
        return resource.getFile();
    }

}