package org.mhq0123.demo.fusionpay.sevenpay.sign;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
  
/** 
 */  
public class RSAUtils {  
  
    /** 
     * 签名算法RSA
     */  
    public static final String KEY_ALGORITHM = "RSA";  
      
    /** 
     * 签名算法SHA1WithRSA
     */  
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
  
	/**
	 * 商户私钥
	 */
	private static PrivateKey sysPrivateKey = null;
	
	/**
	 * 商户公钥
	 */
	private static PublicKey sysPublicKey = null;
	
	/**
	 * 七分钱公钥
	 */
	private static PublicKey sevenPubKey = null;

    
    static{
		
		try {
			// 商户私钥
			String privateKeyStr = 
					"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALys+oYaxqv4FYju"+
					"8C1poM6qmHLjWPnXzqEJT0NxyFXgdaK/Qe9DcpcASod9mIAdlLIxJEyYNlWeonAJ"+
					"VYW8pQ+pTVGwI9n0iaT71ldWQzcMN3Dvi/+zpgw3HxxO7HJtEIlR84pvILv1yceC"+
					"ZCqqQ4O/4SemsG00oTiTyD3SM2ZvAgMBAAECgYBLToeX6ywNC7Icu7Hljll+45yB"+
					"jri+0CJLKFoYw1uA21xYnxoEE9my54zX04uA502oafDhGYfmWLDhIvidrpP6oalu"+
					"URb/gbV5Bdcm98gGGVgm6lpK+G5N/eawXDjP0ZjxXb114Y/Hn/oVFVM9OqcujFSV"+
					"+Wg4JgJ4Mmtdr35gYQJBAPbhx030xPcep8/dL5QQMc7ddoOrfxXewKcpDmZJi2ey"+
					"381X+DhuphQ5gSVBbbunRiDCEcuXFY+R7xrgnP+viWcCQQDDpN8DfqRRl+cUhc0z"+
					"/TbnSPJkMT/IQoFeFOE7wMBcDIBoQePEDsr56mtc/trIUh/L6evP9bkjLzWJs/kb"+
					"/i25AkEAtoOf1k/4NUEiipdYjzuRtv8emKT2ZPKytmGx1YjVWKpyrdo1FXMnsJf6"+
					"k9JVD3/QZnNSuJJPTD506AfZyWS6TQJANdeF2Hxd1GatnaRFGO2y0mvs6U30c7R5"+
					"zd6JLdyaE7sNC6Q2fppjmeu9qFYq975CKegykYTacqhnX4I8KEwHYQJAby60iHMA"+
					"YfSUpu//f5LMMRFK2sVif9aqlYbepJcAzJ6zbiSG5E+0xg/MjEj/Blg9rNsqDG4R"+
					"ECGJG2nPR72O8g==";
			
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
			
			// 因为java里面不识别x509格式的私钥，所以必须转换为 pkcs8格式方可使用
			// java异常描述为： java.security.spec.InvalidKeySpecException: Only RSAPrivate(Crt)KeySpec and PKCS8EncodedKeySpec supported for RSA private keys
			byte[] keyBytes = Base64.decode(privateKeyStr);  
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			sysPrivateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
			
			// 商户公钥
			String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8rPqGGsar+BWI7vAtaaDOqphy41j5186hCU9DcchV4HWiv0HvQ3KXAEqHfZiAHZSyMSRMmDZVnqJwCVWFvKUPqU1RsCPZ9Imk+9ZXVkM3DDdw74v/s6YMNx8cTuxybRCJUfOKbyC79cnHgmQqqkODv+EnprBtNKE4k8g90jNmbwIDAQAB";
			
			byte[] pubkeyBytes = Base64.decode(publicKeyStr);  
			X509EncodedKeySpec pubkeySpec = new X509EncodedKeySpec(pubkeyBytes);  
			sysPublicKey = keyFactory.generatePublic(pubkeySpec); 
			
			// 七分钱公钥
			String sevenPubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4G+OSs43I0Ctw7nxxuJPauTFsPyjMKkfsvYEcw4wqbn82KQWbFiSTy7P5hul4wdoZaFW5lSnHug+lyjn64t0dtCsaViOWefWrpL1gWZNpOc9gk6qNhQ0120ikHLE1SLH//gVStf+TDeVtaW+4Uzs5J7+/shdvfgU5T4+gxBk9jQIDAQAB";
			byte[] sevenPubKeyBytes = Base64.decode(sevenPubKeyStr);  
			X509EncodedKeySpec sevenPubKeySpec = new X509EncodedKeySpec(sevenPubKeyBytes);  
			sevenPubKey = keyFactory.generatePublic(sevenPubKeySpec);  
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
    public static PublicKey getSevenPayPubKey(){
    	return sevenPubKey;
    }
    
    /** 
     * <p> 
     * 用私钥对信息生成数字签名 
     * </p> 
     *  
     * @param data 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String sign(byte[] data, PrivateKey privateKey) throws Exception {
    	
    	if(null == privateKey){
			privateKey = sysPrivateKey;
    	}
    	
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateKey);
        signature.update(data);  
        return parseByte2HexStr(signature.sign());
    }  
  
    /** 
     * <p> 
     * 校验数字签名 
     * </p> 
     *  
     * @param data 已加密数据 
     * @param publicKey 公钥(BASE64编码) 
     * @param sign 数字签名 
     *  
     * @return 
     * @throws Exception 
     *  
     */  
    public static boolean verify(byte[] data, PublicKey publicKey, String sign)
            throws Exception {  
    	
    	if(null == publicKey){
			publicKey = sysPublicKey;
    	}
    	
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(publicKey);
        signature.update(data);  
        return signature.verify(parseHexStr2Byte(sign));  
    }  
  

    /**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {  
                    hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
	} 
	
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1) {
        	return null;  
        }
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
            result[i] = (byte) (high * 16 + low);  
        }  
        return result;
	} 
	
}
