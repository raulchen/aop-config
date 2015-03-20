package cn.otc.aopconfig.model;


public enum PointType {

	BEFORE("before"), AFTER("after"), AFTER_THROWING("after_throwing"), ATTER_RETURNING(
			"after_returning"), AROUND("around");

	String name;

	PointType(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public static PointType of(String name){
		name=name.toLowerCase();
		for(PointType pointType: PointType.values()){
			if(pointType.getName().equals(name)){
				return pointType;
			}
		}
		return null;
	}
}