package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.mapper.PsiInventoryMapper;
import com.fnd.psi.model.PsiInventory;
import com.fnd.psi.service.PsiInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 14:50
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiInventoryService")
public class PsiInventoryServiceImpl extends ServiceImpl<PsiInventoryMapper, PsiInventory> implements PsiInventoryService {



}
