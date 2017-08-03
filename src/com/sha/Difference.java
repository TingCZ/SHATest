package com.sha;



public class Difference {
	private String _binPath;
	private String _modifiedDateTime;
	private long _size;
	
	public Difference(String binPath, String modifiedDateTime, long size) {
		_binPath = binPath;
		_modifiedDateTime = modifiedDateTime;
		_size = size;
	}
	   
	public String get_binPath() {
		return _binPath;
	}
	public void set_binPath(String _binPath) {
		this._binPath = _binPath;
	}
	public String get_modifiedDateTime() {
		return _modifiedDateTime;
	}
	public void set_modifiedDateTime(String _modifiedDateTime) {
		this._modifiedDateTime = _modifiedDateTime;
	}
	public long get_size() {
		return _size;
	}
	public void set_size(long _size) {
		this._size = _size;
	}
}
