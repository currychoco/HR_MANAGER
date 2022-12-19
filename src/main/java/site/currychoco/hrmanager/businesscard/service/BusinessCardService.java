package site.currychoco.hrmanager.businesscard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import site.currychoco.hrmanager.businesscard.domain.BusiCard;
import site.currychoco.hrmanager.businesscard.domain.BusiCardDto;
import site.currychoco.hrmanager.businesscard.repository.BusiCardRepository;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;
import site.currychoco.hrmanager.emp.repository.EmployeeAllInfoRepository;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessCardService {

    private final EmployeeAllInfoRepository employeeAllInfoRepository;

    private final BusiCardRepository busiCardRepository;

    private final String BUSINESS_CARD_FONT_NAME = "NanumGothic";

    public void responseBusinessCardImage(long empNo, String cardType, HttpServletResponse res) {

        try {
            // 사원정보 조회
            EmployeeAllInfo empInfo = employeeAllInfoRepository.getAllByEmpNo(empNo);
            
            // 이미지 정보 생성
            BufferedImage buffImage = createImage(empInfo, cardType);

            // 이미지 정보를 response 에 담기 위한 변환 작업
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffImage, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            // response 에 이미지 헤더 정보 및 이미지 data 적재
            res.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(is, res.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 사원정보를 통해 이미지객체(BufferedImage) 생성
     */
    private BufferedImage createImage(EmployeeAllInfo empInfo, String cardType) {

        try {
            BufferedImage cardImg = ImageIO.read(new ClassPathResource("static/image/card_template/card_" + cardType + ".png").getInputStream());

            String empName = "ko".equals(cardType) ? empInfo.getEmpName() : empInfo.getEmpNameEn();
            String deptName = "ko".equals(cardType) ? empInfo.getDeptName() : empInfo.getDeptNameEn();
            String jobName = "ko".equals(cardType) ? empInfo.getJobName() : empInfo.getJobNameEn();
            String positionName = "ko".equals(cardType) ? empInfo.getPositionName() : empInfo.getPositionNameEn();

            BufferedImage nameImg = this.convertTextToBufferedImage(empName, new Font(BUSINESS_CARD_FONT_NAME, Font.PLAIN, 42), Color.BLACK);
            BufferedImage deptImg = this.convertTextToBufferedImage(deptName, new Font(BUSINESS_CARD_FONT_NAME, Font.PLAIN, 18), Color.BLACK);
            BufferedImage jobImg = this.convertTextToBufferedImage(jobName, new Font(BUSINESS_CARD_FONT_NAME, Font.PLAIN, 18), Color.BLACK);
            BufferedImage positionImg = this.convertTextToBufferedImage(positionName, new Font(BUSINESS_CARD_FONT_NAME, Font.PLAIN, 18), Color.BLACK);
            BufferedImage phoneImg = this.convertTextToBufferedImage(empInfo.getPhone(), new Font(BUSINESS_CARD_FONT_NAME, Font.PLAIN, 28), Color.BLACK);
            BufferedImage emailImg = this.convertTextToBufferedImage(empInfo.getEmail(), new Font(BUSINESS_CARD_FONT_NAME, Font.PLAIN, 28), Color.BLACK);

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

            graphics.drawImage(phoneImg, 55, 294, null);
            graphics.drawImage(emailImg, 55, 208, null);

            return result;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 문자열을 이미지객체(BufferedImage) 로 변환
     * reference : https://stackoverflow.com/questions/18800717/convert-text-content-to-image
     */
    private BufferedImage convertTextToBufferedImage(String text, Font font, Color color) {

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

    /**
     * 명함 신청
     */
    public void requestBusiCard(BusiCardDto dto){
        BusiCard busiCard = new BusiCard(dto);
        busiCardRepository.save(busiCard);
    }

    /**
     * 명함 신청 리스트
     */
    public List<BusiCardDto> getBusiList(){

        return busiCardRepository.findAll(Sort.by(Sort.Direction.DESC, "no")).stream()
                .map(n -> BusiCardDto.fromEntity(n))
                .collect(Collectors.toList());
    }

    /**
     * 명함 신청 no으로 출력
     */
    public BusiCardDto getBusiCardDtoByNo(Long no){
        BusiCard busiCard = busiCardRepository.findById(no).orElseThrow();
        BusiCardDto busiCardDto = BusiCardDto.fromEntity(busiCard);
        return busiCardDto;
    }

    /**
     * 명함 신청 승인
     */
    public void allowBusiCard(BusiCardDto dto){
        BusiCard busiCard = new BusiCard(dto);
        busiCardRepository.save(busiCard);
    }
}
