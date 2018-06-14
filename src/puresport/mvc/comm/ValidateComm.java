/**
 * PureSport
 * create by zw at 2018年6月14日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.util.Arrays;
import java.util.List;

import puresport.constant.EnumTypeLevel;
import puresport.mvc.t6mgrahr.T6MgrSession;

/**
 * @author zw
 *
 */
public class ValidateComm {

	private static List<String>  crdt_typeList = Arrays.asList("身份证", "护照","军官证","注册证");
	private static List<String>  gndList = Arrays.asList("男", "女");
	private static List<String>  typeleveList = Arrays.asList("国家级", "省级","市级");
	public static boolean v_column_crdt_tp(String nm){
		if (crdt_typeList.contains(nm)) {
			return true;
		}
		return false;
	}
	public static boolean inv_column_crdt_tp(String nm){
		return !v_column_crdt_tp(nm);
	}
	
	public static boolean v_column_gnd(String gnd){
		if (gndList.contains(gnd)) {
			return true;
		}
		return false;
	}
	
	public static boolean inv_column_gnd(String gnd){
		return !v_column_gnd(gnd);
	}
	
	public static boolean v_column_typeleve(T6MgrSession mgrSession, String column_typeleve){
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			if (column_typeleve.equals(EnumTypeLevel.Country.getName())
					|| column_typeleve.equals(EnumTypeLevel.Province.getName())) {
				return true;
			}
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			if (column_typeleve.equals(EnumTypeLevel.City.getName())) {
				return true;
			}
		} 
		return false;
	}
	
	public static boolean inv_column_typeleve(T6MgrSession mgrSession, String column_typeleve){
		return !v_column_typeleve(mgrSession, column_typeleve);
	}
	
	public static void main(String[] args) {
		System.out.println(inv_column_crdt_tp(new String("身份证")));
	}
}
