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
	 * ��������
	 */
		public int getRowCount() {
			return sts.size();
		}


		public int getColumnCount() {
			return 4;
		}

	/**
	 * ����ÿ�е�����
	 */
		public String getColumnName(int columnIndex) {
			if(columnIndex==0){		
				String u="���";
				return u;
			}else if(columnIndex==1){
				String pwd="�û���";
				return pwd;
			}else if(columnIndex==2){			
			String ip="IP";
			return ip;
			}else if(columnIndex==3){
				return "����";
			}
			return null;
		}

	/**
	 * ��ȡÿ�еı�ͷ
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
	 * �жϵ�Ԫ���Ƿ������༭
	 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

	/**��ȡ��Ӧ��Ԫ�������*/
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}

	/**
	 * ���ö�Ӧ��Ԫ�������
	 */
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
		}

	/**
	 * ����Ԫ����Ӽ������ķ���
	 */
		public void addTableModelListener(TableModelListener l) {
			
		}

		public void removeTableModelListener(TableModelListener l) {
			
		}

}
