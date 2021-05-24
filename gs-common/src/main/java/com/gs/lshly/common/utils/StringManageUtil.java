package com.gs.lshly.common.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 字段类型转化
 *
 * 
 * @author yingjun
 * @date 2019年6月13日 上午9:01:59
 */
public class StringManageUtil {
    /**
     * 截断传入字符串为指定长度的字符串，最后加省略号
     * 
     * @param stringSize
     * @param content
     *            String
     * @return String
     */
    public static String limitStringSize(int stringSize, String content) {
        if (content == null || "".equals(content)) {
            return "";
        }
        content = clearHtmlTag(content);
        content = content.trim();
        if (content.length() > stringSize && stringSize > 0) {
            content = content.substring(0, stringSize) + "..";
        }

        return content;
    }

    public static String clearSensitiveWord(String key) {
        if (key == null) {
            return key;
        }
        String keyword = key;
        keyword = keyword.replace(">", "&gt;").replace("<", "&lt;");
        keyword = keyword.replace("\"", "").replace("'", "").replace("%", "");

        return keyword;
    }

    /**
     * 截取字符串，后面不带省略号
     * 
     * @param stringSize
     * @param content
     * @return String
     */
    public static String limitStringNoTail(int stringSize, String content) {

        if (content == null || "".equals(content)) {
            return "";
        }
        content = clearHtmlTag(content);
        content = content.trim();
        if (content.length() > stringSize && stringSize > 0) {
            content = content.substring(0, stringSize);
        }

        return content;
    }

    /**
     * 清楚html标签
     * 
     * @param s
     *            String
     * @return String
     */
    public static String clearHtmlTag(String s) {
        try {
            if (s == null || s.length() == 0) {
                return "";
            }
            s = Pattern
                    .compile(
                            "<(.*?)>",
                            Pattern.DOTALL | Pattern.MULTILINE
                                    | Pattern.CASE_INSENSITIVE).matcher(s)
                    .replaceAll("");
            s = s.replaceAll("&nbsp;", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    
    public static String formatStrFromInt(Integer digital, Integer len) {
        String str = "000000000000000";
        String intStr = digital + "";
        if (intStr.length() <= len) {
            intStr = str.substring(0, len - intStr.length()) + intStr;
        } else {
            intStr = intStr.substring(intStr.length() - len);
        }

        return intStr;
    }

    public static Integer[] convertString2IntegerArr(String str) {
        if (str == null) {
            return null;
        }

        List<Integer> list = new ArrayList<Integer>();
        String[] arr = str.split(",");
        for (String s : arr) {
            if ("".equals(s.trim())) {
                continue;
            }
            if (list.contains(Integer.parseInt(s))) {
                continue;
            }
            list.add(Integer.valueOf(s));
        }

        return list.toArray(new Integer[list.size()]);
    }

    public static String genRandomCode(int length) {
        Random randGen = new Random();
        char[] numbersAndLetters = ("0123456789" + "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen
                    .nextInt(numbersAndLetters.length)];
        }
        return new String(randBuffer);
    }

    public static String genRandomNumber(int length) {
        Random randGen = new Random();
        char[] numbersAndLetters = ("0123456789876543210").toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen
                    .nextInt(numbersAndLetters.length)];
        }

        return new String(randBuffer);
    }

    public static String patternPrint(Integer x) {
        DecimalFormat df = new DecimalFormat("########");
        return df.format(x);
    }

    public static String patternPrint(double x) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(x);
    }

    public static String patternPrint(double x, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(x);
    }

    public static String getTime(Integer seconds) {
        String times = "";
        if (seconds / 60 < 10) {
            times += "0" + seconds / 60 + ":";
        } else {
            times += seconds / 60 + ":";
        }

        if (seconds % 60 == 0) {
            times += "00";
        } else if (seconds % 60 < 10) {
            times += "0" + seconds % 60;
        } else {
            times += seconds % 60;
        }

        return times;
    }

    public static String getFileSize(Integer fileSize) {
        String size = "";
        if (fileSize > 1024 * 1024) {
            size += patternPrint(fileSize / (1024 * 1024), "#.##") + "M";
        } else if (fileSize > 1024) {
            size += patternPrint(fileSize / 1024, "#.##") + "K";
        }

        return size;
    }

    public static String getUserAgent(HttpServletRequest req) {
        if (req == null) {
            return null;
        }
        Enumeration<?> e = req.getHeaders("User-Agent");
        if (e == null) {
            return null;
        }
        String clientDevice = "";
        if (e != null && e.hasMoreElements()) {
            clientDevice += e.nextElement();
        }
        if (clientDevice.length() > 150) {
            clientDevice = clientDevice.substring(0, 150);
        }
        if (clientDevice.indexOf("app_android") >= 0) {
            clientDevice = "app_android";
        } else if (clientDevice.indexOf("app_ios") >= 0) {
            clientDevice = "app_ios";
        } else if (clientDevice.indexOf("Mozilla") >= 0
                && clientDevice.indexOf("Linux") == -1) {
            clientDevice = "web_pc";
        } else {
            clientDevice = "web_others";
        }

        return clientDevice;
    }

    public static String getChannelName(String channelName) {
        if (channelName==null||"".equals(channelName)) {
            return "PC浏览器";
        }
        if ("web_pc".equals(channelName)) {
            channelName = "PC浏览器";
        } else if ("web_others".equals(channelName)) {
            channelName = "其他浏览器";
        } else if ("app_android".equals(channelName)) {
            channelName = "Andorid手机App";
        } else if ("app_ios".equals(channelName)) {
            channelName = "苹果手机App";
        } else {
            channelName = "大屏幕";
        }

        return channelName;
    }
    
    public static String hidePhone(String phone){
      if((phone!=null&&!"".equals(phone))&&phone.length()>10){
        phone = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
      }
      return phone;
    }
    
	public static String hideUserName(String userName) {
		if (StringUtils.isNotEmpty(userName)) {
			userName = userName.substring(0, 1) + "**" + userName.substring(userName.length() - 1, userName.length());
		} else {
			userName = "**";
		}
		return userName;
	}
	
	public static String hideMail(String mail) {
		if (StringUtils.isNotEmpty(mail)&&mail.indexOf("@")>0) {
			String mails[] = mail.split("@");
			String name = mails[0];
			
			mail = name.substring(0, 1) + "**" + name.substring(name.length() - 1, name.length())+"@**.com";
		} else {
			mail = "**";
		}
		return mail;
	}
    
	
    public static String hideCardNum(String cardNum){
    	if ((cardNum==null||"".equals(cardNum)) || (cardNum.length() < 8)) {
            return cardNum;
        }
        return cardNum.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
      }
    
    /**
     * 验证是否是URL
     * @param url
     * @return
     */
    public static boolean verifyUrl(String url){
        
        // URL验证规则
        String regEx ="[a-zA-z]+://[^\\s]*";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
        
    }
    
    /* 
     * 判断是否为整数  
     * @param str 传入的字符串  
     * @return 是整数返回true,否则返回false  
   */  
    public static boolean isNumeric(String str){  
      Pattern pattern = Pattern.compile("[0-9]*");  
      return pattern.matcher(str).matches();     
  }  
    
    public static String returnSexByCertificateNum(String certificateNum){
      if(certificateNum.length()>=18){
        String sex = certificateNum.substring(certificateNum.length()-2, certificateNum.length()-1);
        Integer sexNum = Integer.valueOf(sex);
        if (sexNum % 2 != 0) {
             return "男";
          } else {
            return "女";
          }
      }
      return "";
    }
    
    public static double formatDouble(double d){
      BigDecimal b = new BigDecimal(d);
      d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();   
      return d;
    }
    
    
    public static double formatDouble(double d,int pos){
      BigDecimal b = new BigDecimal(d);
      d = b.setScale(pos, BigDecimal.ROUND_HALF_UP).doubleValue();   
      return d;
    }
    
    public static BigDecimal formatBigDecimal(BigDecimal d){
      d = d.setScale(0, BigDecimal.ROUND_HALF_UP);   
      return d;
    }
    
    public static byte[] int2ByteArray(int n){
        byte[] b = new byte[4];  
        b[0] = (byte) (n & 0xff);  
        b[1] = (byte) (n >> 8 & 0xff);  
        b[2] = (byte) (n >> 16 & 0xff);  
        b[3] = (byte) (n >> 24 & 0xff);  
        return b;  
             }
    
    /**
     * 10进制转2进制不足位补全
     * @param n
     * @param pos
     * @return
     */
    public static String toBinaryString(Integer n,Integer pos){
      String s = Integer.toBinaryString(n) ;
      //System.out.println(s.length());
      Integer length = s.length();
      if(length<8){
        for(int i=0;i<8-length;i++){
          s = "0"+s;
        }
      }
      return s;
    }
    
	public static String formatDistance(Integer distance){
		if(distance>=1000){
			double dis = (double)distance/1000;
			return dis+"km";
		}else{
			return distance+"m";
		}
	}
	
	/**
	 * 判断是否为奇数
	 * @param a
	 * @return
	 */
	public static boolean isOdd(int a) {
      if ((a&1)==1) {
          return true;
      }
      return false;
    }
	
	public static String random6(){
	  double random =  (Math.random()*9+1)*100000;
	  return String.valueOf(random);
	}
	
	
	public static String RMBformat(Integer amount){
	  String rmb = "¥";
	  double d1 = amount/100d;
	  rmb = rmb + String.format("%.2f", d1);
	  return rmb;
	}
	
	public static String RMBformatDouble(Integer amount){
	  double d1 = amount/100d;
      return String.format("%.2f", d1);
	}

  public static String changeNullToEmpty(Object obj) {
    if (obj == null) {
      return "";
    }
    return obj.toString();
  }
  
  /**
   * 对字符串处理:将指定位置到指定位置的字符以星号代替
   *
   * @param content 传入的字符串
   * @param begin   开始位置
   * @param end     结束位置
   * @return
   */
  public static String getStarString(String content, int begin, int end) {

      if (begin >= content.length() || begin < 0) {
          return content;
      }
      if (end >= content.length() || end < 0) {
          return content;
      }
      if (begin >= end) {
          return content;
      }
      String starStr = "";
      for (int i = begin; i < end; i++) {
          starStr = starStr + "*";
      }
      return content.substring(0, begin) + starStr + content.substring(end, content.length());
  }


  /**
   * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替
   *
   * @param content  传入的字符串
   * @param frontNum 保留前面字符的位数
   * @param endNum   保留后面字符的位数
   * @return 带星号的字符串
   */

  public static String getStarString2(String content, int frontNum, int endNum) {

      if (frontNum >= content.length() || frontNum < 0) {
          return content;
      }
      if (endNum >= content.length() || endNum < 0) {
          return content;
      }
      if (frontNum + endNum >= content.length()) {
          return content;
      }
      String starStr = "";
      for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
          starStr = starStr + "*";
      }
      return content.substring(0, frontNum) + starStr
              + content.substring(content.length() - endNum, content.length());

  }
//  public static String createRandom(int length) {
//      return UUIDUtil.getUUID().replace("-","" ).substring(0,length );
//  }
  public static String concat(List<String> stringList,final String split) {

      if(BeanUtils.isNotEmpty(stringList)){
          return stringList.stream().reduce("", (s1, s2) -> s1.concat(split).concat(s2)).substring(split.length() );
      }else{
          return "";
      }
  }

  public static String urlDecode(String str){
      String decode = null;
      try {
          decode = URLDecoder.decode(str, "UTF-8");
          decode= decode.replace(" ", "+");
          if(!str.equals(decode)){
              return urlDecode(decode);
          }else{
              return decode;
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      return "";
  }
  public static String formatStrFromLong(Long digital, Integer len) {
      String str = "000000000000000";
      String intStr = digital + "";
      if (intStr.length() <= len) {
          intStr = str.substring(0, len - intStr.length()) + intStr;
      } else {
          intStr = intStr.substring(intStr.length() - len);
      }

      return intStr;
  }
  
	
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div(Integer v1, Integer v2, int scale) {
		if(v1==null)
			v1=0;
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static Integer multiply(BigDecimal v1,Integer v2){
		if(v1==null)
			return 0;
		
		v1 = v1.multiply(new BigDecimal(v2.toString()));
		return v1.intValue();
	}
	
	
	public static String patternHtml(String html){
		  Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		  Matcher m = p.matcher(html);
		  List<String> srcs = new ArrayList<String>();
		        while(m.find()){
		            srcs.add(m.group(1));
		        }
		  String regex = "<[^>]*>";
		  String str = html.replaceAll(regex, "");
		  str = str.replace("&nbsp;", "");
		  return str;
	}
	
	public static void main(String args[]) {
//		String html = "<p>问哦我柔佛IE入会费&nbsp; 我为金额噢if胡伟方脑壳京东数科陆金所的&nbsp; 欧沃IE及品位和覅发货前二位我金融IP为妻儿 我温柔我我就开始大部分克里斯建安费</p>";
//		System.out.print(patternHtml(html));
		
//		System.out.println(addressResolution("浙江省湖州市吴兴区月河街道湖东府1幢502室"));
//		System.out.println(addressResolution("湖北省黄梅县大河会镇杨凼村四组"));
//		System.out.println(addressResolution("杭州市西湖区兰庭公寓3幢1单元703室"));
		
		System.out.println(hideMail("yingjun@126.com"));
		BigDecimal price = new BigDecimal("0");
		DecimalFormat df2 =new DecimalFormat("#.00"); 
	    String str = df2.format(price);  
	    System.out.println(new BigDecimal(str));  //13.15  
	}
	
	public static BigDecimal formatBigDecimal2(BigDecimal price){
		DecimalFormat df2 =new DecimalFormat("#.00"); 
	    String str = df2.format(price);  
	    return new BigDecimal(str);  //13.15  
	}
	
	/**
	 * 验证手机号码的合法性
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		if (phone.length() != 11) {
			return false;
		} else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			boolean isMatch = m.matches();
			if (isMatch) {
				return true;
			} else {
				return false;
			}
		}
	}
	/**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static Map<String,String> addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
//        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
//            table.add(row);
        }
        return row;
//        return table;
    }
    
    public static int getAgeByPsptNo(String psptNo) {
        if (psptNo==null||"".equals(psptNo)) {
            return 0;
        }
        String birthDay = psptNo.substring(6, 14);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String yearStr = time.split("-")[0];
        String monthStr = time.split("-")[1];
        String dayStr = time.split("-")[2];
        String yearBirthStr = birthDay.substring(0, 4);
        String monthBirthStr = birthDay.substring(4, 6);
        String dayBirthStr = birthDay.substring(6);
        int year = Integer.valueOf(yearStr);
        int yearBirth = Integer.valueOf(yearBirthStr);
        if (year - yearBirth <= 0) {
            return 0;
        }
        int age = year - yearBirth;
        int month = Integer.valueOf(monthStr);
        int monthBirth = Integer.valueOf(monthBirthStr);
        if (month - monthBirth > 0) {
            return age;
        }
        if (month - monthBirth < 0) {
            return --age;
        }
        int day = Integer.valueOf(dayStr);
        int dayBirth = Integer.valueOf(dayBirthStr);
        if (day - dayBirth >= 0) {
            return age;
        }
        return --age;
    }
    
    
        /**
         * 获取登录用户的IP地址
         *
         * @param request
         * @return
         */
        public static String getIpAddr(HttpServletRequest request) {
        	if(request==null)
        		request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        	
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = "127.0.0.1";
            }
            if (ip.split(",").length > 1) {
                ip = ip.split(",")[0];
            }
            return ip;
        }

        /**
         * 通过IP获取地址(需要联网，调用淘宝的IP库)
         *
         * @param ip
         * @return
         */
        public static String getIpInfo(String ip) {
            if ("127.0.0.1".equals(ip)) {
                ip = "127.0.0.1";
            }
            String info = "";
            try {
                URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
                HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
                htpcon.setRequestMethod("GET");
                htpcon.setDoOutput(true);
                htpcon.setDoInput(true);
                htpcon.setUseCaches(false);

                InputStream in = htpcon.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                StringBuffer temp = new StringBuffer();
                String line = bufferedReader.readLine();
                while (line != null) {
                    temp.append(line).append("\r\n");
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                JSONObject obj = (JSONObject) JSON.parse(temp.toString());
                if (obj.getIntValue("code") == 0) {
                    JSONObject data = obj.getJSONObject("data");
                    info += data.getString("country") + " ";
                    info += data.getString("region") + " ";
                    info += data.getString("city") + " ";
                    info += data.getString("isp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return info;
        }

        public static List<Integer> randomdBetween(Integer min,Integer max,Integer num){
        	List<Integer> retList = new ArrayList<Integer>();
        	
        	for(int i= 0;i<num;i++){
	        	Random random = new Random();
	            Integer s = random.nextInt(max) % (max - min + 1) + min;
	            retList.add(s);
        	}
        	return retList;
        }
}
