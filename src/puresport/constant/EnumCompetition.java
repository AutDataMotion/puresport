package puresport.constant;

public enum EnumCompetition {
	ShengYunHui(0,"0","省运会"),
	YaYunHui(1,"1","亚运会"),
	QingAoHui(2,"2","青奥会"),
	JunYunHui(3,"3","军运会"),
	DongJingAoYunHui(4,"4","东京奥运会"),
	//zhuchaobin, 2019-12-04
	DongQingAoHui(6,"6","冬青奥会"),
	ShiSiDongHui(7,"7","十四冬会"),
	;
	private int index;
	private String index_str;
	private String competitionName;
	private EnumCompetition(int index,String index_str,String competitionName)
	{
		this.index = index;
		this.index_str = index_str;
		this.competitionName = competitionName;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}
	public int getIndex()
	{
		return this.index;
	}
	public void setIndex_str(String index_str)
	{
		this.index_str = index_str;
	}
	public String getIndex_str()
	{
		return this.index_str;
	}
	
	public void setCompetitionName(String competitionName)
	{
		this.competitionName = competitionName;
	}
	public String getCompetitionName()
	{
		return this.competitionName;
	}
	
	public static String getCompetitionName(String index_str)
	{
		if("0".equals(index_str))
			return "省运会";
		else if("1".equals(index_str))
			return "亚运会";
		else if("2".equals(index_str))
			return "青奥会";
		else if("3".equals(index_str))
			return "军运会";
		else if("6".equals(index_str))
			return "冬青奥会";
		else if("4".equals(index_str))
			return "东京奥运会";
		else if("7".equals(index_str))
			return "十四冬会";
		else
			return "";
	}
}
