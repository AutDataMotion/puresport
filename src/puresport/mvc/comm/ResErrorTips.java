/**
 * PureSport
 * create by zw at 2018年5月1日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zw
 *
 */
public class ResErrorTips implements Serializable{
	private List<TipMdl> fieldErrors = new ArrayList<>();
	
	public ResErrorTips(){}
	
	public ResErrorTips addErroFiled(String name, String status){
		fieldErrors.add(new TipMdl(name, status));
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
