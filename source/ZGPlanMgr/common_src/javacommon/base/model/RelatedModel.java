package javacommon.base.model;

public class RelatedModel {
	private String relatedTableName;
	private String relatedCuidName;
	private String relatedLabelName;
	private String value;
	private String id;
	private String relatedBmClassId;
	
	public RelatedModel(String relatedTableName,String relatedCuidName,String relatedLabelName){
		this.relatedCuidName = relatedCuidName;
		this.relatedLabelName = relatedLabelName;
		this.relatedTableName = relatedTableName;
	}
	
	public String getRelatedTableName() {
		return relatedTableName;
	}
	public void setRelatedTableName(String relatedTableName) {
		this.relatedTableName = relatedTableName;
	}
	public String getRelatedCuidName() {
		return relatedCuidName;
	}
	public void setRelatedCuidName(String relatedCuidName) {
		this.relatedCuidName = relatedCuidName;
	}
	public String getRelatedLabelName() {
		return relatedLabelName;
	}
	public void setRelatedLabelName(String relatedLabelName) {
		this.relatedLabelName = relatedLabelName;
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

	public String getRelatedBmClassId() {
		return relatedBmClassId;
	}

	public void setRelatedBmClassId(String relatedBmClassId) {
		this.relatedBmClassId = relatedBmClassId;
	}
}
