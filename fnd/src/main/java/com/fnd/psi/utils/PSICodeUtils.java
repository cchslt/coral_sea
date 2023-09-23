package com.fnd.psi.utils;


import com.fnd.psi.constant.PSICodeHeaderConstant;

/**
 * @Author: Skipper
 * @Date: 2022/2/11/011 1:56
 * @Desc: 获取业务编码工具类
 * @See:
 */
public class PSICodeUtils {


    /**
     * 随机获取仓库编码
     * @return
     */
    public static String  getWarehouseCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.WAREHOUSE_CODE_HEADER);
    }

    /**
     * 随机获取供应商编码
     * @return
     */
    public static String  getSupplierCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.SUPPLIER_CODE_HEADER);
    }
    /**
     * 随机获取入库单编码
     * @return
     */
    public static String  getStorageOrderCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.STORAGE_ORDER_CODE_HEADER);
    }
    /**
     * 随机获取销售单编码
     * @return
     */
    public static String  getSalesOrderCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.SALES_ORDER_CODE_HEADER);
    }
    /**
     * 随机获取采购单编码
     * @return
     */
    public static String  getPurchaseOrderCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.PURCHASE_ORDER_CODE_HEADER);
    }
    /**
     * 随机获取出库单编码
     * @return
     */
    public static String  getOutboundOrderCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.OUTBOUND_ORDER_CODE_HEADER);
    }
    /**
     * 随机获取调拨单编码
     * @return
     */
    public static String  getTransferringOrderCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.TRANSFERRING_ORDER_CODE_HEADER);
    }
    /**
     * 随机获取SPU编码
     * @return
     */
    public static String  getSPUCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.SPU_CODE_HEADER);
    }
    /**
     * 随机获取SKU编码
     * @return
     */
    public static String  getSKUCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.SKU_CODE_HEADER);
    }

    /**
     * 随机获取单品编码
     * @return
     */
    public static String  getSIMCode(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.PRODUCT_SINGLE_ITEM_CODE_HEADER);
    }

    /**
     * 随机获取批次号
     * @return
     */
    public static String  getWareBatchNumber(){
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.WARE_BATCH_NUMBER);
    }

    /**
     * 随机获取期初库存导入批次号
     *
     * @return
     */
    public static String getOpeningImportWareBatchNumber() {
        return PsiNumberingRuleUtil.getStrSnowCode();
    }

    /**
     * 随机获取客户编码
     * @return
     */
    public static String getCustomerNumber() {
        return PsiNumberingRuleUtil.getCodeByHeader(PSICodeHeaderConstant.CUSTOMER_NUMBER);
    }


}
