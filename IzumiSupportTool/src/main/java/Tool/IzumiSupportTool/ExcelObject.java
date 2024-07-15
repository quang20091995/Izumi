package Tool.IzumiSupportTool;

public class ExcelObject {

	public int no;
	public String itemName = "";
	public String itemId = "";
	public Boolean isPrimaryKey = false;
	public String dataType;
	public int dataLength;
	public int decimal;
	public String defaultValue;
	public Boolean isNotNull = false;
	public String description = "";
	
	public ExcelObject() {
		super();
	}
	
	public ExcelObject(int no, String itemName, Boolean isPrimaryKey, String dataType, int dataLength, int decimal,
			String defaultValue, Boolean isNotNull, String description) {
		super();
		this.no = no;
		this.itemName = itemName;
		this.isPrimaryKey = isPrimaryKey;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.decimal = decimal;
		this.defaultValue = defaultValue;
		this.isNotNull = isNotNull;
		this.description = description;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getDataLength() {
		return dataLength;
	}
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	public int getDecimal() {
		return decimal;
	}
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Boolean getIsNotNull() {
		return isNotNull;
	}
	public void setNotNull(Boolean isNotNull) {
		this.isNotNull = isNotNull;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ExcelObject [no=" + no + ", itemName=" + itemName + ", itemId=" + itemId + ", isPrimaryKey=" + isPrimaryKey
				+ ", dataType=" + dataType + ", dataLength=" + dataLength + ", decimal=" + decimal + ", defaultValue="
				+ defaultValue + ", isNotNull=" + isNotNull + ", description=" + description + "]";
	}
}
