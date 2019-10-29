/**
 * PureSport
 * create by zw at 2018年5月1日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import puresport.constant.EnumStatus;

/**
 * @author zw
 *
 */
public class ResTips implements Serializable{
	private boolean isSuc;
	private TipMdl status = new TipMdl();
	private List<TipMdl> fieldErrors;
	private List<String> tipStrings;
	
	public ResTips(){}
	
	public ResTips(EnumStatus astatus){
		isSuc = astatus == EnumStatus.Success?true:false;
		status.setName(astatus.getName());
	}
	
	public ResTips(EnumStatus astatus, List<String> tips){
		isSuc = astatus == EnumStatus.Success?true:false;
		tipStrings = tips;
	}
	 
	public ResTips addErroFiled(String name, String status){
		if (CollectionUtils.isEmpty(fieldErrors)) {
			fieldErrors = new LinkedList<ResTips.TipMdl>();
		}
		fieldErrors.add(new TipMdl(name, status));
		return this;
	}
	
	
	public static ResTips getSuccRes() {
		return new ResTips(EnumStatus.Success);
	}
	
	public static ResTips getFailRes() {
		return new ResTips(EnumStatus.Failed);
	}
	
	public static ResTips getFailRes(String ... tips) {
		ResTips tip = new ResTips(EnumStatus.Failed);
		if (Objects.nonNull(tips) && tips.length > 0) {
			tip.tipStrings = new LinkedList<>();
			for (int i = 0; i < tips.length; i++) {
				tip.tipStrings.add(tips[i]);
			}
		}
		
		return tip;
	}
	
	public static ResTips getIllegalRes() {
		return new ResTips(EnumStatus.Illegal);
	}
	
	public static ResTips getFailRes(List<String> tips) {
		return new ResTips(EnumStatus.Failed, tips);
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



	public List<String> getTipStrings() {
		return tipStrings;
	}

	public void setTipStrings(List<String> tipStrings) {
		this.tipStrings = tipStrings;
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
