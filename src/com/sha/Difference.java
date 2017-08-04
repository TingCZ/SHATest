package com.sha;



public class Difference {
	private String _binPath;
	private String _modifiedDateTime;
	private long _size;
	private String _status;
	
	public Difference(String binPath, String modifiedDateTime, long size, String status) {
		_binPath = binPath;
		_modifiedDateTime = modifiedDateTime;
		_size = size;
		_status = status;
	}
	   
	public String getBinPath() {
		return _binPath;
	}
	public void setBinPath(String _binPath) {
		this._binPath = _binPath;
	}
	public String getModifiedDateTime() {
		return _modifiedDateTime;
	}
	public void setModifiedDateTime(String _modifiedDateTime) {
		this._modifiedDateTime = _modifiedDateTime;
	}
	public long getSize() {
		return _size;
	}
	public void setSize(long _size) {
		this._size = _size;
	}
	public String getStatus() {
		return _status;
	}
	public void setStatus(String status) {
		this._status = status;
	}
}
