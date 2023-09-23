package com.fnd.psi.constant;

/**
 * @Date: 2022/2/10/010 21:50
 * @Desc: 定义PSI系统中各类编码头部
 * SKU   	Stock Keeping Unit
 * SPU   	Standard product unit
 * SIM      Product Single Item  单品
 * SPR   	Supplier   供应商
 * WHS  	Warehouse  仓库
 * STO   	Storage  Order   入库单
 * SLO      Sales   Order   销售单
 * PHO	    Purchase Order  采购单
 * OBO	    Outbound Order  出库单
 * TFO	    Transferring Order 调拨单
 * @See:
 */
public interface PSICodeHeaderConstant {

    final static String SKU_CODE_HEADER = "SKU";
    /**
     *
     */
    final static String SPU_CODE_HEADER = "SPU";
    /**
     *
     */
    final static String PRODUCT_SINGLE_ITEM_CODE_HEADER = "SIM";
    /**
     * 供应商编码头
     */
    final static String SUPPLIER_CODE_HEADER = "SPR";
    /**
     * 仓库
     */
    final static String WAREHOUSE_CODE_HEADER = "WHS";
    /**
     * 入库单
     */
    final static String STORAGE_ORDER_CODE_HEADER = "STO";
    /**
     * 销售单
     */
    final static String SALES_ORDER_CODE_HEADER = "SLO";
    /**
     * 采购单
     */
    final static String PURCHASE_ORDER_CODE_HEADER = "PHO";
    /**
     * 出库单
     */
    final static String OUTBOUND_ORDER_CODE_HEADER = "OBO";
	/**
	 * 调拨单
	 */
	final static String TRANSFERRING_ORDER_CODE_HEADER = "TFO";
	/**
	 * 批次号
	 */
	final static String WARE_BATCH_NUMBER = "BTN";

	/**
	 * 客户编号头
	 */
	final static String CUSTOMER_NUMBER = "CSM";

	/**
	 * 期初库存导入批次号
	 */
	final static String OPENING_IMPORT_WARE_BATCH_NUMBER = "OIMPB";

}
