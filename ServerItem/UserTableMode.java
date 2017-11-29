package ServerItem;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class UserTableMode implements TableModel{

	List<SerThread> sts;
	public UserTableMode(List<SerThread> sts) {
		this.sts=sts;
	}

	/**
	 * 返回行数
	 */
		public int getRowCount() {
			return sts.size();
		}


		public int getColumnCount() {
			return 4;
		}

	/**
	 * 返回每列的名字
	 */
		public String getColumnName(int columnIndex) {
			if(columnIndex==0){		
				String u="编号";
				return u;
			}else if(columnIndex==1){
				String pwd="用户名";
				return pwd;
			}else if(columnIndex==2){			
			String ip="IP";
			return ip;
			}else if(columnIndex==3){
				return "操作";
			}
			return null;
		}

	/**
	 * 获取每列的表头
	 */
		public Class<?> getColumnClass(int columnIndex) {
			if(columnIndex==0){
				return Integer.class;
			}else if(columnIndex==1){
				return String.class;
			}else if(columnIndex==2){
				return String.class;
			}else if(columnIndex==3){
				return JComboBox.class;
			}
			return null;
		}

	/**
	 * 判断单元格是否允许被编辑
	 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

	/**获取对应单元格的数据*/
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}

	/**
	 * 设置对应单元格的数据
	 */
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
		}

	/**
	 * 给单元格添加监听器的方法
	 */
		public void addTableModelListener(TableModelListener l) {
			
		}

		public void removeTableModelListener(TableModelListener l) {
			
		}

}
