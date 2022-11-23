package site.currychoco.hrmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.service.EmployeeService;
import site.currychoco.hrmanager.trdparty.naver.romanization.domain.RomanizationDto;
import site.currychoco.hrmanager.trdparty.naver.romanization.service.RomanizationService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@SpringBootTest
class HrManagerApplicationTests {

    @Autowired
    EmployeeService employeeService;

    final String STORAGE_PATH = "c:/storages/HR_MANAGER";

    @Test
    void 명함출력_테스트() {

        final long empNo = 10002L;
        EmployeeAllInfo empInfo = employeeService.getEmpInfoByEmpNo(empNo);
        createImage(empInfo);
    }

    private void createImage(EmployeeAllInfo empInfo) {

        String filePath = STORAGE_PATH + "/cards/" + empInfo.getEmpNo();

        try {
            new File(filePath).mkdir();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        try {
            final String fontName = "맑은 고딕";
            BufferedImage cardImg = ImageIO.read(ResourceUtils.getFile("classpath:static/image/card_template/card_ko.png"));
            BufferedImage nameImg = this.convertTextToBufferedImage(empInfo.getEmpName(), new Font(fontName, Font.PLAIN, 42), Color.BLACK);
            BufferedImage deptImg = this.convertTextToBufferedImage(empInfo.getDeptName(), new Font(fontName, Font.PLAIN, 18), Color.BLACK);
            BufferedImage jobImg = this.convertTextToBufferedImage(empInfo.getJobName(), new Font(fontName, Font.PLAIN, 18), Color.BLACK);
            BufferedImage positionImg = this.convertTextToBufferedImage(empInfo.getPositionName(), new Font(fontName, Font.PLAIN, 18), Color.BLACK);
            BufferedImage phoneImg = this.convertTextToBufferedImage(empInfo.getPhone(), new Font(fontName, Font.PLAIN, 28), Color.BLACK);
            BufferedImage emailImg = this.convertTextToBufferedImage(empInfo.getEmail(), new Font(fontName, Font.PLAIN, 28), Color.BLACK);

            int width = cardImg.getWidth();
            int height = cardImg.getHeight();

            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = (Graphics2D) result.getGraphics();

            graphics.setBackground(Color.WHITE);
            graphics.drawImage(cardImg, 0, 0, null);

            graphics.drawImage(nameImg, 20, 30, null);
            graphics.drawImage(deptImg, 25, 100, null);
            graphics.drawImage(jobImg, 25, 125, null);
            graphics.drawImage(positionImg, 25, 150, null);

            graphics.drawImage(phoneImg, 420, 210, null);
            graphics.drawImage(emailImg, 55, 210, null);

            ImageIO.write(result, "png", new File(filePath + "/" + empInfo.getEmpNo() + "_ko.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage convertTextToBufferedImage(String text, Font font, Color color) throws Exception {

        BufferedImage image = null;

        try {
            image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2d = image.createGraphics();
            graphics2d.setFont(font);
            FontMetrics fontMetrics = graphics2d.getFontMetrics();
            int width = fontMetrics.stringWidth(text);
            int height = fontMetrics.getHeight();
            graphics2d.dispose();

            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            graphics2d = image.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            graphics2d.setFont(font);
            fontMetrics = graphics2d.getFontMetrics();
            graphics2d.setColor(color);
            graphics2d.drawString(text, 0, fontMetrics.getAscent());
            graphics2d.dispose();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return image;
    }
}
