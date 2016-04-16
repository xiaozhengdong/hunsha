package org.guili.ecshop.util.chongwu;

public enum PetAddressConstants {
//	1	北京
//	2	上海
//	3	杭州
//	4	深圳
//	5	苏州
//	6	广州
//	7	天津
//	8	成都
//	9	郑州
//	10	武汉
//	11	重庆
//	12	温州
//	13	南京

	 BEIJING(1L,"北京"),
     SHANGHAI(2L,"上海"),
     HANGZHOU(3L,"杭州"),
     SHENZHEN(4L,"深圳"),
     SUZHOU(5L,"苏州"),
     GUANGZHOU(6L,"广州"),
     TIANJING(7L,"天津"),
     CHENGDU(8L,"成都"),
     ZHENGZHOU(9L,"郑州"),
     WUHAN(10L,"武汉"),
     CHONGQING(11L,"重庆"),
     WENZHOU(12L,"温州"),
     NANJING(13L,"南京");
    
	 private Long  id;
	 private String addressName;
	
	 private PetAddressConstants(Long id,String addressName){
	 	 this.id=id;
		 this.addressName=addressName;
	 }
	
	  public Long getId() {
		return id;
	}

	public String getAddressName() {
		return addressName;
	}

	public static void main(String[] args){
     	 for(PetCategoryConstants name :PetCategoryConstants.values()){
    		 System.out.println(name+" : "+name.getCategoryName()+"--id:"+name.getParentCategoryId());
      	 }
      }
}
