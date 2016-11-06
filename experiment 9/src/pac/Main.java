package pac;

import java.util.HashMap;
import java.util.Map;


public class Main {
	
	Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
	hints.put(EncodeHintType.MARGIN, 0);
	BitMatrix bitMatrix = new QRCodeWriter().encode("生成二维码的内容",
	BarcodeFormat.QR_CODE, 256, 256,hints);
	int width = bitMatrix.getWidth();
	int height = bitMatrix.getHeight();
	BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
	for (int x = 0; x < width; x++) {
	     for (int y = 0; y < height; y++) {
	           image.setRGB(x, y, bitMatrix.get(x, y) == true ? 
	           Color.BLACK.getRGB():Color.WHITE.getRGB());
	    }
	}
	ImageIO.write(image,"png", new File("./"));
}
