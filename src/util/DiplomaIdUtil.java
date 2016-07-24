package util;


public class DiplomaIdUtil {
	
	/**
	 * 证书编号长度
	 */
	public static final int Diploma_Length = 14;
		
	
	/**
	 * 工程协会类型号
	 */
	public static final String Diploma_Type_gcxh = "SGL";
	
	/**
	 * 水利协会类型号
	 */
	public static final String Diploma_Type_slxh = "SQX";
	
	
	/**
	 * 审批单位区域代码
	 */
	public static enum Area{
		CHANGJIAO("水利部长江水利委员会","01"),
		HUANGHE("水利部黄河水利委员会","02"),
		HUAIHE("水利部淮河水利委员会","03"),
		HAIHE("水利部海河水利委员会","04"),
		ZHUJIANG("水利部珠江水利委员会","05"),
		SONGLIAO("水利部松辽水利委员会","06"),
		TAIHU("水利部太湖流域管理局","07"),
		OTHER("其他部直属及非水利系统单位","08"),
		BEIJING("北京市","11"),
		TIANJING("天津市","12"),
		HEBEI("河北省","13"),
		SHANXI1("山西省","14"),
		NEIMENG("内蒙省","15"),
		LIAONING("辽宁省","16"),
		JILIN("吉林省","22"),
		HEILONGJIANG("黑龙江","23"),
		SHANGHAI("上海市","31"),
		JIANGSU("江苏省","32"),
		ZHEJIANG("浙江省","33"),
		ANHUI("安徽省","34"),
		FUJIAN("福建省","35"),
		SHANDONG("山东省","37"),
		HENAN("河南省","41"),
		HUBEI("湖北省","42"),
		HUNAN("湖南省","43"),
		GUANGDONG("广东省","44"),
		GUANGXI("广西壮族自治区","45"),
		HAINAN("海南省","46"),
		CHONGQING("重庆市","50"),
		SICHUAN("四川省","51"),
		GUIZHOU("贵州省","52"),
		YUNNAN("云南省","53"),
		XIZANG("西藏自治区","54"),
		SHANXI2("陕西省","61"),
		GANSU("甘肃省","62"),
		QINGHAI("青海省","63"),
		NINGXIA("宁夏回族自治区","64"),
		XINJIANG("新疆维吾尔自治区","65"),
		XINJIANGBINTUAN("新疆兵团","09");
		
		private String name;
		private String id;
		
		
		
		private Area(String name,String id){
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public String getId() {
			return id;
		}
		
		public static String getName(String id){
			for(Area a:Area.values()){
				if(a.getId().equals(id)){
					return a.getName();
				}
			}
			return null;
		}
		
		public static String getid(String name){
			for(Area a:Area.values()){
				if(a.getName().equals(name)){
					return a.getId();
				}
			}
			return null;
		}
	}
	
	/**
	 * 根据证书编号获取
	 * @param diplomaid
	 * @return
	 */
	public static String getArea(String diplomaid){
		String areaid = diplomaid.substring(7, 9);
		return areaid;
	}
	
	public static void main(String[] args) {
		System.out.println(getArea("SGL20150807623"));
	}
}
