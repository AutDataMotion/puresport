/**
 * PureSport
 * create by zw at 2018年5月1日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import puresport.constant.EnumStatus;

/**
 * @author zw
 *
 */
public class ResTips implements Serializable{
	private boolean isSuc;
	private TipMdl status = new TipMdl();
	private List<TipMdl> fieldErrors = new ArrayList<>();
	
	public ResTips(){}
	
	public ResTips(EnumStatus astatus){
		isSuc = astatus == EnumStatus.Success?true:false;
		status.setName(astatus.getName());
	}
	 
	public ResTips addErroFiled(String name, String status){
		fieldErrors.add(new TipMdl(name, status));
		return this;
	}
	
	
	public static ResTips getSuccRes() {
		return new ResTips(EnumStatus.Success);
	}
	
	public static ResTips getFailRes() {
		return new ResTips(EnumStatus.Failed);
	}
	
	public static ResTips newSimpleTips(String status, String tips) {
		return new ResTips().setStatus(new TipMdl(status, tips));
	}
	
	/**
	 * @return the status
	 */
	public TipMdl getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public ResTips setStatus(TipMdl status) {
		this.status = status;
		return this;
	}

	/**
	 * @return the fieldErrors
	 */
	public List<TipMdl> getFieldErrors() {
		return fieldErrors;
	}



	/**
	 * @param fieldErrors the fieldErrors to set
	 */
	public void setFieldErrors(List<TipMdl> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}



	public static class TipMdl implements Serializable{
		private String name;
		private String status;
		
		public TipMdl(){}
		
		public TipMdl(String name, String status){
			this.name = name;
			this.status = status;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		
		
	}
}
