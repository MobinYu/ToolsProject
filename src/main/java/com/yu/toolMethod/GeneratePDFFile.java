package com.yu.toolMethod;

import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.yu.model.UserQr;

public class GeneratePDFFile {
	
	public static String createPDFFile(String basepath, List<UserQr> userQrList){
		String filePath = "";
		try {
			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4.rotate(), 36F, 36F, 144F, 36F);
			// 构造好的pdf文件输出位置
			filePath = basepath + String.valueOf(System.currentTimeMillis())+ ".pdf";
			PdfWriter.getInstance(pdfDoc, new FileOutputStream(filePath));
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			for (UserQr userQr : userQrList) {
				// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
				// 创建一个两列的表格
				PdfPTable headerTable = new PdfPTable(2);
				// 定义宽度比例
				headerTable.setWidthPercentage(95);
				
				// 创建图片对象,加入headerTable中,此处写入图片路径
				Image image = Image.getInstance(userQr.getQrPath());
				image.scaleAbsolute(360, 360);
				PdfPCell leftCell = new PdfPCell(image);
				leftCell.setBorder(Rectangle.NO_BORDER);
				leftCell.setUseAscender(true);
				leftCell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				// image.setBorderColor(Color.WHITE);
				headerTable.addCell(leftCell);
				// 方法一：使用Windows系统字体(TrueType)
				BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				
				// 方法二：使用iTextAsian.jar中的字体
				// BaseFont baseFont =
				// BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
				
				// 方法三：使用资源字体(ClassPath)
				// //BaseFont baseFont =
				// BaseFont.createFont("/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
				Font font = new Font(baseFont);
				font.setSize(24F);
				font.setStyle(Font.BOLD);
				PdfPCell rightCell = new PdfPCell(new Paragraph("编号： "+userQr.getId() + "\n\n"+ "姓名： "+userQr.getName()+ "\n\n" + "年龄： " + userQr.getAge(), font));
				rightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				rightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				rightCell.setBorder(Rectangle.NO_BORDER);
				headerTable.addCell(rightCell);
				
				pdfDoc.add(headerTable);
			}

			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filePath;
		
	}

}
