package org.guili.ecshop.util.chongwu;

/**
 * 宠物类别常量
 * @author Administrator
 *
 */
public enum  PetCategoryConstants {
    Mini_DOG(1L,"小型犬"),
    Middle_DOG(2L,"中型犬"),
    Large_DOG(3L,"大型犬"),
    MAO(4L,"猫");
    
	private Long  parentCategoryId;
	private String categoryName;
	
	private PetCategoryConstants(Long parentCategoryId,String categoryName){
		this.parentCategoryId=parentCategoryId;
		this.categoryName=categoryName;
	}
	public String getCategoryName(){
   	 return this.categoryName;
    }
	public Long getParentCategoryId(){
	   	 return this.parentCategoryId;
	}
	
	 public static void main(String[] args){
    	 for(PetCategoryConstants name :PetCategoryConstants.values()){
    		 System.out.println(name+" : "+name.getCategoryName()+"--id:"+name.getParentCategoryId());
    	 }
     }
}
