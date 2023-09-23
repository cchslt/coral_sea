package com.fnd.psi.utils;


import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Random;


/**
 * Function:
 * Date: 2018/12/6
 *
 * @author skipper
 * @desc
 * @see
 */
public class NumberingRuleUtil {


    private static final String[] code =new String[]{"A","B","C","D","E","F","G","H","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","1","2","3","4","5","6","7","8","9","0"};

    private static final String[] NumberCode =new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"};
    /**
	 * 根据编码头部编号
	 * @param codeHeader
	 * @return
	 */
	public static String newCodeByHeader(String codeHeader) {
		String rtn=NumberCode[DatetimeUtil.getYear()-2013]+NumberCode[DatetimeUtil.getMonth()]+NumberCode[DatetimeUtil.getDay()]+NumberCode[DatetimeUtil.getHour()]+ DatetimeUtil.getSecAndmsec()+
				getRandomStr(4);
		return rtn;
	}
	/**
	 * 生成随机数 并且以字符串形式返回
	 * @param i 需要的数字长度
	 * @return
	 */
	public  static String getRandomStr(int i){
		String randomStr = "";
		while(randomStr.length() < i) {
			randomStr += getRandomNumber();
		}
		return randomStr;
	}
	/**
	 * 生成订单编号
	 * @param userId
	 * @return 前缀+用户编号后3位+年份+月份编码+日期编码+小时编码+分秒+随机数
	 */
	public static String newOrdercode(String userId) {

		String userCode="";
		switch (userId.length()) {
		case 1:
			userCode="00"+userId;
			break;
		case 2:
			userCode="0"+userId;
			break;
		case 3:
			userCode=userId;
			break;

		default:
			userCode=userId.substring(userId.length()-3, userId.length());
			break;
		}
		String rtn=userCode+code[DatetimeUtil.getYear()-2013]+code[DatetimeUtil.getMonth()]+code[DatetimeUtil.getDay()]+code[DatetimeUtil.getHour()]+ DatetimeUtil.getMinAndSec()+(int)(Math.random()*10000);
		return rtn;
	}
    /**
     * 生成基础编号
     * @param codeHeader 前缀
     * @return 前缀+年份+月份编码+日期编码+小时编码+分秒+随机数
     */
    public static String newBaseCode(String codeHeader) {
        String rtn=codeHeader+ DatetimeUtil.getYear()+code[DatetimeUtil.getMonth()]+code[DatetimeUtil.getDay()]+code[DatetimeUtil.getHour()]+ DatetimeUtil.getSecAndmsec()
                +(int)(Math.random()*10000);
        return rtn;
    }
    /**
     * 生成自定义编号
     * @param codeHeader 前缀
     * @param codeCenter 中间码
     * @param codeEnd 后缀
     * @param ifNeedTime 是否需要日期时间编码，true返回 ：年份+月份编码+日期编码+小时编码+分秒+随机数
     * @return 前缀+中间码+随机数/时间码+后缀
     */
    public static String newBaseCode(String codeHeader,String codeCenter,String codeEnd,boolean ifNeedTime) {
        String unique="";
        if(ifNeedTime){
            unique= DatetimeUtil.getYear()+code[DatetimeUtil.getMonth()]+code[DatetimeUtil.getDay()]+code[DatetimeUtil.getHour()]+ DatetimeUtil.getMinAndSec()+(int)(Math.random()*10000);
        }
        
        String rtn=codeHeader+codeCenter+unique+codeEnd;
        return rtn;
    }
    
    /**
     * 生成自定义编号
     * @param codePrefix 前缀
     * @param codeCenter 中间码
     * @param codeSuffix 后缀
     * @param ifNeedTime 是否需要时间
     * @param randomLength 随机数位数
     * @return 前缀+日期时间+中间码+随机数+后缀
     */
    public static String newBaseCode(String codePrefix,boolean ifNeedTime,String codeCenter,int randomLength,String codeSuffix) {
        String datetimeString = "";
        if(ifNeedTime)
        {
            datetimeString = DatetimeUtil.getYear()+code[DatetimeUtil.getMonth()]+code[DatetimeUtil.getDay()]+code[DatetimeUtil.getHour()]+ DatetimeUtil.getMinAndSec();
        }
        String randomString = "";
        if(randomLength>0)
        {
            for (int i = 0; i < randomLength; i++) {
                randomString += getRandomNumber();
            }
        }
        codePrefix = StringUtils.isEmpty(codePrefix)?"":codePrefix;
        codeCenter = StringUtils.isEmpty(codeCenter)?"":codeCenter;
        codeSuffix = StringUtils.isEmpty(codeSuffix)?"":codeSuffix;
        return codePrefix + datetimeString + codeCenter + randomString + codeSuffix;
    }
    
    public static String getRandomNumber()
    {
        return new Double(Math.random()*10d).intValue()+"";
    }
    
    /**     * 生成自定义编号
     */
    public static String newBaseCode(String codeHeader,Long timeStamp) {
        Date date=new Date(timeStamp);
        String time= DatetimeUtil.getYear(date)+code[DatetimeUtil.getMonth(date)]+code[DatetimeUtil.getDay(date)]+code[DatetimeUtil.getHour(date)]+ DatetimeUtil.getMinAndSec(date)+(int)(Math.random()*10000);
        String rtn=codeHeader+time;
        return rtn;
    }


    /**
     *  根据长度获取指定长度的code
     * @param codeLength
     * @return
     */
    public static String createCodeByLength(int codeLength){
        int random = createRandomInt();
        return createCode(random, codeLength);
    }

    /**
     * 根据随机数和
     * @param random
     * @param len
     * @return
     */
    public static  String createCode(int random,int len){
        Random rd = new Random(random);
        final int  maxNum = 62;
        StringBuffer sb = new StringBuffer();
        int rdGet;//取得随机数
        int count=0;
        while(count < len){
            rdGet = Math.abs(rd.nextInt(maxNum));//生成的数最大为62-1
            if (rdGet >= 0 && rdGet < code.length) {
                sb.append(code[rdGet]);
                count ++;
            }
        }
        return sb.toString();
    }

    public static  int createRandomInt(){
        //得到0.0到1.0之间的数字，并扩大100000倍
        double temp = Math.random()*100000;
        //如果数据等于100000，则减少1
        if(temp>=100000){
            temp = 99999;
        }
        int tempint = (int)Math.ceil(temp);
        return tempint;
    }

    public static void main(String[] args) {
//        System.out.println(NumberingRuleUtil.newBaseCode("before", true, "center", 5, "after"));
//        System.out.println(newCodeByHeader("P"));
        System.out.println(createCodeByLength(6));

    }
}
