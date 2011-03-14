package javacommon.base.model;

public class EnumModel {
	private String enumKey;
	private String value;
	private String id;
	
	public EnumModel(String key){
		this.enumKey = key;
	}
	
	public String getEnumKey() {
		return enumKey;
	}
	public void setEnumKey(String enumKey) {
		this.enumKey = enumKey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
