package ClientItem;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ClientTableMode implements TableModel{

	public int getRowCount() {
		return 1;
	}

	public int getColumnCount() {
		return 0;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if(columnIndex==0){		
			String u="ÓÃ»§Ãû";
			return u;
	}
		return null;
}

	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==0){
			return Integer.class;
		}
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

	public void addTableModelListener(TableModelListener l) {
		
	}

	public void removeTableModelListener(TableModelListener l) {
		
	}

}
