package com.bgip.model.upload;

import java.util.List;

import com.bgip.model.upload.FilesBean;
import com.bgip.model.upload.FolderBean;

public class FolderResponse extends FolderBean{

	private static final long serialVersionUID = 5842793384730832688L;
	private List<FolderBean> folderList;
	private List<FilesBean> fileList;
	
	
	public List<FolderBean> getFolderList() {
		return folderList;
	}
	public void setFolderList(List<FolderBean> folderList) {
		this.folderList = folderList;
	}
	public List<FilesBean> getFileList() {
		return fileList;
	}
	public void setFileList(List<FilesBean> fileList) {
		this.fileList = fileList;
	}


	
}
