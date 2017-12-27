package com.bgip.dao;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import com.bgip.constants.BgipConstants;
import com.bgip.constants.StatusCodes;
import com.bgip.exception.BgipException;
import com.bgip.model.ResponseBean;
import com.bgip.model.upload.FilesBean;
import com.bgip.model.upload.FolderBean;
import com.bgip.model.upload.FolderResponse;
import com.bgip.mongo.MongoManager;

import org.slf4j.Logger;

public class TrashDAO extends BaseDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TrashDAO.class);

	@Autowired
	private MongoManager mongoManager;
	
	
	@Autowired
	StatusCodes statusCodes;
	
	
	
	public ResponseBean createTrashFolder(String folderId) throws Exception {
		ResponseBean response = null;
		try{
			FolderBean folderFromDB= mongoManager.getObjectByID(com.bgip.constants.BgipConstants.FOLDER_COLLECTION, folderId, FolderBean.class);
		if( folderFromDB != null){
			
		 mongoManager.updateByObjectId(com.bgip.constants.BgipConstants.FOLDER_COLLECTION,"_id", new ObjectId(folderFromDB.getId()), "trash", true);
		 response = new ResponseBean(StatusCodes.SUCCESS_MESSAGE);
			}
		} catch (Exception e) {
			log.error("Error in Folder move to Trash", e);
			throw new BgipException(StatusCodes.NOT_FOUND, " Error in Folder deletion");
		}
		return response;
	}
	
	
	public ResponseBean restoreTrashFolder(String folderId) throws Exception {
		ResponseBean response = null;
		try{
			FolderBean folderFromDB= mongoManager.getObjectByID(com.bgip.constants.BgipConstants.FOLDER_COLLECTION, folderId, FolderBean.class);
		if( folderFromDB != null){
			
		 mongoManager.updateByObjectId(com.bgip.constants.BgipConstants.FOLDER_COLLECTION,"_id", new ObjectId(folderFromDB.getId()), "trash", false);
		 response = new ResponseBean(StatusCodes.SUCCESS_MESSAGE);
			}
		} catch (Exception e) {
			log.error("Error in Folder Restore from Trash", e);
			throw new BgipException(StatusCodes.NOT_FOUND, " Error in Folder Restore");
		}
		return response;
	}
	
	
	
	
	public ResponseBean fileMoveToTrash(String fileId) throws Exception {
		ResponseBean response = null;
		try{
			FilesBean fileFromDB= mongoManager.getObjectByID(com.bgip.constants.BgipConstants.FILES_COLLECTION, fileId, FilesBean.class);
		if( fileFromDB != null){
			
		 mongoManager.updateByObjectId(com.bgip.constants.BgipConstants.FILES_COLLECTION,"_id", new ObjectId(fileFromDB.getId()), "trash", true);
		 response = new ResponseBean(StatusCodes.SUCCESS_MESSAGE);
			}
		} catch (Exception e) {
			log.error("Error in File move to Trash", e);
			throw new BgipException(StatusCodes.NOT_FOUND, " Error in Folder deletion");
		}
		return response;
	}
	
	
	
	public ResponseBean fileRestore(String fileId) throws Exception {
		ResponseBean response = null;
		try{
			FilesBean fileFromDB= mongoManager.getObjectByID(com.bgip.constants.BgipConstants.FILES_COLLECTION, fileId, FilesBean.class);
		if( fileFromDB != null){
			
		 mongoManager.updateByObjectId(com.bgip.constants.BgipConstants.FILES_COLLECTION,"_id", new ObjectId(fileFromDB.getId()), "trash", false);
		 response = new ResponseBean(StatusCodes.SUCCESS_MESSAGE);
			}
		} catch (Exception e) {
			log.error("Error in File Restore from Trash", e);
			throw new BgipException(StatusCodes.NOT_FOUND, " Error in File Restore");
		}
		return response;
	}
	
	
	
	
//	public FolderResponse getFolderDetails(String folderId, String loginUser) throws Exception {
//		FolderResponse FolderFromDB = new FolderResponse();
//		if( folderId.equals("0")) {
//			FolderFromDB = getAllDriveFolders(loginUser);
//		}else {
//			 FolderFromDB = mongoManager.getObjectByField(com.bgip.constants.BgipConstants.FOLDER_COLLECTION, "id", folderId, FolderResponse.class);
//				
//				if( FolderFromDB ==null ) {
//					throw new BgipException(StatusCodes.NOT_FOUND, "  Error in getFolderDetails Api !! FolderId doesn't exist ");
//				}
//				FolderFromDB.setFolderList(getSubFolderList(folderId,loginUser ));
//				FolderFromDB.setFileList(getFilesByFolderId(folderId,loginUser ));
//		}
//		
//		return FolderFromDB;
//	}
//	
	
	public List<FolderBean> getSubFolderList(String folderId, String loginUser) throws Exception {
		List<FolderBean> folderList = new ArrayList<FolderBean>();
		try {
			folderList = mongoManager.getObjectsBy2Fields(com.bgip.constants.BgipConstants.FOLDER_COLLECTION,
					"parentFolderId", folderId, "trash", true, FolderBean.class);
			System.out.println("  folderId : "+folderId);
			System.out.println(" Foldr list : "+folderList);
			
//			folderList = mongoManager.getObjectsBy2Fields(com.bgip.constants.BgipConstants.FOLDER_COLLECTION, "parentFolderId", folderId,
//					"userName", loginUser, FolderBean.class);
			return folderList;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BgipException(StatusCodes.NOT_FOUND, " Error in getSubFolderList Api from TrashDAO ");
		}
		
	}

	
	
	public List<FilesBean> getFilesByFolderId(String folderId, String loginUser) throws Exception {
		try {
			
			return mongoManager.getObjectsBy2Fields(com.bgip.constants.BgipConstants.FILES_COLLECTION, "folderId", folderId, "trash", true, FilesBean.class);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new BgipException(StatusCodes.NOT_FOUND, " Error in getFilesByFolderId Api On Trash DAO ");
		}
	}
	
	
	
	
	
	
	
}
