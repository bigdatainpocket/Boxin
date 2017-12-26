package com.bgip.model.upload;

import java.util.List;

public class FolderRequest extends FolderBean{

	private static final long serialVersionUID = 8383086735600709935L;

	private List<FilesBean> fileList;

	public List<FilesBean> getFileList() {
		return fileList;
	}

	public void setFileList(List<FilesBean> fileList) {
		this.fileList = fileList;
	}


}
