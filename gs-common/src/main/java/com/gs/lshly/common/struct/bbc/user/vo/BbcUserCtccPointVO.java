package com.gs.lshly.common.struct.bbc.user.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用户电信积分
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午11:45:16
 */
public abstract class BbcUserCtccPointVO implements Serializable {

	@Data
	@ApiModel("BbcUserCtccPointVO.DetailVO")
	public static class DetailVO implements Serializable {

		/**
		 * id
		 */
		private String id;

		/**
		 * 会员ID
		 */
		private String userId;

		/**
		 * 积分账户标识
		 */
		private Long pointAcctId;

		/**
		 * 客户标识
		 */
		private Long custId;

		/**
		 * 积分余额
		 */
		private Long pointBalance;

		/**
		 * 年末到期积分
		 */
		private Long yearBalance;

		/**
		 * 积分类型组编码
		 */
		private String pointTypeNum;
	}
}
