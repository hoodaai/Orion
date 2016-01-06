package com.utility.xsl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MimeUploadParser {

	List objFileList = null;
	
	public void mimeRequestProcessor(HttpServletRequest objInRequest) 
				throws Exception,FileUploadException{
		
		//Check if MIME type
		// Check that we have a file upload request
		if(objInRequest==null||!ServletFileUpload.isMultipartContent(objInRequest)){
			throw new Exception("Invalid Request");
		}
		
		objFileList = new ArrayList();
		
		// Create a factory for disk-based file items
		FileItemFactory objFileItemFactory  = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload objServletFileUpoad= new ServletFileUpload(objFileItemFactory);
		// Parse the request and set it into object
		this.setObjFileList((List)objServletFileUpoad.parseRequest(objInRequest));
		
	}

	public List getObjFileList() {
		return objFileList;
	}

	public void setObjFileList(List objFileList) {
		this.objFileList = objFileList;
	}
	
}