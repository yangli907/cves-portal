package models.environment;

import java.util.Date;

import models.AbstractData;

public class EnvironmentData extends AbstractData {
	public String name;
	public String desc;
	public int isCert;
	public int isActive;
	public int isInUse;
	public Date creationTime;
	public Date modificationTime;
	public String modifiedBy;
	public String versionNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getIsCert() {
		return isCert;
	}
	public void setIsCert(int isCert) {
		this.isCert = isCert;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIsInUse() {
		return isInUse;
	}
	public void setIsInUse(int isInUse) {
		this.isInUse = isInUse;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
}
