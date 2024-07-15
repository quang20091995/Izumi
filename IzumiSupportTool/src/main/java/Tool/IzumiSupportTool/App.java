package Tool.IzumiSupportTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App 
{	
	private static final int NO_INDEX = 0;
	private static final int ITEM_NAME_INDEX = 2;
	private static final int ITEM_ID_INDEX = 14;
	private static final int PRIMARY_KEY_INDEX = 26;
	private static final int DATA_TYPE_INDEX = 36;
	private static final int DATA_LENGTH_INDEX = 42;
	private static final int DECIMAL_INDEX = 45;
	private static final int DEFAULT_VALUE_INDEX = 47;
	private static final int IS_NOT_NULL_INDEX = 52;
	private static final int DESCRIPTION_INDEX = 57;
	private static final int MODE_NORMAL = 0;
	private static final int MODE_JAPANESE = 1;
	
	private static final String DATA_TYPE_NUMERIC = "numeric";
	private static final String DATA_TYPE_CHAR = "char";
	private static final String DATA_TYPE_VARCHAR = "varchar";
	
    public static void main( String[] args ) throws IOException
    {
    	List<ExcelObject> params = readExcel();
        
        createMaxData(params);
    }
    
    public static List<ExcelObject> readExcel() throws IOException {
    	List<ExcelObject> results = new ArrayList<ExcelObject>();
    	
    	FileInputStream file = new FileInputStream(new File("D:\\Izumi\\DB項目定義書_業務共通.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        
        XSSFSheet sheet = workbook.getSheet("汎用マスタ");
        
        DataFormat fmt = workbook.createDataFormat();
        CellStyle textStyle = workbook.createCellStyle();
        textStyle.setDataFormat(fmt.getFormat("@"));
      
        for (int i = 22; i < sheet.getLastRowNum(); i++) {
        	
        	if(sheet.getRow(i) != null) {
        		Cell noCell = sheet.getRow(i).getCell(NO_INDEX);

        		Cell itemNameCell = sheet.getRow(i).getCell(ITEM_NAME_INDEX);

        		Cell itemIdCell = sheet.getRow(i).getCell(ITEM_ID_INDEX);

        		Cell primaryKeyCell = sheet.getRow(i).getCell(PRIMARY_KEY_INDEX);

        		Cell dataTypeCell = sheet.getRow(i).getCell(DATA_TYPE_INDEX);

        		Cell dataLengthCell = sheet.getRow(i).getCell(DATA_LENGTH_INDEX);

        		Cell decimalCell = sheet.getRow(i).getCell(DECIMAL_INDEX);

        		Cell defaultValueCell = sheet.getRow(i).getCell(DEFAULT_VALUE_INDEX);

        		Cell isNotNullCell = sheet.getRow(i).getCell(IS_NOT_NULL_INDEX);

        		Cell descriptionCell = sheet.getRow(i).getCell(DESCRIPTION_INDEX);

        		
        		ExcelObject obj = new ExcelObject();
        		
        		if (noCell != null && noCell.getCellType() == CellType.BLANK) {
        			break;
        		}
        		
        		int no = 0;
    			if (noCell.getCellType() == CellType.NUMERIC) {
    				no = (int) noCell.getNumericCellValue();
    			} else {
    				no = Integer.valueOf(noCell.getStringCellValue());
    			}
        		
        		obj.setNo(no);
        		
        		obj.setItemName(itemNameCell.getStringCellValue());
        		obj.setItemId(itemIdCell.getStringCellValue());
        		if (primaryKeyCell != null && primaryKeyCell.getCellType() != CellType.BLANK) {
        			obj.setPrimaryKey(true);
        		}
        		
        		obj.setDataType(dataTypeCell.getStringCellValue());
        		
        		if (dataLengthCell != null && dataLengthCell.getCellType() != CellType.BLANK) {

        			int dataLength = 0;
        			if (dataLengthCell.getCellType() == CellType.NUMERIC) {
        				dataLength = (int) dataLengthCell.getNumericCellValue();
        			} else {
        				dataLength = Integer.valueOf(dataLengthCell.getStringCellValue());
        			}
        			obj.setDataLength(dataLength);
        		}
        		
        		if (decimalCell != null && decimalCell.getCellType() != CellType.BLANK) {
        			int decimal = 0;
        			if (decimalCell.getCellType() == CellType.NUMERIC) {
        				decimal = (int) decimalCell.getNumericCellValue();
        			} else {
        				decimal = Integer.valueOf(decimalCell.getStringCellValue());
        			}
        			obj.setDecimal(decimal);
        		}
        		
        		obj.setDefaultValue(defaultValueCell.getStringCellValue());
        		
        		if(isNotNullCell.getStringCellValue().equals("NOT NULL")) {
        			obj.setNotNull(true);
        		}
        		
        		obj.setDescription(descriptionCell.getStringCellValue());
        		results.add(obj);
        	}
		}
		return results;
    }
    
    public static void createMaxData(List<ExcelObject> excelObjects) {
    	for (ExcelObject item : excelObjects) {
    		String result = "";
    		if (item.getDataType().equals(DATA_TYPE_CHAR)) {
    			result = getSaltString(item.getDataLength(), MODE_NORMAL);
    		} else if (item.getDataType().equals(DATA_TYPE_VARCHAR)) {
    			result = getSaltString(item.getDataLength(), MODE_JAPANESE);
    		} else if (item.getDataType().equals(DATA_TYPE_NUMERIC)) {
    			result = getNumber(item.getDataLength(), item.getDecimal());
    		}
    		System.out.println(result);
    	}
    }
    
    private static String getSaltString(int length, int mode) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        
        if (mode == MODE_JAPANESE) {
        	SALTCHARS = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよをん";
        }
        
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    private static String getNumber(int length, int decimal) {
    	StringBuilder result = new StringBuilder();
    	for (int i = 0; i < length; i++) {
    		result.append("9");
		}
    	
    	if (decimal != 0) {
    		result.insert(length - decimal, ",");
    	}
    	return result.toString();
    }
}
