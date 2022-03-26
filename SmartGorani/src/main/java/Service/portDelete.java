package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inter.Command;

public class portDelete implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

				    int result = 0;
				    String checkNum = "";

				    for(String str : checkBoxArr){
				    checkNum = str;
				    archiveFolder.setFolderSeq(checkNum);
				    folderService.archiveFolderDelete(archiveFolder);
				    PrintWriter out = response.getWriter();
				    
				    out.print(result);
				    
				    }
				    
		
		return null;
	}

}
