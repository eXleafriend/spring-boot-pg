package com.example.springbootpg.kafka;

public class StorageRequest extends Request {

	private final String storageId;

	public StorageRequest(String systemId, String storageId) {
		super(systemId);
		this.storageId = storageId;
	}

	public String getStorageId() {
		return storageId;
	}

}
