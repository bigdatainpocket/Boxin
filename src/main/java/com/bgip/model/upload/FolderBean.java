package com.bgip.model.upload;

import com.bgip.model.user.BaseBean;

public class FolderBean extends BaseBean {

	private static final long serialVersionUID = 7102548924381281877L;

	private String folderName;
	
	private String parentFolderId = "0";
	
	private String link;
	
	private boolean favourite;
	
	private boolean trash;

	private String bucketPublicId;
	
	
	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public String getBucketPublicId() {
		return bucketPublicId;
	}

	public void setBucketPublicId(String bucketPublicId) {
		this.bucketPublicId = bucketPublicId;
	}

	public String getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(String parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	

}
