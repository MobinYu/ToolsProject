package com.yu.toolMethod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.aspose.words.CellMerge;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataTable;
import com.yu.model.Group;
import com.yu.model.GroupMember;

public class ExportWord {

	public static void main(String[] args) {
		creatDocFile();
	}
	
	public static void creatDocFile (){
		String tempUrl = getTempPath();
		
		ByteArrayOutputStream dstStream = null;
		OutputStream output = null;
		
		try {
			Document doc = new Document(tempUrl);
			DocumentBuilder builder = new DocumentBuilder(doc);
			
			int saveFormat = SaveFormat.DOCX;
			
			Group group = new Group("1001", "工作组l", "每日测试", "张三", "87954645", "一级", "2018-01-01", "测试工作组1");
			
			doc.getRange().replace("$groupName$", StringUtils.isNotBlank(group.getName()) ? group.getName() : "", true, false);
			doc.getRange().replace("$duty$", StringUtils.isNotBlank(group.getDuty()) ? group.getDuty() : "", true, false);
			doc.getRange().replace("$leader$", StringUtils.isNotBlank(group.getLeader()) ? group.getLeader() : "", true, false);
			doc.getRange().replace("$identifier$", StringUtils.isNotBlank(group.getIdentifier()) ? group.getIdentifier() : "", true, false);
			doc.getRange().replace("$level$", StringUtils.isNotBlank(group.getLevel()) ? group.getLevel() : "", true, false);
			doc.getRange().replace("$foundingTime$", StringUtils.isNotBlank(group.getFoundingTime()) ? group.getFoundingTime() : "", true, false);
			doc.getRange().replace("$introduction$", StringUtils.isNotBlank(group.getIntroduction()) ? group.getIntroduction() : "", true, false);
			
			List<GroupMember> memberList = new ArrayList<GroupMember>();
			DataTable memberTable = new DataTable("visitPersonList");
			memberTable.getColumns().add("memberInfo");
			memberTable.getColumns().add("name");
			memberTable.getColumns().add("sex");
			memberTable.getColumns().add("IDNum");
			memberTable.getColumns().add("phoneNum");
			
			GroupMember member1 = new GroupMember("100101", "1001", "李四", "男", "32435684848545485454", "15566666666");
			memberList.add(member1);
			
			GroupMember member2 = new GroupMember("100102", "1001", "王五", "男", "32435684848545483333", "15533333333");
			memberList.add(member2);
			
			for (int i = 0; i < memberList.size(); i++) {
				GroupMember member = memberList.get(i);
				DataRow row = memberTable.newRow();
				row.set(0, "组内成员");
				row.set(1, member.getName());
				row.set(2, member.getSex());
				row.set(3, member.getIDNumber());
				row.set(4, member.getPhoneNo());
				memberTable.getRows().add(row);
			}
			
			doc.getMailMerge().executeWithRegions(memberTable);
			
			//合并单元格
			builder.moveToCell(0, 5, 0, 0);
			builder.getCellFormat().setVerticalMerge(CellMerge.FIRST);
			if (memberList.size() == 0) {
				builder.moveToCell(0, 6, 0, 0);
			    builder.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
			} else {
				for(int i = 1; i < memberList.size() + 1; i++ ) {
					builder.moveToCell(0, 5 + i, 0, 0);
					builder.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
				}
			}
			
			dstStream = new ByteArrayOutputStream();
			doc.save(dstStream, saveFormat);
			
			//将doc文件写入指定磁盘位置
			output = new FileOutputStream(getOutFile());
			output.write(dstStream.toByteArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null){
					output.flush();
					output.close();
				}
				
				if (dstStream != null) {
					dstStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	private static String getTempPath() {
		String tempUrl = "";
		URL url = ExportWord.class.getClassLoader().getResource("file/docTemplate.docx");
		
		if (url != null && "/".equals(url.getPath().substring(0, 1))){
			tempUrl = url.getPath().substring(1,url.getPath().length());
		}
		return tempUrl;
	}
	
	private static File getOutFile() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		
		String path = "D:/DemoMVC/tempFile/tempFile_" + date + ".docx";
		File outFile = new File(path);
		return outFile;
	}
}
