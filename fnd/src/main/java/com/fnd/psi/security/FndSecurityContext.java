package com.fnd.psi.security;

import com.fnd.psi.dto.user.PsiUserInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@ApiModel
@ToString(callSuper = true)
@Slf4j
@NoArgsConstructor
public class FndSecurityContext implements Serializable {

	private static final long serialVersionUID = 1799342847607655831L;
	/**
	 * id
	 */
	@ApiModelProperty("id")
	private Long id;

	/**
	 * 登录名
	 */
	@ApiModelProperty("登录名")
	private String username;

	
	@ApiModelProperty("user")
	private PsiUserInfoDTO psiUserInfoDTO;


}
